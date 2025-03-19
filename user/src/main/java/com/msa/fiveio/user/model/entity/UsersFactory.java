package com.msa.fiveio.user.model.entity;

import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import java.util.UUID;

public class UsersFactory {

  //Users 객체 생성
  public static Users createUser(
      String username,
      String password,
      String slackId,
      String hubId,
      String roleEnum
  ) {
    return Users.builder()
        .username(username)
        .password(password)
        .slackId(slackId)
        .hubId(UUID.fromString(hubId))
        .role(UsersRoleEnum.valueOf(roleEnum))
        .build();
  }
}