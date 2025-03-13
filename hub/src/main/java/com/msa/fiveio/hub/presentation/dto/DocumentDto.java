package com.msa.fiveio.hub.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("y")
    private String latitude;

    @JsonProperty("x")
    private String longitude;



}
