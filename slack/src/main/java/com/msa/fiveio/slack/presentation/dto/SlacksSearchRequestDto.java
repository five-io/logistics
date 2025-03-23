package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.fiveio.slack.model.entity.SendStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksSearchRequestDto {

	@Getter
	@Builder
	public static class SlacksDto {

		@JsonProperty("slack-id")
		private UUID slackId;

		@JsonProperty("order-id")
		private UUID orderId;

		@Size(min = 1, max = 100, message = "출발 허브명은 1~100자로 입력해야 합니다.")
		@JsonProperty("depart-hub-name")
		private String departHubName;

		@Size(min = 1, max = 100, message = "경유지는 1~100자로 입력해야 합니다.")
		@JsonProperty("transit-point")
		private String transitPoint;

		@Size(min = 1, max = 100, message = "도착 허브명은 1~100자로 입력해야 합니다.")
		@JsonProperty("arrive-hub-name")
		private String arriveHubName;

		@Size(min = 1, max = 255, message = "배송지는 1~255자로 입력해야 합니다.")
		@JsonProperty("delivery-address")
		private String deliveryAddress;

		@Size(min = 1, max = 100, message = "수령인은 1~100자로 입력해야 합니다.")
		@JsonProperty("recipient-name")
		private String recipientName;

		@Size(min = 1, max = 100, message = "수신 슬랙 ID는 1~100자로 입력해야 합니다.")
		@JsonProperty("recipient-slack-id")
		private String recipientSlackId;

		@Size(min = 1, max = 100, message = "업체 배송 담당자는 1~100자로 입력해야 합니다.")
		@JsonProperty("company-delivery-manager")
		private String companyDeliveryManager;

		@Size(min = 1, max = 100, message = "상품명은 1~100자로 입력해야 합니다.")
		@JsonProperty("product-name")
		private String productName;

		@Min(value = 1, message = "상품 수량은 1 이상이어야 합니다.")
		@JsonProperty("product-quantity")
		private Long productQuantity;

		@Size(min = 1, max = 255, message = "요청 사항은 1~255자로 입력해야 합니다.")
		@JsonProperty("request-notes")
		private String requestNotes;

		@Pattern(regexp = "SEND_SUCCESS|SEND_FAILURE|SEND_ERROR|SEND_TIMEOUT",
			message = "발신 상태는 SEND_SUCCESS, SEND_FAILURE, SEND_ERROR, SEND_TIMEOUT 만 허용합니다.")
		@JsonProperty("send-status")
		private SendStatus sendStatus;

		private String message;
	}
}