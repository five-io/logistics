package com.msa.fiveio.slack.presentation.mapper;

import com.msa.fiveio.slack.model.entity.Slacks;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class SlacksMapper {

	public static Slacks slacksCreateRequestDtoToEntity(
		SlacksCreateRequestDto slacksCreateRequestDto) {
		return Slacks.builder()
			.userId(slacksCreateRequestDto.getUserId())
			.receiveId(slacksCreateRequestDto.getReceiveId())
			.orderId(slacksCreateRequestDto.getOrderId())
			.message(slacksCreateRequestDto.getMessage())
			.deliveryTime(slacksCreateRequestDto.getDeliveryTime())
			.build();
	}

	public static SlacksCreateResponseDto entityToCreateResponseDto(Slacks slacks) {
		return SlacksCreateResponseDto.builder()
			.slackId(slacks.getId())
			.build();
	}

	public static SlacksReadResponseDto pageToReadResponseDto(Page<Slacks> page) {
		SlacksReadResponseDto.SlacksReadResponseDtoBuilder builder = SlacksReadResponseDto.builder();
		builder = builder
			.totalContents(page.getTotalElements())
			.size(page.getSize())
			.currentPage(page.getNumber() + 1);
		List<SlacksReadResponseDto.SlacksDto> slacksDtoList = page.getContent().stream()
			.map(SlacksMapper::entityToSlacksDtoElement)
			.collect(Collectors.toList());
		builder = builder.slackList(slacksDtoList);
		return builder.build();
	}

	public static SlacksReadResponseDto.SlacksDto entityToSlacksDtoElement(Slacks slacks) {
		return SlacksReadResponseDto.SlacksDto.builder()
			.slackId(slacks.getId())
			.userId(slacks.getUserId())
			.receiveId(slacks.getReceiveId())
			.orderId(slacks.getOrderId())
			.message(slacks.getMessage())
			.deliveryTime(slacks.getDeliveryTime())
			.build();
	}

	public static SlacksUpdateResponseDto entityToUpdateResponseDto(Slacks slacks) {
		return SlacksUpdateResponseDto.builder()
			.userId(slacks.getUserId())
			.receiveId(slacks.getReceiveId())
			.orderId(slacks.getOrderId())
			.message(slacks.getMessage())
			.deliveryTime(slacks.getDeliveryTime())
			.build();
	}

	public static SlacksDeleteResponseDto entityToDeleteResponseDto(Slacks slacks) {
		return SlacksDeleteResponseDto.builder()
			.slackId(slacks.getId())
			.build();
	}
}
