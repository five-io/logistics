package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.SearchResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubsFacade {

    HubsResponseDto createHubs(HubsRequestDto hubsRequestDto);

    HubsResponseDto readHubs(UUID id);

    HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsDto);

    Page<SearchResponseDto> searchHubs(HubsRequestDto hubsDto, Pageable pageable);


}
