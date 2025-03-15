package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksUpdateRequestDto {

	private String message;

	@JsonProperty("delivery-time")
	private LocalDateTime deliveryTime;
}
