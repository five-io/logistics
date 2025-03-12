package com.spring_cloud.client.gateway;

import com.msa.fiveio.gateway.exception.AccessTokenExpiredException;
import com.msa.fiveio.gateway.exception.BaseException;
import com.msa.fiveio.gateway.exception.NotExistsAuthorization;
import com.msa.fiveio.gateway.jwt.RedisService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.core.HttpHeaders;
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
public class CustomPreFilter  implements GlobalFilter, Ordered {

    @Value("${service.jwt.secret-key}")
    private String secretKey;
    private static final String AUTH_TYPE = "Bearer ";

    private final RedisService redisService;


    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();


        if(path.equals("/users/signIn")||path.equals("/users/signUp")){

            return chain.filter(exchange);
        }

        try {
            //토큰가져오기
            String authorization = getJwtFromHeader(exchange);

            String jwtToken = parseAuthorizationToken(authorization);

            //검증
            if (!StringUtils.hasText(jwtToken) || !validateToken(jwtToken)) {
                throw new NotExistsAuthorization();
            }

            //유효기간확인
            if (isValidateExpire(jwtToken)) {
                throw new AccessTokenExpiredException();
            }

            // 블랙리스트 확인
            if (redisService.isBlacklisted(authorization)) {
                throw new AccessTokenExpiredException();
            }





            return chain.filter(exchange);





        } catch (BaseException e) {
            return sendErrorResponse(exchange, e.getErrorCode(), e);
        } catch (Exception e) {
            e.printStackTrace();
            return sendErrorResponse(exchange, 999, e);
        }


    }



    private Mono<Void> sendErrorResponse(ServerWebExchange exchange, int errorCode, Exception e) {
        String errorBody = "{\"code\": " + errorCode + ", \"message\": \"" + e.getMessage() + "\"}";

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer buffer = response.bufferFactory().wrap(errorBody.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }


    private String parseAuthorizationToken(String authorization) {
        return authorization.replace(AUTH_TYPE, "").trim();
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
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
    }


    public String getJwtFromHeader(ServerWebExchange exchange) {
        List<String> authorizations = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (authorizations == null || authorizations.isEmpty()) {
            throw new NotExistsAuthorization();
        }
        return authorizations.stream()
            .filter(this::isBearerType)
            .findFirst()
            .orElseThrow(NotExistsAuthorization::new);
    }

    private boolean isBearerType(String authorization) {
        return authorization.startsWith(AUTH_TYPE);
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
