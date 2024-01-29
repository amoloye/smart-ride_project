package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.JwtAuthenticationResponse;
import org.example.authservice.dto.RefreshTokenRequest;
import org.example.authservice.dto.SignUpRequest;
import org.example.authservice.dto.SigninRequest;
import org.example.authservice.entity.Role;
import org.example.authservice.entity.User;
import org.example.authservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup/admin")
    public ResponseEntity<User> signupAdmin(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest, Role.ADMIN));
    }

    @PostMapping("/signup/cab_driver")
    public ResponseEntity<User> signupCabDriver(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest, Role.CAB_DRIVER));
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<User> signupPassenger(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest, Role.PASSENGER));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
