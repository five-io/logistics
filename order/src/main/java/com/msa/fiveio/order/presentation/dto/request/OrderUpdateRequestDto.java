package com.msa.fiveio.order.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderUpdateRequestDto {

    private final Long quantity;
    private final String requestNotes;
}
