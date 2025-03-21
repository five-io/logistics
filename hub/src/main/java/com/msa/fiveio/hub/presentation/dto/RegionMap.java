package com.msa.fiveio.hub.presentation.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;

@Getter
public class RegionMap {

    private Map<UUID, List<Route>> regionMap;

    public RegionMap() {
        this.regionMap = new HashMap<>();
    }

    public void addRoute(UUID from, UUID to, long distance) {
        regionMap.computeIfAbsent(from, k -> new ArrayList<>()).add(new Route(to, distance));
        regionMap.computeIfAbsent(to, k -> new ArrayList<>())
            .add(new Route(from, distance)); // 역방향도 추가
    }

    @Getter
    public static class Route {

        UUID destination;
        long distance;

        public Route(UUID destination, long distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }
}
