package com.msa.fiveio.user.presentation.dto;

import java.time.LocalDateTime;
import java.util.Locale;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long userId;
    private String username;
    private LocalDateTime updatedAt;

    public UserResponseDto(Long userId, String username, LocalDateTime updatedAt) {
        this.userId = userId;
        this.username = username;
        this.updatedAt = updatedAt;
    }

}
