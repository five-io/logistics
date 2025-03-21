package com.msa.fiveio.slack.application.facade;

import com.msa.fiveio.slack.application.usecase.SlacksService;
import com.msa.fiveio.slack.model.entity.SendStatus;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SlacksFacadeImpl implements SlacksFacade {

	private final SlacksService slacksService;

	@Override
	public SlacksCreateResponseDto createSlack(SlacksCreateRequestDto slacksCreateRequestDto) {
		return slacksService.createSlack(slacksCreateRequestDto);
	}

	@Override
	public SlacksReadResponseDto readSlack(Pageable pageable) {
		return slacksService.readSlack(pageable);
	}

	@Override
	public SlacksSearchResponseDto searchSlack(UUID id, Pageable pageable) {
		return slacksService.searchSlack(id, pageable);
	}

	@Override
	public String updateStatus(UUID id, String status) {
		return slacksService.updateStatus(id, status);
	}

	@Override
	public SlacksDeleteResponseDto deleteSlack(UUID id) {
		return slacksService.deleteSlack(id);
	}
}
