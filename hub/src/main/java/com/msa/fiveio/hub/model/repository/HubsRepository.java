package com.msa.fiveio.hub.model.repository;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface HubsRepository {

    Hubs save(Hubs hubs);

    Optional<Hubs> findById(UUID id);

    Page<Hubs> searchHubs(HubsRequestDto hubsRequestDto, Pageable pageable);

    long count();

    @Query("SELECT DISTINCT a.id FROM Hubs a")
    List<UUID> getHubsNameList();

    @Query("SELECT a FROM Hubs a WHERE a.hubName IN ('경기남부', '대전', '대구', '경상북도')")
    List<Hubs> getHubListByMiddle();

    @Query("SELECT a FROM Hubs a  WHERE a.hubName LIKE CONCAT(:name, '%')")
    Hubs getHubByName(String name);


}
