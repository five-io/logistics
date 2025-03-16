package com.msa.fiveio.company.application.facade;

import com.msa.fiveio.company.application.usecase.CompanyService;
import com.msa.fiveio.company.presentation.dto.CompanyRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyResponseDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanysFacadeImpl implements CompanysFacade {
    private final CompanyService companyService;

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto) {
        return companyService.createCompanys(companyRequestDto);
    }
}
