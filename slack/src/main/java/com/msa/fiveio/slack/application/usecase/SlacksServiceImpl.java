package com.msa.fiveio.slack.application.usecase;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.SlackErrorCode;
import com.msa.fiveio.slack.infrastructure.client.AiClient;
import com.msa.fiveio.slack.infrastructure.client.SlackClient;
import com.msa.fiveio.slack.model.entity.Slacks;
import com.msa.fiveio.slack.model.repository.SlacksQueryRepository;
import com.msa.fiveio.slack.model.repository.SlacksRepository;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSendRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import com.msa.fiveio.slack.presentation.mapper.SlacksMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SlacksServiceImpl implements SlacksService {

	private final AiClient aiClient;
	private final SlackClient slackClient;
	private final SlacksRepository slacksRepository;
	private final SlacksQueryRepository slacksQueryRepository;

	@Override
	public SlacksCreateResponseDto createSlack(SlacksCreateRequestDto slacksCreateRequestDto) {

		String message = aiClient.createAis(slacksCreateRequestDto);

		SlacksSendRequestDto slacksSendRequestDto = SlacksMapper.stringToSendRequestDto(message);
		slackClient.sendSlack(slacksSendRequestDto);

		Slacks newSlacks = SlacksMapper.slacksCreateRequestDtoToEntity(slacksCreateRequestDto,
			message);
		Slacks createSlacks = slacksRepository.save(newSlacks);

		return SlacksMapper.entityToCreateResponseDto(createSlacks);
	}

	@Override
	@SQLRestriction("deleted_at IS NULL")
	@Transactional(readOnly = true)
	public SlacksReadResponseDto readSlack(Pageable pageable) {

		Page<Slacks> slacksPage = slacksQueryRepository.findSlacksList(pageable);

		return SlacksMapper.pageToReadResponseDto(slacksPage);
	}

	@SQLRestriction("deleted_at IS NULL")
	@Transactional
	@Override
	public SlacksSearchResponseDto searchSlack(UUID id, Pageable pageable) {

		Page<Slacks> slacksSearchPage = slacksQueryRepository.findSlacksSearchList(pageable, id);

		return SlacksMapper.pageToSearchResponseDto(slacksSearchPage);
	}

	@Override
	@Transactional
	public SlacksUpdateResponseDto updateSlack(UUID id,
		SlacksUpdateRequestDto slacksUpdateRequestDto) {
		String message = slacksUpdateRequestDto.getMessage();

		Slacks slacks = slacksRepository.findById(id).orElseThrow(() ->
			new CustomException(SlackErrorCode.SLACKS_NOT_FOUND));

		slacks.update(message);

		return SlacksMapper.entityToUpdateResponseDto(slacks);
	}

	@Override
	public SlacksDeleteResponseDto deleteSlack(UUID id) {
		Slacks slacks = slacksRepository.findById(id).orElseThrow(() ->
			new CustomException(SlackErrorCode.SLACKS_NOT_FOUND));

		return SlacksMapper.entityToDeleteResponseDto(slacks);
	}
}
