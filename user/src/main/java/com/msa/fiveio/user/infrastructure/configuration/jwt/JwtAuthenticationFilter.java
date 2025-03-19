package com.msa.fiveio.user.infrastructure.configuration.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import com.msa.fiveio.user.presentation.dto.UsersLoginRequestDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;

    public JwtAuthenticationFilter(JwtUtil jwtUtil
        , JwtProperties jwtProperties
    ) {
        this.jwtUtil = jwtUtil;
        this.jwtProperties = jwtProperties;
        setFilterProcessesUrl("/api/users/signIn");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {

        try {

            UsersLoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(),
                UsersLoginRequestDto.class);
            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    requestDto.getUsername(),
                    requestDto.getPassword(),
                    null
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Failed to read login request: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        super.setRequiresAuthenticationRequestMatcher(
            new AntPathRequestMatcher(filterProcessesUrl, "POST"));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UsersRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getRole();

        String token = jwtUtil.createToken(username, role);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(Map.of(
                "accessToken", token
            )));
        } catch (IOException e) {
            log.error("Failed to write response: {}", e.getMessage());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException failed) {

        log.warn("Authentication failed for user: {}", request.getParameter("username"));
        response.setStatus(401);
    }

}