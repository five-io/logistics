package com.msa.fiveio.user.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UsersProfileDto {

    private final String username;

    private final String name;

    private final String email;

    private final LocalDateTime createDate;

}