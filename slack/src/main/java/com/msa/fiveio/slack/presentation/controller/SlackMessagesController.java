package com.msa.fiveio.slack.presentation.controller;

import com.msa.fiveio.slack.application.service.SlackMessagesService;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesSaveRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slack-messages")
@RequiredArgsConstructor
public class SlackMessagesController {
	private final SlackMessagesService slackMessageService;

	@PostMapping
	public ResponseEntity<SlackMessagesSaveResponseDto> createMessage(@RequestBody SlackMessagesSaveRequestDto slackMessagesRequestDto) {
		SlackMessagesSaveResponseDto slackMessagesResponseDto = slackMessageService.createMessages(slackMessagesRequestDto);

		return ResponseEntity.ok(slackMessagesResponseDto);
	}

}
