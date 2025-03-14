package com.msa.fiveio.slack.application.service;

import com.msa.fiveio.slack.model.entity.SlackMessages;
import com.msa.fiveio.slack.model.repository.SlackMessagesRepository;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesRequestDto;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SlackMessagesService {
	private final SlackMessagesRepository slackMessagesRepository;

	@Transactional
	public SlackMessages createMessages(SlackMessagesRequestDto slackMessagesRequestDto) {

		SlackMessages slackMessages = SlackMessages.builder()
			.userId(slackMessagesRequestDto.getUserId())
			.receiveId(slackMessagesRequestDto.getReceiveId())
			.orderId(slackMessagesRequestDto.getOrderId())
			.message(slackMessagesRequestDto.getMessage())
			.deliveryTime(LocalDateTime.now())
			.build();

		return slackMessagesRepository.save(slackMessages);
	}
}
