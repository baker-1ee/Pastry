package com.baker1ee.pastry.security.auth.dto;

import com.baker1ee.pastry.security.auth.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class UserResponse {

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedDatetime())
                .updatedAt(user.getUpdatedDatetime())
                .build();
    }
}
