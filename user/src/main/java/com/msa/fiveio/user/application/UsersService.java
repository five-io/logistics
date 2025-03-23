package com.msa.fiveio.user.application;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.UserErrorCode;
import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.entity.UsersFactory;
import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import com.msa.fiveio.user.model.repository.UsersRepository;
import com.msa.fiveio.user.presentation.dto.UsersProfileDto;
import com.msa.fiveio.user.presentation.dto.UsersSignUpRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;


  //회원가입
  public void signUp(UsersSignUpRequestDto usersSignUpRequestDto) {
    UsersRoleEnum roleEnum = usersSignUpRequestDto.getRole();
    ManagersTypeEnum managersType = usersSignUpRequestDto.getManagersType();

    //유저네임 중복체크
    usersRepository.findByUsername(usersSignUpRequestDto.getUsername())
        .ifPresent(user -> {
          throw new CustomException(UserErrorCode.USER_ALEADY_EXIST);
        });

    Users users = UsersFactory.createUser(
        usersSignUpRequestDto.getUsername(),
        passwordEncoder.encode(usersSignUpRequestDto.getPassword()),
        usersSignUpRequestDto.getSlackId(),
        UUID.fromString(usersSignUpRequestDto.getHubId()),
        roleEnum
//        managersType
    );

    usersRepository.save(users);
  }

  //로그아웃
  public void logout(String token) {
  }

  //사용자 정보 조회
  public UsersProfileDto getUserProfile(Long userId) {
    Users users = usersRepository.findByUserId(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_ERROR_CODE));
    return new UsersProfileDto(users);
  }
}