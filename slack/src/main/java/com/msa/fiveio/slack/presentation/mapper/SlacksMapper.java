package com.msa.fiveio.slack.presentation.mapper;

import com.msa.fiveio.slack.model.entity.Slacks;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksUpdateResponseDto;

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

	public static SlacksUpdateResponseDto entityToUpdateResponseDto(Slacks slacks) {
		return SlacksUpdateResponseDto.builder()
			.userId(slacks.getUserId())
			.receiveId(slacks.getReceiveId())
			.orderId(slacks.getOrderId())
			.message(slacks.getMessage())
			.deliveryTime(slacks.getDeliveryTime())
			.build();
	}
}
