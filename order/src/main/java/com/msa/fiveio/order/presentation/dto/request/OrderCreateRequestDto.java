package com.msa.fiveio.order.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCreateRequestDto {

    @JsonProperty("receiver-company-id")
    private final UUID receiverCompanyId;

    @JsonProperty("product-id")
    private final UUID productId;

    @JsonProperty("quantity")
    private final Long quantity;

    @JsonProperty("request-notes")
    private final String requestNotes;

    @JsonProperty("recipient-name")
    private final String recipientName;

    @JsonProperty("recipient-slack-id")
    private final String recipientSlackId;

}
