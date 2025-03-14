package com.msa.fiveio.hub.model.repository;

import com.msa.fiveio.hub.model.entity.Hubs;
import java.util.Optional;
import java.util.UUID;

public interface HubsRepository {

    Hubs save(Hubs hubs);

    Optional<Hubs> findById(UUID id);
}
