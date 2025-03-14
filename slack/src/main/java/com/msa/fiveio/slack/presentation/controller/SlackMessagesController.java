package com.msa.fiveio.slack.presentation.controller;

import com.msa.fiveio.slack.application.service.SlackMessagesService;
import com.msa.fiveio.slack.model.entity.SlackMessages;
import com.msa.fiveio.slack.presentation.dto.SlackMessagesRequestDto;
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
	public ResponseEntity<SlackMessages> createMessage(@RequestBody SlackMessagesRequestDto slackMessagesRequestDto) {
		SlackMessages slackMessage = slackMessageService.createMessages(slackMessagesRequestDto);
		return ResponseEntity.ok(slackMessage);
	}

}
