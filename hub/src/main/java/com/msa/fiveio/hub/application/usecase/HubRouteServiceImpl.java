package com.msa.fiveio.hub.application.usecase;

import com.msa.fiveio.hub.infrastructure.client.KakaoMobility;
import com.msa.fiveio.hub.model.entity.HubRoute;
import com.msa.fiveio.hub.model.repository.HubRouteRepository;
import com.msa.fiveio.hub.presentation.dto.RegionMap;
import com.msa.fiveio.hub.presentation.dto.RegionMap.Route;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.DirectionsResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import com.msa.fiveio.hub.presentation.mapper.HubRouteDtos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubRouteServiceImpl implements HubRouteService {

    private final KakaoMobility kakaoMobilityService;
    private final HubRouteRepository hubRouteRepository;


    @Override
    public HubRouteResponseDto createHubRoute(HubsResponseDto start, HubsResponseDto end) {
        //출발
        String origin = start.longitude().toString() + "," + start.latitude().toString();
        //도착
        String destination = end.longitude().toString() + "," + end.latitude().toString();

        DirectionsResponseDto responseDto = kakaoMobilityService.distanceMatrix(origin,
            destination);
        HubRoute hubRoute = HubRoute.builder()
            .departId(start.id())
            .arriveId(end.id())
            .takenTime(Long.valueOf(responseDto.getRoutes()[0].getSummary().getDuration()))
            .distance(Long.valueOf(responseDto.getRoutes()[0].getSummary().getDistance()))
            .build();

        HubRoute savedRoute = hubRouteRepository.save(hubRoute);

        return HubRouteResponseDto.of(savedRoute);
    }


    @Override
    public List<HubRouteResponseDto> getHubsListByHubId(UUID id) {
        List<HubRoute> hubs = hubRouteRepository.getHubsListByHubId(id);
        return HubRouteDtos.fromList(hubs).getResponseDtos();
    }

    @Override
    public HubRouteResponseDto getHubRouteByIds(UUID start, UUID end) {
        HubRoute hubRoute = hubRouteRepository.getHubRouteByIds(start, end);
        return HubRouteResponseDto.of(hubRoute);
    }


    @Override
    public List<UUID> findShortestRoute(UUID start, UUID end, RegionMap graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.distance));
        Map<UUID, Long> distances = new HashMap<>();
        Map<UUID, UUID> previousNodes = new HashMap<>();
        Set<UUID> visited = new HashSet<>();
        Long init = 0L;

        Map<UUID, List<RegionMap.Route>> map = graph.getRegionMap();

        // 모든 거리를 무한대로 초기화
        for (UUID hub : map.keySet()) {
            distances.put(hub, Long.MAX_VALUE);
        }
        distances.put(start, init);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 이미 방문한 노드는 스킵
            if (visited.contains(current.name)) {
                System.out.println("Visited " + current.name + " + 거리 " + current.distance);
                continue;
            }
            visited.add(current.name);

            // 목적지 도착 시 종료
            if (current.name.equals(end)) {
                break;
            }

            // 현재 허브에서 갈 수 있는 모든 경로 탐색
            for (Route route : map.getOrDefault(current.name, new ArrayList<>())) {
                System.out.println("Route " + route);
                long newDist = distances.get(current.name) + route.getDistance();
                if (newDist < distances.get(route.getDestination())) {
                    distances.put(route.getDestination(), newDist);
                    previousNodes.put(route.getDestination(), current.name);
                    pq.add(new Node(route.getDestination(), newDist));
                }
            }
        }

        // 최단 경로 복원
        List<UUID> path = new ArrayList<>();
        UUID step = end;
        while (step != null) {
            path.add(step);
            step = previousNodes.get(step);
        }
        Collections.reverse(path);

        System.out.println(path);
        // return path.isEmpty() || !path.get(0).equals(start) ? List.of("경로 없음") : path;

        return path;
    }


    static class Node {

        UUID name;
        long distance;

        public Node(UUID name, long distance) {
            this.name = name;
            this.distance = distance;
        }

    }

}
