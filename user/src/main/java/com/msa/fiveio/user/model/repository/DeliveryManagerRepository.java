package com.msa.fiveio.user.model.repository;

import com.msa.fiveio.user.model.entity.DeliveryManagers;

import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import com.msa.fiveio.user.presentation.dto.UserResponseDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryManagerRepository {

  DeliveryManagers save(DeliveryManagers deliveryManagers);

  Optional<DeliveryManagers> findByUserId(Long userId);

  boolean existsById(Long userId);

  @Query("SELECT COALESCE(MAX(dm.sequence),0) FROM DeliveryManagers dm JOIN Users u ON dm.userId = u.userId WHERE u.hubId = :hubId and dm.managersType = :managersType")
  Integer findMaxSequenceByHubId(UUID hubId, ManagersTypeEnum managersType);

  @Query("""
    SELECT new com.msa.fiveio.user.presentation.dto.UserResponseDto(
        u.userId,
        u.username,
        dm.updatedAt
    )
    FROM DeliveryManagers dm
    JOIN Users u ON dm.userId = u.userId
    WHERE u.hubId = :hubId
      AND dm.managersType = :type
      AND dm.isWorking = false
""")
  List<UserResponseDto> findUserIdByHubId(UUID hubId, ManagersTypeEnum type);
}