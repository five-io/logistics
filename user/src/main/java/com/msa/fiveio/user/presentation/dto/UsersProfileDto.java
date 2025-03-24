package com.msa.fiveio.user.presentation.dto;

import com.msa.fiveio.user.model.entity.Users;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UsersProfileDto {

  private String username;

  private Long userId;

  private String slackId;

  private String hubId;

  private String role;

  private LocalDateTime createDate;

  public UsersProfileDto(Users users) {
    this.userId = users.getUserId();
    this.username = users.getUsername();
    this.slackId = users.getSlackId();
    this.hubId = String.valueOf(users.getHubId());
    this.role= String.valueOf(users.getRole());
    this.createDate = users.getCreatedAt();
  }
}