package com.msa.fiveio.user.presentation.controller;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.UserErrorCode;
import com.msa.fiveio.user.application.UsersService;
import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.repository.UsersRepository;
import com.msa.fiveio.user.presentation.dto.UsersLoginRequestDto;
import com.msa.fiveio.user.presentation.dto.UsersProfileDto;
import com.msa.fiveio.user.presentation.dto.UsersSignUpRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Service", description = "사용자 서비스 API")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersRepository usersRepository;

    @Operation(summary = "사용자 로그인", description = "사용자 로그인 후 JWT 토큰 발급")
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UsersLoginRequestDto usersLoginRequestDto) {
        return ResponseEntity.ok(usersService.login(usersLoginRequestDto));
    }

//    @Operation(summary = "사용자 로그아웃", description = "사용자 로그아웃 후 세션을 만료")
//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request) {
//        usersService.logout(request);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public ResponseEntity<String> signup(@Valid @RequestBody UsersSignUpRequestDto usersSignUpRequestDto) {
        usersService.signup(usersSignUpRequestDto);
        return ResponseEntity.ok("회원가입 성공");
    }


//    @PostMapping("/api/users")
//    @Operation(summary = "사용자 정보 조회", description = "사용자의 정보를 확인합니다")

}
