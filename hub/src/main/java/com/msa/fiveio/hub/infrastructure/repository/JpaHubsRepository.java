package com.msa.fiveio.hub.infrastructure.repository;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.model.repository.HubsRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHubsRepository extends HubsRepository, JpaHubsRepositoryCustom,
    JpaRepository<Hubs, UUID> {

}
