package com.msa.fiveio.slack.presentation.controller;

import com.msa.fiveio.slack.application.facade.SlacksFacade;
import com.msa.fiveio.slack.model.entity.SendStatus;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slacks")
@RequiredArgsConstructor
@Tag(name = "Slack Service", description = "슬랙 서비스 API")
public class SlacksController {

	private final SlacksFacade slacksFacade;

	@Operation(summary = "Slack 등록", description = "Slack 등록 api 입니다.")
	@PostMapping
	public ResponseEntity<SlacksCreateResponseDto> createSlack(
		@RequestBody SlacksCreateRequestDto slacksCreateRequestDto
	) {

		SlacksCreateResponseDto slacksCreateResponseDto = slacksFacade.createSlack(
			slacksCreateRequestDto);
		slacksFacade.updateStatus(slacksCreateRequestDto.getOrderId(),
			slacksCreateRequestDto.getSendStatus().name());
		return ResponseEntity.ok(slacksCreateResponseDto);
	}

	@Operation(summary = "Slack 목록 조회", description = "Slack 목록 조회 api 입니다.")
	@GetMapping
	public ResponseEntity<SlacksReadResponseDto> readSlack(Pageable pageable) {
		SlacksReadResponseDto slacksReadResponseDto = slacksFacade.readSlack(pageable);

		return ResponseEntity.ok(slacksReadResponseDto);
	}

	@Operation(summary = "Slack 검색", description = "Slack 검색 api 입니다.")
	@GetMapping("/search/{id}")
	public ResponseEntity<SlacksSearchResponseDto> searchSlack(@PathVariable UUID id,
		Pageable pageable) {
		SlacksSearchResponseDto slacksSearchResponseDto = slacksFacade.searchSlack(id, pageable);

		return ResponseEntity.ok(slacksSearchResponseDto);
	}

	@Operation(summary = "Slack 상태 변경", description = "Slack 상태 변경 api 입니다.")
	@PatchMapping("/{id}/status")
	public ResponseEntity<String> updateStatus(SlacksUpdateRequestDto slacksUpdateRequestDto
	) {
		try {
			return ResponseEntity.ok(slacksFacade.updateStatus(slacksUpdateRequestDto.getOrderId(),
				slacksUpdateRequestDto.getSendStatus().name()));
		} catch (IllegalArgumentException e) {
			String errorMessage = "잘못된 상태 값입니다. \n허용된 값: "
				+ String.join(", ", SendStatus.getAllowedStatuses()) + " 입니다.";
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}

	@Operation(summary = "Slack 삭제", description = "Slack 삭제 api 입니다.")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SlacksDeleteResponseDto> deleteSlack(@PathVariable UUID id) {
		SlacksDeleteResponseDto slacksDeleteResponseDto = slacksFacade.deleteSlack(id);

		return ResponseEntity.ok(slacksDeleteResponseDto);
	}
}
