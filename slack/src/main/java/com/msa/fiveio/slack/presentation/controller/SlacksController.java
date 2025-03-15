package com.msa.fiveio.slack.presentation.controller;

import com.msa.fiveio.slack.application.service.SlacksService;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slacks")
@RequiredArgsConstructor
public class SlacksController {
	private final SlacksService slackMessageService;

	@PostMapping
	public ResponseEntity<SlacksCreateResponseDto> createMessage(@RequestBody SlacksCreateRequestDto slacksCreateRequestDto) {
		SlacksCreateResponseDto slacksCreateResponseDto = slackMessageService.createMessages(
			slacksCreateRequestDto);

		return ResponseEntity.ok(slacksCreateResponseDto);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<SlacksUpdateResponseDto> updateMessage(@PathVariable UUID id, @RequestBody SlacksUpdateRequestDto slacksUpdateRequestDto) {
		SlacksUpdateResponseDto slacksUpdateResponseDto = slackMessageService.updateMessages(id,
			slacksUpdateRequestDto);

		return ResponseEntity.ok(slacksUpdateResponseDto);
	}

}
