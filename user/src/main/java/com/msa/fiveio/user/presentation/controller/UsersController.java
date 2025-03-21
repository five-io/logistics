package com.msa.fiveio.user.presentation.controller;

import com.msa.fiveio.user.application.UsersService;
import com.msa.fiveio.user.model.repository.UsersRepository;
import com.msa.fiveio.user.presentation.dto.UserDto;
import com.msa.fiveio.user.presentation.dto.UsersSignUpRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public ResponseEntity<UserDto> signup(
        @Valid @RequestBody UsersSignUpRequestDto usersSignUpRequestDto) {
        UserDto userDto = usersService.signup(usersSignUpRequestDto);
        return ResponseEntity.ok(userDto);
    }

//    @PostMapping("/api/users")
//    @Operation(summary = "사용자 정보 조회", description = "사용자의 정보를 확인합니다")

}
