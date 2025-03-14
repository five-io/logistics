package com.msa.fiveio.slack.presentation.mapper;


import com.msa.fiveio.slack.model.entity.SlackMessages;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesCreateResponseDto;
import java.time.LocalDateTime;

public class SlackMessagesMapper {

	public static SlackMessages slackMessagesSaveRequestDtoToEntity(
		SlackMessagesCreateRequestDto slackMessagesCreateRequestDto) {
		return SlackMessages.builder()
			.userId(slackMessagesCreateRequestDto.getUserId())
			.receiveId(slackMessagesCreateRequestDto.getReceiveId())
			.orderId(slackMessagesCreateRequestDto.getOrderId())
			.message(slackMessagesCreateRequestDto.getMessage())
			.deliveryTime(LocalDateTime.now())
			.build();
	}

	public static SlackMessagesCreateResponseDto entityToSlackMessagesSaveResponseDto(SlackMessages slackMessages) {
		return SlackMessagesCreateResponseDto.builder()
			.slackId(slackMessages.getId())
			.build();
	}
}
