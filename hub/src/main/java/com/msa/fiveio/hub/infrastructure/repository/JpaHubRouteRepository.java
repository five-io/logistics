package com.msa.fiveio.hub.infrastructure.repository;

import com.msa.fiveio.hub.model.entity.HubRoute;
import com.msa.fiveio.hub.model.repository.HubRouteRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHubRouteRepository extends HubRouteRepository, JpaRepository<HubRoute, UUID> {

}
