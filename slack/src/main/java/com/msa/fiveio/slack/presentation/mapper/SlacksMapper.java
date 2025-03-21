package com.msa.fiveio.slack.presentation.mapper;

import com.msa.fiveio.slack.model.entity.Slacks;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksDeleteResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksReadResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchResponseDto;
import com.msa.fiveio.slack.presentation.dto.SlacksSendRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class SlacksMapper {

	public static Slacks slacksCreateRequestDtoToEntity(
		SlacksCreateRequestDto slacksCreateRequestDto, String message) {
		return Slacks.builder()
			.orderId(slacksCreateRequestDto.getOrderId())
			.departHubName(slacksCreateRequestDto.getDepartHubName())
			.transitPoint(slacksCreateRequestDto.getTransitPoint())
			.arriveHubName(slacksCreateRequestDto.getArriveHubName())
			.deliveryAddress(slacksCreateRequestDto.getDeliveryAddress())
			.requestNotes(slacksCreateRequestDto.getRequestNotes())
			.recipientName(slacksCreateRequestDto.getRecipientName())
			.recipientSlackId(slacksCreateRequestDto.getRecipientSlackId())
			.companyDeliveryManager(slacksCreateRequestDto.getCompanyDeliveryManager())
			.productName(slacksCreateRequestDto.getProductName())
			.productQuantity(slacksCreateRequestDto.getProductQuantity())
			.message(message)
			.sendStatus(slacksCreateRequestDto.getSendStatus())
			.build();
	}

	public static SlacksCreateResponseDto entityToCreateResponseDto(Slacks slacks) {
		return SlacksCreateResponseDto.builder()
			.message(slacks.getMessage())
			.build();
	}


	public static SlacksSendRequestDto stringToSendRequestDto(String text) {
		return SlacksSendRequestDto.builder()
			.text(text)
			.build();
	}

	public static SlacksReadResponseDto pageToReadResponseDto(Page<Slacks> page) {
		SlacksReadResponseDto.SlacksReadResponseDtoBuilder builder = SlacksReadResponseDto.builder();
		builder = builder
			.totalContents(page.getTotalElements())
			.size(page.getSize())
			.currentPage(page.getNumber() + 1);
		List<SlacksReadResponseDto.SlacksDto> slacksDtoList = page.getContent().stream()
			.map(SlacksMapper::entityToSlacksReadDto)
			.collect(Collectors.toList());
		builder = builder.slackList(slacksDtoList);
		return builder.build();
	}

	public static SlacksReadResponseDto.SlacksDto entityToSlacksReadDto(Slacks slacks) {
		return SlacksReadResponseDto.SlacksDto.builder()
			.slackId(slacks.getId())
			.orderId(slacks.getOrderId())
			.build();
	}

	public static SlacksSearchResponseDto pageToSearchResponseDto(Page<Slacks> page) {
		SlacksSearchResponseDto.SlacksSearchResponseDtoBuilder builder = SlacksSearchResponseDto.builder();
		builder = builder
			.totalContents(page.getTotalElements())
			.size(page.getSize())
			.currentPage(page.getNumber() + 1);
		List<SlacksSearchResponseDto.SlacksDto> slacksDtoList = page.getContent().stream()
			.map(SlacksMapper::entityToSlacksSearchDto)
			.collect(Collectors.toList());

		return builder
			.slackList(slacksDtoList)
			.build();
	}

	public static SlacksSearchResponseDto.SlacksDto entityToSlacksSearchDto(Slacks slacks) {
		return SlacksSearchResponseDto.SlacksDto.builder()
			.slackId(slacks.getId())
			.orderId(slacks.getOrderId())
			.build();
	}

	public static SlacksDeleteResponseDto entityToDeleteResponseDto(Slacks slacks) {
		return SlacksDeleteResponseDto.builder()
			.slackId(slacks.getId())
			.build();
	}
}
