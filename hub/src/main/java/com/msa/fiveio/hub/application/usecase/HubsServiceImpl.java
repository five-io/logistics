package com.msa.fiveio.hub.application.usecase;


import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.HubErrorCode;
import com.msa.fiveio.hub.infrastructure.client.KakaoClient;
import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.model.repository.HubsRepository;
import com.msa.fiveio.hub.presentation.mapper.HubsMapper;
import com.msa.fiveio.hub.presentation.mapper.SearchResponseDtos;
import com.msa.fiveio.hub.presentation.dto.DocumentDto;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.KakaoResponseDto;
import com.msa.fiveio.hub.presentation.dto.SearchResponseDto;
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
    private final KakaoClient   kakaoClient;



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
    public HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsDto, Hubs hub) {
        if(hubsDto.hubName() != null) {
            hub.updateHubName(hubsDto.hubName());
            hubsRepository.save(hub);
        }
        if(hubsDto.address() != null) {
            String[] response = searchAddress(hubsDto.address());
            hub.updateAddress(hubsDto.address(),Double.parseDouble(response[0]),Double.parseDouble(response[1]));
            hubsRepository.save(hub);
        }

        return HubsMapper.entityToHubsResponseDto(hub);
    }

    @Override
    public Page<SearchResponseDto> searchHubs(HubsRequestDto hubsDto, Pageable pageable) {
        Page<Hubs> hubsPage = hubsRepository.searchHubs(hubsDto,pageable);
        return SearchResponseDtos.fromPage(hubsPage).toPage(hubsPage);
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


