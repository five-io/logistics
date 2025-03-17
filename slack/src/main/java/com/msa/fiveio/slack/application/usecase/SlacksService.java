package com.msa.fiveio.slack.application.usecase;

import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public interface SlacksService {

	SlacksCreateResponseDto createSlack(SlacksCreateRequestDto slacksCreateRequestDto);

	@Transactional(readOnly = true)
	SlacksReadResponseDto readSlack(Integer page, Integer size, String orderby, String sort);
	SlacksSearchResponseDto searchSlack(UUID id, Integer page, Integer size, String orderby, String sort);

	SlacksUpdateResponseDto updateSlack(UUID id, SlacksUpdateRequestDto slacksUpdateRequestDto);
	SlacksDeleteResponseDto deleteSlack(UUID slackId);
}
