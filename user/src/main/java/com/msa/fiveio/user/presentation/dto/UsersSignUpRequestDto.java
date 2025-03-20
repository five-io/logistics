package com.msa.fiveio.user.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.UUID;

@Getter
public class UsersSignUpRequestDto {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Size(min = 4, max = 10, message = "아이디는 최소 4자 이상, 최대 10자 이하여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 알파벳 소문자(a-z)와 숫자(0-9)만 포함해야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상, 최대 15자 이하여야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "비밀번호는 알파벳 대소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "슬랙 ID는 필수 입력값입니다.")
    private String slackId;

    @NotBlank(message = "허브 ID는 필수 입력값입니다.")
    private String hubId;

    @NotBlank(message = "사용자 역할(role)은 필수 입력값입니다.")
    private String role;

}