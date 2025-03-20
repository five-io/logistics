package com.msa.fiveio.hub.model.repository;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubsRepository {

    Hubs save(Hubs hubs);

    Optional<Hubs> findById(UUID id);

    Page<Hubs> searchHubs(HubsRequestDto hubsRequestDto, Pageable pageable);

    List<Hubs> findAll();
}
