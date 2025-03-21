package com.msa.fiveio.ai.presentation.controller;

import com.msa.fiveio.ai.infrastructure.client.GeminiClient;
import com.msa.fiveio.ai.presentation.dto.AisCreateRequestDto;
import com.msa.fiveio.ai.presentation.dto.GeminiRequestDto;
import com.msa.fiveio.ai.presentation.dto.GeminiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ais")
@RequiredArgsConstructor
@Tag(name = "Ai Service", description = "Ai 서비스 API")
public class AisController {

	private final GeminiClient geminiClient;

	@Operation(summary = "Ai 등록", description = "Ai 등록 api 입니다.")
	@PostMapping
	public String createAis(@RequestBody AisCreateRequestDto aisCreateRequestDto) {
		String requestBodyString = convertDtoToString(aisCreateRequestDto);

		GeminiRequestDto geminiRequestDto = new GeminiRequestDto(requestBodyString);
		GeminiResponseDto geminiResponseDto = geminiClient.createAis(geminiRequestDto);

		String message = "";
		for (GeminiResponseDto.Candidate candidate : geminiResponseDto.getCandidates()) {
			for (GeminiResponseDto.Parts part : candidate.getContent().getParts()) {
				message = part.getText();
			}
		}

		return message;
	}

	private String convertDtoToString(AisCreateRequestDto aisCreateRequestDto) {
		return String.format(
			"출발허브: %s, 경유지: %s, 도착허브: %s, 배송지: %s, 수령인: %s, 전화번호: %s, 발송담당자: %s, 상품명: %s, 상품수량: %s, 요청사항: %s. 위 내용으로 일정한 형식으로 발송시한 작성해줘. 50자 이내로",
			aisCreateRequestDto.getDepartHubName(),
			aisCreateRequestDto.getTransitPoint(),
			aisCreateRequestDto.getArriveHubName(),
			aisCreateRequestDto.getDeliveryAddress(),
			aisCreateRequestDto.getRecipientName(),
			aisCreateRequestDto.getRecipientSlackId(),
			aisCreateRequestDto.getCompanyDeliveryManager(),
			aisCreateRequestDto.getProductName(),
			aisCreateRequestDto.getProductQuantity(),
			aisCreateRequestDto.getRequestNotes()
		);
	}
}
