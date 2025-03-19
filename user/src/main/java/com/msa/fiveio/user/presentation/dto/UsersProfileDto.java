package com.msa.fiveio.user.presentation.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UsersProfileDto {

  private String username;

  private String name;

  private String email;

  private LocalDateTime createDate;

}