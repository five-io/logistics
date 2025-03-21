package com.msa.fiveio.hub.presentation.controller;

import com.msa.fiveio.hub.application.facade.HubsFacade;
import com.msa.fiveio.hub.application.usecase.HubsService;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitSetting {

    private final HubsFacade hubsFacade;
    private final HubsService hubsService;

    public void initSet(String inputs) {

        // 줄 단위로 분리
        String[] lines = inputs.split("\\n");
        // 정규식 패턴 설정
        Pattern pattern = Pattern.compile("(.*?센터)\\s*:\\s*(.*)");

        int count = 1;
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

    public void initSetRoutes(String[] inputs) {
        //중앙
        List<String> list = Arrays.asList(inputs[0].split("\\n"));

        for (int i = 1; i < inputs.length; i++) {

            List<String> conList = Arrays.asList(inputs[i].split("\\n"));
            String middle = list.get(i - 1);
            HubsResponseDto end = hubsService.getHubByName(middle);

            for (String con : conList) {
                HubsResponseDto dto = hubsService.getHubByName(con);
                hubsFacade.createHubInit(dto, end);
            }

        }


    }


}
