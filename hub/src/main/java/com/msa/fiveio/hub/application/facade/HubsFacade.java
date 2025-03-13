package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;

public interface HubsFacade {

    HubsResponseDto createHubs(HubsRequestDto hubsRequestDto);

}
