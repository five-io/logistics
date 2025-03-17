package com.msa.fiveio.slack.application.usecase;

import com.msa.fiveio.common.config.JpaAuditingConfig;
import com.msa.fiveio.slack.infrastructure.exception.BusinessLogicException;
import com.msa.fiveio.slack.model.entity.Slacks;
import com.msa.fiveio.slack.model.repository.SlacksQueryRepository;
import com.msa.fiveio.slack.model.repository.SlacksRepository;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import com.msa.fiveio.slack.presentation.mapper.SlacksMapper;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@RefreshScope
public class SlacksServiceImpl implements SlacksService {

	private final SlacksRepository slacksRepository;
	private final SlacksQueryRepository slacksQueryRepository;
	private final MessageSource messageSource;

	@Override
	public SlacksCreateResponseDto createSlack(SlacksCreateRequestDto slacksCreateRequestDto) {

			Slacks newSlacks = SlacksMapper.slacksCreateRequestDtoToEntity(slacksCreateRequestDto);
			Slacks createSlacks = slacksRepository.save(newSlacks);
			return SlacksMapper.entityToCreateResponseDto(createSlacks);
	}

	@Override
	@SQLRestriction("deleted_at IS NULL")
	@Transactional(readOnly = true)
	public SlacksReadResponseDto readSlack(Integer page, Integer size, String orderby, String sort) {

		PageRequest pageable = JpaAuditingConfig.getNormalPageable(page, size, orderby, sort);
		Page<Slacks> slacksPage = slacksQueryRepository.findSlacksList(pageable);

		return SlacksMapper.pageToReadResponseDto(slacksPage);
	}

	@SQLRestriction("deleted_at IS NULL")
	@Transactional
	@Override
	public SlacksSearchResponseDto searchSlack(UUID id, Integer page, Integer size, String orderby, String sort) {

		PageRequest pageable = JpaAuditingConfig.getNormalPageable(page, size, orderby, sort);
		Page<Slacks> slacksSearchPage = slacksQueryRepository.findSlacksSearchList(pageable, id);

		return SlacksMapper.pageToSearchResponseDto(slacksSearchPage);
	}

	@Override
	@Transactional
	public SlacksUpdateResponseDto updateSlack(UUID id, SlacksUpdateRequestDto slacksUpdateRequestDto) {
		String message = slacksUpdateRequestDto.getMessage();
		LocalDateTime deliveryTime = slacksUpdateRequestDto.getDeliveryTime();

		Slacks slacks = slacksRepository.findById(id).orElseThrow(()->
			new BusinessLogicException(messageSource.getMessage("api.call.client-error", null, Locale.KOREA)));

		slacks.update(message, deliveryTime);

		return SlacksMapper.entityToUpdateResponseDto(slacks);
	}

	@Override
	public SlacksDeleteResponseDto deleteSlack(UUID id) {
		Slacks slacks = slacksRepository.findById(id).orElseThrow(()->
			new BusinessLogicException(messageSource.getMessage("api.call.client-error", null, Locale.KOREA)));

		return SlacksMapper.entityToDeleteResponseDto(slacks);
	}

}
