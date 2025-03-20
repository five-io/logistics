package com.msa.fiveio.ai.application.usecase;

import com.msa.fiveio.ai.presentation.dto.AisCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AisServiceImpl implements AisService {

	@Override
	public String createAis(AisCreateRequestDto aisCreateRequestDto) {
		return "AIS 생성 완료";
	}
}
