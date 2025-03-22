package com.msa.fiveio.delivery.infrastructure.client.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private Long id;
    private String userName;
}
