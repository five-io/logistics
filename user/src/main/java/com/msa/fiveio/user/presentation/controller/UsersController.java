package com.msa.fiveio.user.presentation.controller;

import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_COMPANY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_DELIVERY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_HUB_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_MASTER;

import com.msa.fiveio.common.annotation.ApiPermission;
import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.AuthErrorCode;
import com.msa.fiveio.common.exception.domain.UserErrorCode;
import com.msa.fiveio.user.application.UsersService;
import com.msa.fiveio.user.infrastructure.configuration.jwt.UserDetailsImpl;
import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.repository.UsersRepository;
import com.msa.fiveio.user.presentation.dto.UsersProfileDto;
import com.msa.fiveio.user.presentation.dto.UsersSignUpRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Service", description = "사용자 서비스 API")
@RequiredArgsConstructor
public class UsersController {

  private final UsersService usersService;
  private final UsersRepository usersRepository;

//    @Operation(summary = "사용자 로그아웃", description = "사용자 로그아웃 후 세션을 만료")
//    @PostMapping("/logout")
//    public ResponseEntity<Users> logout {
//    }

  @PostMapping("/signUp")
  @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER,
      ROLE_COMPANY_MANAGER})
  @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
  public ResponseEntity<String> signup(
      @Valid @RequestBody UsersSignUpRequestDto usersSignUpRequestDto) {

    usersService.signUp(usersSignUpRequestDto);

    return ResponseEntity.ok("회원가입 완료");
  }

  @GetMapping("/")
  @Operation(summary = "사용자 정보 조회", description = "사용자의 정보를 확인합니다")
  public ResponseEntity<UsersProfileDto> getUserProfile(@RequestHeader("X-User-Id") String userId) {

    Long parsedUserId = Long.parseLong(userId); // userId는 Long 타입

    return ResponseEntity.ok(usersService.getUserProfile(parsedUserId));
  }
}
