package com.msa.fiveio.slack.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksSendRequestDto {

	private String text;
}
