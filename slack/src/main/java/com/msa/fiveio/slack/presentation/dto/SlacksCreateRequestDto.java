package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksCreateRequestDto {
	@NotNull(message = "주문 ID는 필수 입력값입니다.")
	@JsonProperty("order-id")
	private UUID orderId;

	@NotNull(message = "출발 허브명은 필수 입력값입니다.")
	@Size(min = 1, max = 100, message = "출발 허브명은 1~100자로 입력해야 합니다.")
	@JsonProperty("depart-hub-name")
	private String departHubName;

	@NotNull(message = "경유지는 필수 입력값입니다.")
	@Size(min = 1, max = 100, message = "경유지는 1~100자로 입력해야 합니다.")
	@JsonProperty("transit-point")
	private String transitPoint;

	@NotNull(message = "도착 허브명은 필수 입력값입니다.")
	@Size(min = 1, max = 100, message = "도착 허브명은 1~100자로 입력해야 합니다.")
	@JsonProperty("arrive-hub-name")
	private String arriveHubName;

	@NotNull(message = "배송지는 필수 입력값입니다.")
	@Size(min = 1, max = 255, message = "배송지는 1~255자로 입력해야 합니다.")
	@JsonProperty("delivery-address")
	private String deliveryAddress;

	@NotNull(message = "수령인은 필수 입력값입니다.")
	@Size(min = 1, max = 100, message = "수령인은 1~100자로 입력해야 합니다.")
	@JsonProperty("recipient-name")
	private String recipientName;

	@NotNull(message = "수신 슬랙 ID는 필수 입력값입니다.")
	@Size(min = 1, max = 100, message = "수신 슬랙 ID는 1~100자로 입력해야 합니다.")
	@JsonProperty("recipient-slack-id")
	private String recipientSlackId;

	@NotNull(message = "업체 배송 담당자는 필수 입력값입니다.")
	@Size(min = 1, max = 100, message = "업체 배송 담당자는 1~100자로 입력해야 합니다.")
	@JsonProperty("company-delivery-manager")
	private String companyDeliveryManager;

	@NotNull(message = "상품명은 필수 입력값입니다.")
	@Size(min = 1, max = 100, message = "상품명은 1~100자로 입력해야 합니다.")
	@JsonProperty("product-name")
	private String productName;

	@NotNull(message = "상품 수량은 필수 입력값입니다.")
	@Min(value = 1, message = "상품 수량은 1 이상이어야 합니다.")
	@JsonProperty("product-quantity")
	private Long productQuantity;

	@NotNull(message = "요청 사항은 필수 입력값입니다.")
	@Size(min = 1, max = 255, message = "요청 사항은 1~255자로 입력해야 합니다.")
	@JsonProperty("request-notes")
	private String requestNotes;

	private String message;

}
