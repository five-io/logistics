package com.msa.fiveio.ai.application.facade;

import com.msa.fiveio.ai.application.usecase.AisService;
import com.msa.fiveio.ai.presentation.dto.AisCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AisFacadeImpl implements AisFacade {

	private final AisService aisService;

	@Override
	public String createAis(AisCreateRequestDto aisCreateRequestDto) {
		return aisService.createAis(aisCreateRequestDto);
	}
}
