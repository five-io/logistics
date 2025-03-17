package com.msa.fiveio.slack.presentation.controller;

import com.msa.fiveio.slack.application.facade.SlacksFacade;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slacks")
@RequiredArgsConstructor
@Tag(name = "Slack Service", description = "슬랙 서비스 API")
public class SlacksController {
	private final SlacksFacade slacksFacade;

	@Operation(summary = "Slack 등록", description = "Slack 등록 api 입니다.")
	@PostMapping
	public ResponseEntity<SlacksCreateResponseDto> createSlack(@RequestBody SlacksCreateRequestDto slacksCreateRequestDto) {
		SlacksCreateResponseDto slacksCreateResponseDto = slacksFacade.createSlack(
			slacksCreateRequestDto);

		return ResponseEntity.ok(slacksCreateResponseDto);
	}

	@Operation(summary = "Slack 목록 조회", description = "Slack 목록 조회 api 입니다.")
	@GetMapping
	public ResponseEntity<SlacksReadResponseDto> readSlack(SlacksReadRequestDto slacksReadRequestDto) {
		SlacksReadResponseDto slacksReadResponseDto = slacksFacade.readSlack(
			slacksReadRequestDto.getPage(),
			slacksReadRequestDto.getSize(),
			slacksReadRequestDto.getOrderby(),
			slacksReadRequestDto.getSort()
		);

		return ResponseEntity.ok(slacksReadResponseDto);
	}

	@Operation(summary = "Slack 검색", description = "Slack 검색 api 입니다.")
	@GetMapping("/search/{id}")
	public ResponseEntity<SlacksSearchResponseDto> searchSlack(@PathVariable UUID id, SlacksSearchRequestDto slacksSearchRequestDto) {
		SlacksSearchResponseDto slacksSearchResponseDto = slacksFacade.searchSlack(
			id,
			slacksSearchRequestDto.getPage(),
			slacksSearchRequestDto.getSize(),
			slacksSearchRequestDto.getOrderby(),
			slacksSearchRequestDto.getSort()
		);

		return ResponseEntity.ok(slacksSearchResponseDto);
	}

	@Operation(summary = "Slack 수정", description = "Slack 수정 api 입니다.")
	@PatchMapping("/update/{id}")
	public ResponseEntity<SlacksUpdateResponseDto> updateSlack(@PathVariable UUID id, @RequestBody SlacksUpdateRequestDto slacksUpdateRequestDto) {
		SlacksUpdateResponseDto slacksUpdateResponseDto = slacksFacade.updateSlack(id,
			slacksUpdateRequestDto);

		return ResponseEntity.ok(slacksUpdateResponseDto);
	}

	@Operation(summary = "Slack 삭제", description = "Slack 삭제 api 입니다.")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SlacksDeleteResponseDto> deleteSlack(@PathVariable UUID id) {
		SlacksDeleteResponseDto slacksDeleteResponseDto = slacksFacade.deleteSlack(id);

		return ResponseEntity.ok(slacksDeleteResponseDto);
	}

}
