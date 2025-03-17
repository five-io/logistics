package com.msa.fiveio.slack.application.facade;

import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import java.util.UUID;

public interface SlacksFacade {
	SlacksCreateResponseDto createSlack(SlacksCreateRequestDto slacksCreateRequestDto);

	SlacksReadResponseDto readSlack(Integer page, Integer size, String orderby, String sort);
	SlacksSearchResponseDto searchSlack(UUID id, Integer page, Integer size, String orderby, String sort);

	SlacksUpdateResponseDto updateSlack(UUID id, SlacksUpdateRequestDto slacksUpdateRequestDto);
	SlacksDeleteResponseDto deleteSlack(UUID id);
}
