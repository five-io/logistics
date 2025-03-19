package com.msa.fiveio.user.model.entity;


import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;

public class UsersFactory {

    //Users 객체 생성
    public static Users createUser(
        String username,
        String password,
        String slackId,
        UsersRoleEnum role
    ) {
        return Users.builder()
            .username(username)
            .password(password)
            .slackId(slackId)
            .role(role)
            .build();
    }
}