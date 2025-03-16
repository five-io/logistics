package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Builder;

@Builder
public class SlacksDeleteResponseDto {
	@JsonProperty("slack-id")
	private UUID slackId;
}
