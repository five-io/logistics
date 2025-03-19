package com.msa.fiveio.hub.presentation.dto.hubRoutes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DirectionsResponseDto {

    @JsonProperty("routes")
    private Route[] routes;


    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Route {

        @JsonProperty("summary")
        private Summary summary;

    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Summary {

        @JsonProperty("distance")
        private int distance;

        @JsonProperty("duration")
        private int duration;
    }
}
