package com.msa.fiveio.hub.application.usecase;


import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.HubErrorCode;
import com.msa.fiveio.hub.infrastructure.client.KakaoClient;
import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.model.repository.HubsRepository;
import com.msa.fiveio.hub.presentation.dto.hubs.DocumentDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.KakaoResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.SearchResponseDto;
import com.msa.fiveio.hub.presentation.mapper.HubsMapper;
import com.msa.fiveio.hub.presentation.mapper.SearchResponseDtos;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@RefreshScope
@Slf4j
public class HubsServiceImpl implements HubsService {

    private final HubsRepository hubsRepository;
    private final KakaoClient kakaoClient;


    @Override
    public HubsResponseDto createHubs(HubsRequestDto hubsRequestDto) {
        String[] response = searchAddress(hubsRequestDto.address());
        Hubs hub = Hubs.builder()
            .hubName(hubsRequestDto.hubName())
            .address(hubsRequestDto.address())
            .longitude(Double.parseDouble(response[0]))
            .latitude(Double.parseDouble(response[1])).build();

        hubsRepository.save(hub);

        return HubsMapper.entityToHubsResponseDto(hub);
    }

    @Override
    @Transactional(readOnly = true)
    public HubsResponseDto readHubs(UUID id) {
        Hubs hub = hubsRepository.findById(id).orElseThrow(
            () -> new CustomException(HubErrorCode.HUBS_NOT_FOUND)
        );
        return HubsMapper.entityToHubsResponseDto(hub);
    }

    @Override
    @Transactional
    public HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsDto) {
        Hubs hub = hubsRepository.findById(id).orElseThrow(
            () -> new CustomException(HubErrorCode.HUBS_NOT_FOUND)
        );

        if (hubsDto.hubName() != null) {
            hub.updateHubName(hubsDto.hubName());
        }
        if (hubsDto.address() != null) {
            String[] response = searchAddress(hubsDto.address());
            hub.updateAddress(hubsDto.address(), Double.parseDouble(response[0]),
                Double.parseDouble(response[1]));
        }

        return HubsMapper.entityToHubsResponseDto(hub);
    }

    @Override
    public Page<SearchResponseDto> searchHubs(HubsRequestDto hubsDto, Pageable pageable) {
        Page<Hubs> hubsPage = hubsRepository.searchHubs(hubsDto, pageable);
        return SearchResponseDtos.fromPage(hubsPage).toPage(hubsPage);
    }


    @Override
    public HubsResponseDto getHubByName(String name) {
        Hubs hub = hubsRepository.getHubByName(name);
        return HubsMapper.entityToHubsResponseDto(hub);
    }


    public String[] searchAddress(String address) {
        KakaoResponseDto response = kakaoClient.searchAddress(address);

        List<DocumentDto> documentDtoList = response.getDocumentList();
        String[] result = {documentDtoList.get(0).getLongitude(),
            documentDtoList.get(0).getLatitude()};
        return result;
    }


}


