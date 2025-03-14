package com.msa.fiveio.hub.application.usecase;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.SearchResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubsService {

    HubsResponseDto createHubs(HubsRequestDto hubsRequestDto);

    HubsResponseDto readHubs(UUID id);

    HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsRequestDto, Hubs hub);

    Page<SearchResponseDto> searchHubs(HubsRequestDto hubsDto, Pageable pageable);
}
