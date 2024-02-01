package org.example.authservice.service;


import lombok.RequiredArgsConstructor;
import org.example.authservice.client.PassengerClient;
import org.example.authservice.dto.JwtAuthenticationResponse;
import org.example.authservice.dto.RefreshTokenRequest;
import org.example.authservice.dto.SignUpRequest;
import org.example.authservice.dto.SigninRequest;
import org.example.authservice.entity.Role;
import org.example.authservice.entity.User;
import org.example.authservice.repository.UserRepository;
import org.example.cab_userservice.passenger.Passenger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final PassengerClient passengerClient;  // Inject the PassengerClient

    public User signup(SignUpRequest signUpRequest, Role role) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstName());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        User savedUser = userRepository.save(user);

        // Assuming you have a method in PassengerClient to save Passenger
        Passenger passenger = new Passenger();

        // Set Passenger properties based on signUpRequest or any other data
        passenger.setUser(savedUser);
        passenger.setPhoneNumber(signUpRequest.getPhonenumber());
        passengerClient.savePassenger(passenger);  // Make the call to save Passenger

        return savedUser;
    }




    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        // Set additional information based on user role
        switch (user.getRole()) {
            case PASSENGER:
                jwtAuthenticationResponse.setRole("PASSENGER");
                // Additional passenger-related information
                break;
            case ADMIN:
                jwtAuthenticationResponse.setRole("ADMIN");
                // Additional admin-related information
                break;
            case CAB_DRIVER:
                jwtAuthenticationResponse.setRole("CAB_DRIVER");
                // Additional cab driver-related information
                break;
            // Add more cases if you have additional roles
        }

        return jwtAuthenticationResponse;
    }



    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }

        return null;
    }
}
