package com.msa.fiveio.slack.presentation.mapper;


import com.msa.fiveio.slack.model.entity.SlackMessages;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesSaveRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesSaveResponseDto;
import java.time.LocalDateTime;

public class SlackMessagesMapper {

	public static SlackMessages slackMessagesSaveRequestDtoToEntity(
		SlackMessagesSaveRequestDto slackMessagesSaveRequestDto) {
		return SlackMessages.builder()
			.userId(slackMessagesSaveRequestDto.getUserId())
			.receiveId(slackMessagesSaveRequestDto.getReceiveId())
			.orderId(slackMessagesSaveRequestDto.getOrderId())
			.message(slackMessagesSaveRequestDto.getMessage())
			.deliveryTime(LocalDateTime.now())
			.build();
	}

	public static SlackMessagesSaveResponseDto entityToSlackMessagesSaveResponseDto(SlackMessages slackMessages) {
		return SlackMessagesSaveResponseDto.builder()
			.slackId(slackMessages.getId())
			.build();
	}
}
