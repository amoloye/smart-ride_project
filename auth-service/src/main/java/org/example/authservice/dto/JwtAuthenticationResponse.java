package org.example.authservice.dto;


import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;

    private String refreshToken;

    private String role;  // Add a field to store the user's role

    // Constructors, getters, and setters

}
