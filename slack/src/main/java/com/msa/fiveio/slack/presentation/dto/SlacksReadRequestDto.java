package com.msa.fiveio.slack.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SlacksReadRequestDto {

	private Integer page;

	private Integer size;

	private String orderby;

	private String sort;
}
