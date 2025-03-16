package com.msa.fiveio.company.application.usecase;

import com.msa.fiveio.company.model.entity.Companys;
import com.msa.fiveio.company.model.entity.CompanysType;
import com.msa.fiveio.company.model.repository.CompanysRepository;
import com.msa.fiveio.company.presentation.dto.CompanyRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanysRepository companysRepository;

    //업체생성
    @Override
    public CompanyResponseDto createCompanys(CompanyRequestDto requestDto) {
        //requestdto -> 엔티티 변환
        System.out.println('1');
        Companys company = Companys.builder()
                .companyName(requestDto.getCompanyName())
                .address(requestDto.getCompanyAddress())
                .companyTypes((CompanysType) requestDto.getCompanyType())
                .hubID(requestDto.getHubId())
                .build();
        System.out.println('2');
        //엔티티 저장
        companysRepository.save(company);
        System.out.println('3');
        //dto 반환
        return CompanyResponseDto.of(company);
    }
}
