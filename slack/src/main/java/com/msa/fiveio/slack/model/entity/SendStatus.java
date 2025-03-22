package com.msa.fiveio.slack.model.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SendStatus {
	SEND_SUCCESS("발신 성공"),
	SEND_FAILURE("발신 실패"),
	SEND_ERROR("발신 에러"),
	SEND_TIMEOUT("발신 시간 초과");

	private final String description;

	public static List<String> getAllowedStatuses() {
		return Arrays.stream(values())
			.map(Enum::name)
			.collect(Collectors.toList());
	}
}
