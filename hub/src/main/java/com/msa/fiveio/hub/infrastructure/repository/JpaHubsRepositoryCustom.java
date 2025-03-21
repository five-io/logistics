package com.msa.fiveio.hub.infrastructure.repository;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaHubsRepositoryCustom {

    Page<Hubs> searchHubs(HubsRequestDto hubsRequestDto, Pageable pageable);

}
