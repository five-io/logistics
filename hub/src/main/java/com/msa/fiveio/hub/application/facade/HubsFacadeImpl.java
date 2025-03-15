package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.application.usecase.HubsService;
import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.mapper.HubsMapper;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.SearchResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubsFacadeImpl implements HubsFacade {

    private final HubsService hubsService;

    @Override
    public HubsResponseDto createHubs(HubsRequestDto hubsRequestDto) {
        return hubsService.createHubs(hubsRequestDto);
    }

    @Override
    public HubsResponseDto readHubs(UUID id) {
        return hubsService.readHubs(id);
    }

    @Override
    public HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsDto) {
        HubsResponseDto dto = hubsService.readHubs(id);
        Hubs hub = HubsMapper.hubsResponseDtoToEntity(dto);
        return hubsService.updateHubs(id,hubsDto,hub);
    }

    @Override
    public Page<SearchResponseDto> searchHubs(HubsRequestDto hubsDto, Pageable pageable) {
        return hubsService.searchHubs(hubsDto,pageable);
    }


}
