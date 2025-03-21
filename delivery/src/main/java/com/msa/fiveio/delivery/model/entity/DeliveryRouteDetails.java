package com.msa.fiveio.delivery.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class DeliveryRouteDetails {

    @Column(nullable = false)
    private int sequence;

    @Column(name = "depart_hub_id", nullable = false)
    private UUID departHubId;

    @Column(name = "arrive_hub_id", nullable = false)
    private UUID arriveHubId;

    @Column(name = "estimated_distance_km", nullable = false)
    private Long estimatedDistanceKm;

    @Column(name = "estimated_duration_min", nullable = false)
    private Long estimatedDurationMin;

    @Builder
    public DeliveryRouteDetails(int sequence, UUID departHubId, UUID arriveHubId,
        Long estimatedDistanceKm, Long estimatedDurationMin) {
        this.sequence = sequence;
        this.departHubId = departHubId;
        this.arriveHubId = arriveHubId;
        this.estimatedDistanceKm = estimatedDistanceKm;
        this.estimatedDurationMin = estimatedDurationMin;
    }
}
