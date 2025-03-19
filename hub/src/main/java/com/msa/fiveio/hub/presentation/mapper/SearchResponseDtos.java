package com.msa.fiveio.hub.presentation.mapper;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.hubs.SearchResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;


public class SearchResponseDtos {

    private final List<SearchResponseDto> responseDtos;

    public SearchResponseDtos(List<SearchResponseDto> responseDtos) {
        this.responseDtos = responseDtos;
    }

    public static SearchResponseDtos fromPage(Page<Hubs> hubsPage) {
        List<SearchResponseDto> dtoList = hubsPage.getContent()
            .stream()
            .map(HubsMapper::entityToSearchResponseDto)
            .toList();

        return new SearchResponseDtos(dtoList);
    }

    public Page<SearchResponseDto> toPage(Page<Hubs> hubsPage) {
        return new PageImpl<>(responseDtos, hubsPage.getPageable(), hubsPage.getTotalElements());
    }


}
