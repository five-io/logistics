package com.msa.fiveio.hub.presentation.controller;

import com.msa.fiveio.hub.application.facade.HubsFacade;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitSetting {

    private final HubsFacade hubsFacade;

    public void initSet() {
        String input = """
            서울특별시 센터 : 서울특별시 송파구 송파대로 55
            경기 북부 센터 : 경기도 고양시 덕양구 권율대로 570
            경기 남부 센터 : 경기도 이천시 덕평로 257-21
            부산광역시 센터 : 부산 동구 중앙대로 206
            대구광역시 센터 : 대구 북구 태평로 161
            인천광역시 센터 : 인천 남동구 정각로 29
            광주광역시 센터 : 광주 서구 내방로 111
            대전광역시 센터 : 대전 서구 둔산로 100
            울산광역시 센터 : 울산 남구 중앙로 201
            세종특별자치시 센터 : 세종특별자치시 한누리대로 2130
            강원특별자치도 센터 : 강원특별자치도 춘천시 중앙로 1
            충청북도 센터 : 충북 청주시 상당구 상당로 82
            충청남도 센터 : 충남 홍성군 홍북읍 충남대로 21
            전북특별자치도 센터 : 전북특별자치도 전주시 완산구 효자로 225
            전라남도 센터 : 전남 무안군 삼향읍 오룡길 1
            경상북도 센터 : 경북 안동시 풍천면 도청대로 455
            경상남도 센터 : 경남 창원시 의창구 중앙대로 300
            """;

        // 줄 단위로 분리
        String[] lines = input.split("\\n");

        // 정규식 패턴 설정
        Pattern pattern = Pattern.compile("(.*?센터)\\s*:\\s*(.*)");

        // 결과 저장할 리스트
        List<String> extractedCenters = new ArrayList<>();

        // 각 줄을 검사하여 센터 이름과 주소 추출
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String centerName = matcher.group(1).trim(); // 센터 이름
                String address = matcher.group(2).trim();   // 주소
                HubsRequestDto hubsRequestDto = new HubsRequestDto(centerName, address);
                hubsFacade.createHubs(hubsRequestDto);
            }
        }

    }

}
