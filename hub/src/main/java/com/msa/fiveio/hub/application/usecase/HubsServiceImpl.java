package com.msa.fiveio.hub.application.usecase;




import com.msa.fiveio.hub.infrastructure.client.KakaoClient;
import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.model.repository.HubsRepository;
import com.msa.fiveio.hub.presentation.dto.DocumentDto;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.KakaoResponseDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@RefreshScope
@Slf4j
public class HubsServiceImpl implements HubsService {

    private final HubsRepository hubsRepository;
    private final KakaoClient   kakaoClient;



    @Override
    public HubsResponseDto createHubs(HubsRequestDto hubsRequestDto) {
        String[] response = searchAddress(hubsRequestDto.address());
        Hubs hubs = Hubs.builder()
            .hubName(hubsRequestDto.hubName())
            .address(hubsRequestDto.address())
            .longitude(Double.parseDouble(response[0]))
            .latitude(Double.parseDouble(response[1])).build();

        hubsRepository.save(hubs);

       return HubsResponseDto.of(hubs);
    }

    @Override
    @Transactional(readOnly = true)
    public HubsResponseDto readHubs(UUID id) {
        Hubs hub = hubsRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Hub not found")
        );
        return HubsResponseDto.of(hub);
    }

    @Override
    @Transactional
    public HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsDto) {
        Hubs hub = hubsRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Hub not found")
        );
        if(hubsDto.hubName() != null) {
            hub.setHubName(hubsDto.hubName());
        }
        if(hubsDto.address() != null) {
            String[] response = searchAddress(hubsDto.address());
            hub.setAddress(hubsDto.address());
            hub.setLongitude(Double.parseDouble(response[0]));
            hub.setLatitude(Double.parseDouble(response[1]));
        }

        return HubsResponseDto.of(hub);
    }

    public String[] searchAddress(String address) {
        log.info("[KakaoAddressService searchAddress] address: {}", address);
        KakaoResponseDto  response = kakaoClient.searchAddress(address);
        log.info("[KakaoAddressService searchAddress] response: {}", response);
        List<DocumentDto> documentDtoList = response.getDocumentList();
        return documentDtoList.stream()
            .filter(dto -> dto.getAddressName().equals(address)) // 주소 일치하는 값 찾기
            .findFirst() // 첫 번째 일치하는 요소 찾기
            .or(() -> documentDtoList.stream().findFirst()) // 없으면 첫 번째 요소 가져오기
            .map(dto -> new String[]{dto.getLongitude(), dto.getLatitude()}) // 결과 매핑
            .orElse(new String[]{"", ""});
    }

}


