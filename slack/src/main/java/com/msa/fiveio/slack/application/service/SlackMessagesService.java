package com.msa.fiveio.slack.application.service;

import com.msa.fiveio.slack.model.entity.SlackMessages;
import com.msa.fiveio.slack.model.repository.SlackMessagesRepository;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesCreateResponseDto;
import com.msa.fiveio.slack.presentation.mapper.SlackMessagesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SlackMessagesService {

	private final SlackMessagesRepository slackMessagesRepository;

	@Transactional
	public SlackMessagesCreateResponseDto createMessages(
		SlackMessagesCreateRequestDto slackMessagesCreateRequestDto) {

		SlackMessages newSlackMessages = SlackMessagesMapper.slackMessagesSaveRequestDtoToEntity(
			slackMessagesCreateRequestDto);

		SlackMessages createSlackMessages = slackMessagesRepository.save(newSlackMessages);
		return SlackMessagesMapper.entityToSlackMessagesSaveResponseDto(createSlackMessages);
	}
}
