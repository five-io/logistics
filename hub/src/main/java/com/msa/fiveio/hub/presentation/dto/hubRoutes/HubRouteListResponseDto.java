package com.msa.fiveio.hub.presentation.dto.hubRoutes;


import java.util.UUID;


public record HubRouteListResponseDto(Integer sequence,
                                      UUID depart_hub_id,
                                      UUID arrive_hub_id,
                                      Double estimated_distance_km,
                                      Long estimated_duration_min,
                                      Double actual_distance_km,
                                      Long actual_duration_min
) {


}
