package com.msa.fiveio.user.presentation.dto;

import com.msa.fiveio.user.model.entity.Users;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UsersProfileDto {

  private String username;

  private Long userId;

  private String slack_id;


  private String hubId;

  private LocalDateTime createDate;

  public UsersProfileDto(Users users) {
    this.username = users.getUsername();
    this.userId = users.getUserId();
    this.slack_id = users.getSlackId();
    this.hubId = String.valueOf(users.getHubId());
    this.createDate = getCreateDate();
  }
}