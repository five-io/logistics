package com.msa.fiveio.user.model.entity;

import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import java.util.UUID;

public class UsersFactory {

  //Users 객체 생성
  public static Users createUser(
      String username,
      String password,
      String slackId,
      UUID hub_id,
      UsersRoleEnum roleEnum
  ) {
    return Users.builder()
        .username(username)
        .password(password)
        .slackId(slackId)
        .hub_id(hub_id)
        .role(UsersRoleEnum.valueOf(roleEnum.getAuthority()))
        .build();
  }
}