package com.msa.fiveio.hub.model.repository;

import com.msa.fiveio.hub.model.entity.HubRoute;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface HubRouteRepository {

    HubRoute save(HubRoute hubRoute);

    long count();

    List<HubRoute> findAll();

    @Query("SELECT a FROM HubRoute a WHERE a.departId IN :id")
    List<HubRoute> getHubsListByHubId(UUID id);

    @Query("SELECT a FROM HubRoute a WHERE a.departId = :start AND a.arriveId = :end")
    HubRoute getHubRouteByIds(UUID start, UUID end);
}
