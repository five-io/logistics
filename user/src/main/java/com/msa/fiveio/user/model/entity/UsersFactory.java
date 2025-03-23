package com.msa.fiveio.user.model.entity;

import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import java.util.UUID;

public class UsersFactory {

  //Users 객체 생성
  public static Users createUser(
      String username,
      String password,
      String slackId,
      UUID hubId,
      UsersRoleEnum roleEnum
//      ManagersTypeEnum managersType
  ) {
    return Users.builder()
        .username(username)
        .password(password)
        .slackId(slackId)
        .hubId(hubId)
        .role(UsersRoleEnum.valueOf(roleEnum.getAuthority()))
//        .managersType(managersType)
        .build();
  }
}