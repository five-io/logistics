package com.msa.fiveio.slack.application.usecase;

import static com.msa.fiveio.slack.model.entity.SendStatus.SEND_ERROR;
import static com.msa.fiveio.slack.model.entity.SendStatus.SEND_SUCCESS;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.SlackErrorCode;
import com.msa.fiveio.slack.infrastructure.client.AiClient;
import com.msa.fiveio.slack.infrastructure.client.SlackClient;
import com.msa.fiveio.slack.model.entity.SendStatus;
import com.msa.fiveio.slack.model.entity.Slacks;
import com.msa.fiveio.slack.model.repository.SlacksQueryRepository;
import com.msa.fiveio.slack.model.repository.SlacksRepository;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSendRequestDto;
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

		SendStatus status;
		String message = aiClient.createAis(slacksCreateRequestDto);

		SlacksSendRequestDto slacksSendRequestDto = SlacksMapper.stringToSendRequestDto(message);

		try {
			slackClient.sendSlack(slacksSendRequestDto);
			status = SEND_SUCCESS;
		} catch (Exception e) {
			status = SEND_ERROR;
		}

		Slacks newSlacks = SlacksMapper.slacksCreateRequestDtoToEntity(slacksCreateRequestDto,
			message, status);
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
	public SlacksSearchResponseDto searchSlack(Pageable pageable, SlacksSearchRequestDto.SlacksDto slacksDto) {

		Page<Slacks> slacksSearchPage = slacksQueryRepository.findSlacksSearchList(pageable, slacksDto);

		return SlacksMapper.pageToSearchResponseDto(slacksSearchPage);
	}

	@Transactional
	@Override
	public String updateStatus(UUID orderId, String status) {
		Slacks slacks = slacksRepository.findByOrderId(orderId)
			.orElseThrow(() -> new IllegalArgumentException("Slack not found"));

		SendStatus sendStatus = SendStatus.valueOf(status.toUpperCase());
		slacks.updateStatus(sendStatus);
		return sendStatus.name();
	}

	@Override
	public SlacksDeleteResponseDto deleteSlack(UUID id) {
		Slacks slacks = slacksRepository.findById(id).orElseThrow(() ->
			new CustomException(SlackErrorCode.SLACKS_NOT_FOUND));

		return SlacksMapper.entityToDeleteResponseDto(slacks);
	}
}
