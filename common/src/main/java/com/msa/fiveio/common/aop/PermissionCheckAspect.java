package com.msa.fiveio.common.aop;

import com.msa.fiveio.common.annotation.ApiPermission;
import com.msa.fiveio.common.annotation.ApiPermission.Role;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class PermissionCheckAspect {

    @Around("@annotation(apiPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, ApiPermission apiPermission)
        throws Throwable {
        // 현재 로그인한 사용자의 권한 가져오기
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String rolesHeader = request.getHeader("X-User-Role");

        if (rolesHeader == null || rolesHeader.isEmpty()) {
            throw new AccessDeniedException("권한 정보가 없습니다.");
        }

        // 사용자의 권한 목록 파싱
        Set<String> userRoles = new HashSet<>(Arrays.asList(rolesHeader.split(",")));
        // API에서 요구하는 권한
        Set<Role> requiredRoles = new HashSet<>(Arrays.asList(apiPermission.roles()));

        // 사용자가 필요한 권한을 가지고 있는지 확인
        boolean hasPermission = userRoles.stream()
            .map(Role::valueOf)
            .anyMatch(requiredRoles::contains);

        System.out.println("=== 권한 확인 ===");
        System.out.println("요청 헤더 roles: " + userRoles);
        System.out.println("필요한 roles: " + requiredRoles);
        System.out.println("허용 여부: " + hasPermission);

        if (!hasPermission) {
            throw new AccessDeniedException("권한이 없습니다.");
        }

        // 권한이 있으면 API 실행
        return joinPoint.proceed();
    }

}
