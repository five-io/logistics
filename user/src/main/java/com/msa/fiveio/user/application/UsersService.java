package com.msa.fiveio.user.application;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.UserErrorCode;
import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.entity.UsersFactory;
import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import com.msa.fiveio.user.model.repository.UsersRepository;
import com.msa.fiveio.user.presentation.dto.UsersSignUpRequestDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;


  //회원가입
  public void signup(UsersSignUpRequestDto usersSignUpRequestDto) {

    // 중복 체크
    usersRepository.findByUsername(usersSignUpRequestDto.getUsername())
        .ifPresent(user -> {
          throw new CustomException(UserErrorCode.USER_ERROR_CODE);
        });

    // 입력된 role을 Enum으로 변환
    UsersRoleEnum roleEnum;
    try {
      roleEnum = UsersRoleEnum.valueOf(usersSignUpRequestDto.getRole());
    } catch (IllegalArgumentException e) {
      throw new CustomException(UserErrorCode.ENUM_ERROR_CODE);
    }

    // 빌더형태로 저장 , 비밀번호 암호화
    Users user = UsersFactory.createUser(
        usersSignUpRequestDto.getUsername(),
        passwordEncoder.encode(usersSignUpRequestDto.getPassword()),
        usersSignUpRequestDto.getSlackId(),
        usersSignUpRequestDto.getEmail(),
        roleEnum
    );

    usersRepository.save(user);
  }

  //로그아웃
  public void logout(String token) {
  }

  //사용자 정보 조회
  public Optional<Users> getUserInfo(String username) {
    return usersRepository.findByUsername(username);
  }
}