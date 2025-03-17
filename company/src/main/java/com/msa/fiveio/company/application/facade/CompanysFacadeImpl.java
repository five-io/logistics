package com.msa.fiveio.company.application.facade;

import com.msa.fiveio.company.application.usecase.CompanyService;
import com.msa.fiveio.company.presentation.dto.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.CompanyGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanysFacadeImpl implements CompanysFacade {
    private final CompanyService companyService;

    @Override
    public CompanyCreateResponseDto createCompany(CompanyCreateRequestDto companyRequestDto) {
        return companyService.createCompanys(companyRequestDto);
    }

    @Override
    public CompanyGetResponseDto getCompany(UUID companyId) {
        return companyService.getCompanys(companyId);
    }


}
