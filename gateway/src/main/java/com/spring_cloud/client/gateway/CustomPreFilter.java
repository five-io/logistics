package com.spring_cloud.client.gateway;


import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.AuthErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "JWT 인가 필터")
public class CustomPreFilter implements GlobalFilter, Ordered {

    @Value("${service.jwt.secret-key}")
    private String secretKey;
    private static final String BEARER_PREFIX = "Bearer ";


    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static final List<String> EXCLUDED_PATHS = List.of(
        "/api/users/signIn",
        "/api/users/signUp",
        "/v3/api-docs",
        "/swagger-ui.html",
        "/swagger-ui",
        "/webjars/swagger-ui"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        log.info("***********" + path);
//        if (path != null) {
//            return chain.filter(exchange);
//        }

        if (isExcludedPath(path)) {
            log.info("***********" + path);
            return chain.filter(exchange);
        }

        try {
            //토큰가져오기
            String authorization = getJwtFromHeader(exchange);
            String jwtToken = parseAuthorizationToken(authorization);

            //검증
            if (!StringUtils.hasText(jwtToken) || !validateToken(jwtToken)) {
                throw new CustomException(AuthErrorCode.AUTH_NOT_FOUND);
            }

            //유효기간확인
            if (isValidateExpire(jwtToken)) {
                throw new CustomException(AuthErrorCode.AUTH_UNAUTHORIZED);
            }

            //  JWT 검증 성공 후 요청 헤더에 사용자 정보 추가
            ServerWebExchange modifiedExchange = exchange.mutate()
                .request(exchange.getRequest().mutate()
                    .header("X-User-Id", getUserIdFromToken(jwtToken))
                    .header("X-User-Role", getRoleFromToken(jwtToken))
                    .build())
                .build();

            log.info("********** info " + modifiedExchange);

            return chain.filter(exchange);


        } catch (Exception e) {
            e.printStackTrace();
            return sendErrorResponse(exchange, 999, e);
        }


    }


    private boolean isExcludedPath(String path) {
        return EXCLUDED_PATHS.stream().anyMatch(path::contains);
    }


    private String getUserIdFromToken(String jwtToken) {
        return Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(jwtToken)
            .getBody()
            .get("user_id", String.class);
    }

    private String getRoleFromToken(String jwtToken) {
        return Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(jwtToken)
            .getBody()
            .get("role", String.class);
    }


    private Mono<Void> sendErrorResponse(ServerWebExchange exchange, int errorCode, Exception e) {
        String errorBody = "{\"code\": " + errorCode + ", \"message\": \"" + e.getMessage() + "\"}";

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer buffer = response.bufferFactory()
            .wrap(errorBody.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }


    private String parseAuthorizationToken(String authorization) {
        return authorization.replace(BEARER_PREFIX, "").trim();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
            throw e;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token)
            .getBody();
    }


    public String getJwtFromHeader(ServerWebExchange exchange) {
        List<String> authorizations = exchange.getRequest().getHeaders()
            .get(HttpHeaders.AUTHORIZATION);
        if (authorizations == null || authorizations.isEmpty()) {
            throw new CustomException(AuthErrorCode.AUTH_UNAUTHORIZED);
        }
        return authorizations.stream()
            .filter(this::isBearerType)
            .findFirst()
            .orElseThrow(() -> new CustomException(AuthErrorCode.AUTH_UNAUTHORIZED));
    }

    private boolean isBearerType(String authorization) {
        return authorization.startsWith(BEARER_PREFIX);
    }


    private boolean isValidateExpire(String jwtToken) {
        Date expiration = Jwts.parserBuilder().setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(jwtToken)
            .getBody()
            .getExpiration();
        return expiration.before(new Date());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
