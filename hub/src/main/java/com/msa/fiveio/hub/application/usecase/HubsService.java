package com.msa.fiveio.hub.application.usecase;

import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;

public interface HubsService {

    HubsResponseDto createHubs(HubsRequestDto hubsRequestDto);
}
