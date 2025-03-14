package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.application.usecase.HubsService;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HubsFacadeImpl implements HubsFacade {

    private final HubsService hubsService;

    @Override
    public HubsResponseDto createHubs(HubsRequestDto hubsRequestDto) {
        return hubsService.createHubs(hubsRequestDto);
    }

    @Override
    public HubsResponseDto reaadHubs(UUID id) {
        return hubsService.reaadHubs(id);
    }

    @Override
    public HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsDto) {
        return hubsService.updateHubs(id,hubsDto);
    }


}
