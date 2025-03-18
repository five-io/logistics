package com.msa.fiveio.slack.application.usecase;

import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface SlacksService {

	SlacksCreateResponseDto createSlack(SlacksCreateRequestDto slacksCreateRequestDto);

	@Transactional(readOnly = true)
	SlacksReadResponseDto readSlack(Pageable pageable);
	SlacksSearchResponseDto searchSlack(UUID id, Pageable pageable);

	SlacksUpdateResponseDto updateSlack(UUID id, SlacksUpdateRequestDto slacksUpdateRequestDto);
	SlacksDeleteResponseDto deleteSlack(UUID slackId);
}
