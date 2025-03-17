package com.msa.fiveio.delivery.model.entity;

import com.msa.fiveio.delivery.model.entity.enums.DeliveryRouteStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_delivery_routes")
public class DeliveryRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "delivery_route_id")
    private UUID deliveryRouteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Embedded
    private DeliveryRouteDetails deliveryRouteDetails;

    @Column(name = "actual_distance_km")
    private Double actualDistanceKm;

    @Column(name = "actual_duration_min")
    private Long actualDurationMin;

    @Column(name = "delivery_route_status", nullable = false)
    private DeliveryRouteStatus deliveryRouteStatus;

    // 배송 담당자 ID
    @Column(name = "delivery_manager", nullable = false)
    private Long deliveryManager;
}
