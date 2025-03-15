package com.msa.fiveio.slack.application.service;

import com.msa.fiveio.slack.infrastructure.exception.BusinessLogicException;
import com.msa.fiveio.slack.model.entity.Slacks;
import com.msa.fiveio.slack.model.repository.SlacksRepository;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import com.msa.fiveio.slack.presentation.mapper.SlacksMapper;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SlacksService {

	private final SlacksRepository slacksRepository;
	private final MessageSource messageSource;

	@Transactional
	public SlacksCreateResponseDto createMessages(
		SlacksCreateRequestDto slacksCreateRequestDto) {

		Slacks newSlacks = SlacksMapper.slacksCreateRequestDtoToEntity(
			slacksCreateRequestDto);

		Slacks createSlacks = slacksRepository.save(newSlacks);
		return SlacksMapper.entityToCreateResponseDto(createSlacks);
	}

	@Transactional
	public SlacksUpdateResponseDto updateMessages(UUID id, SlacksUpdateRequestDto slacksUpdateRequestDto) {
		String message = slacksUpdateRequestDto.getMessage();
		LocalDateTime deliveryTime = slacksUpdateRequestDto.getDeliveryTime();

		Slacks slacks = slacksRepository.findById(id).orElseThrow(()->
			new BusinessLogicException(messageSource.getMessage("api.call.client-error", null, Locale.KOREA)));

		slacks.update(message, deliveryTime);

		return SlacksMapper.entityToUpdateResponseDto(slacks);
	}

}
