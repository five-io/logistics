package com.msa.fiveio.slack.application.facade;

import com.msa.fiveio.slack.application.usecase.SlacksService;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
	public SlacksReadResponseDto readSlack(Integer page, Integer size, String orderby, String sort) {
		return slacksService.readSlack(page, size, orderby, sort);
	}

	@Override
	public SlacksSearchResponseDto searchSlack(UUID id, Integer page, Integer size, String orderby, String sort) {
		return slacksService.searchSlack(id, page, size, orderby, sort);
	}

	@Override
	public SlacksUpdateResponseDto updateSlack(UUID id, SlacksUpdateRequestDto slacksUpdateRequestDto) {
		return slacksService.updateSlack(id, slacksUpdateRequestDto);
	}

	@Override
	public SlacksDeleteResponseDto deleteSlack(UUID id) {
		return slacksService.deleteSlack(id);
	}
}
