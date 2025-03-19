package com.msa.fiveio.user.presentation.dto;

import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;

public record UserDto(Long id,
                      String username,
                      String password,
                      UsersRoleEnum role) {

    public static UserDto of(Users user) {
        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getRole()
        );
    }
}