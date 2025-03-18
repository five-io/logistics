package com.msa.fiveio.user.application;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.UserErrorCode;
import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.entity.UsersRoleEnum;
import com.msa.fiveio.user.model.repository.UsersRepository;
import com.msa.fiveio.user.presentation.dto.UsersLoginRequestDto;
import com.msa.fiveio.user.presentation.dto.UsersSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.msa.fiveio.user.infrastructure.security.JwtUtil;
//import com.msa.fiveio.user.infrastructure.SecurityConfig;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtutil;

    /**
     * 🔹 회원가입
     */
    public void signup(UsersSignUpRequestDto usersSignUpRequestDto) {
        // 중복 체크
        if (isUserExists(usersSignUpRequestDto.getUsername())) {
            throw CustomException.from(UserErrorCode.USER_ERROR_CODE);
        }

        // 입력된 role을 Enum으로 변환 (유효하지 않으면 예외 발생)
        UsersRoleEnum roleEnum;
        try {
            roleEnum = UsersRoleEnum.valueOf(usersSignUpRequestDto.getRole());
        } catch (IllegalArgumentException e) {
            throw new CustomException(UserErrorCode.USER_ERROR_CODE);
        }

        // 비밀번호 암호화 후 저장
        Users user = new Users(
                usersSignUpRequestDto.getUsername(),
                passwordEncoder.encode(usersSignUpRequestDto.getPassword()),
                usersSignUpRequestDto.getSlackId(),
                usersSignUpRequestDto.getEmail(),
                roleEnum
        );
        usersRepository.save(user);
    }


    /**
     * 🔹 사용자 존재 여부 확인
     */
    public boolean isUserExists(String username) {
        return usersRepository.findByUsername(username).isPresent();
    }

    /** 🔹 로그인 및 JWT 발급 */
    public String login(UsersLoginRequestDto usersLoginRequestDto) {
        Optional<Users> userOptional = usersRepository.findByUsername(usersLoginRequestDto.getUsername());

        if (userOptional.isEmpty() || !passwordEncoder.matches(usersLoginRequestDto.getPassword(), userOptional.get().getPassword())) {
            throw new RuntimeException("잘못된 사용자 이름 또는 비밀번호입니다.");
        }

        Users user = userOptional.get();

        // JWT 발급 후 반환
        return jwtutil.generateToken(user.getUsername(), user.getRole());
    }

    /**
     * 🔹 로그아웃 (JWT 기반 Blacklist 방식 고려)
     */
    public void logout(String token) {
        // JWT Blacklist 처리 로직 (추후 Redis 활용 가능)
        // 현재는 JWT 기반이므로 별도 구현 필요 없음
    }

    /**
     * 🔹 사용자 정보 조회
     */
    public Optional<Users> getUserInfo(String username) {
        return usersRepository.findByUsername(username);
    }
}