package com.msa.fiveio.hub.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="p_hubs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hubs {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "hub_id")
    private UUID id;

    @Column(name = "hub_name")
    private String hubName;

    @Column
    private String address;

    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double latitude;

    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double longitude;

    @Builder
    public Hubs(String hubName, String address, Double latitude, Double longitude) {
        this.hubName = hubName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
