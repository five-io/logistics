package com.msa.fiveio.user.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "p_delivery_managers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryManagers extends BaseEntity {

  @Id
  private Long userId;

  @OneToOne
  @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
  private Users users;

  @Column(name = "hubId", nullable = false)
  private UUID hubId;

  @Enumerated(EnumType.STRING)
  @Column(name = "managersType", nullable = false)
  private ManagersTypeEnum managersType;

  @Column(name = "sequence", nullable = false)
  private Integer sequence; // 배송 순번

  @Column(name = "isWorking", nullable = false)
  private Boolean isWorking; // 현재 상태 (근무 여부)

  public DeliveryManagers(Long userId, Users users, ManagersTypeEnum managersType,
      UUID hubId, Integer sequence, Boolean isWorking) {
    this.userId = userId;
    this.users = users;
    this.managersType = managersType;
    this.hubId = hubId;
    this.sequence = sequence;
    this.isWorking = isWorking;
  }
}