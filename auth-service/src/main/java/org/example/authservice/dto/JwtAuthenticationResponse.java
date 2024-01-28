package org.example.authservice.dto;


import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;

    private String refreshToken;


}
