package com.msa.fiveio.hub.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(
    name = "p_hubs_routes",
    uniqueConstraints = @UniqueConstraint(columnNames = {"departId", "arriveId"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HubRoute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "hub_id")
    private UUID id;

    @Column
    private UUID departId;

    @Column
    private UUID arriveId;

    @Column
    private Long takenTime;

    @Column
    private Long distance;

    @Builder
    private HubRoute(UUID departId, UUID arriveId, Long takenTime,
        Long distance) {
        this.departId = departId;
        this.arriveId = arriveId;
        this.takenTime = takenTime;
        this.distance = distance;
    }

}
