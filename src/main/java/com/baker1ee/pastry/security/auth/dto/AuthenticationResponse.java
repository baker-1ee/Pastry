package com.baker1ee.pastry.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;

    private UserResponse user;

    public static AuthenticationResponse of(String token, UserResponse user) {
        return AuthenticationResponse.builder()
                .token(token)
                .user(user)
                .build();
    }
}
