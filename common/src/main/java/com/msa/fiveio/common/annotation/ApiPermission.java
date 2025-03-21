package com.msa.fiveio.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target: 어노테이션이 사용될 위치
 * @Retention: 어느 시점까지 어노테이션의 메모리를 가져갈지 설정
 */
@Target({
    ElementType.ANNOTATION_TYPE,
    ElementType.METHOD,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiPermission {

    Role[] roles();

    enum Role {
        ROLE_MASTER,
        ROLE_HUB_MANAGER,
        ROLE_DELIVERY_MANAGER,
        ROLE_COMPANY_MANAGER;
    }

}
