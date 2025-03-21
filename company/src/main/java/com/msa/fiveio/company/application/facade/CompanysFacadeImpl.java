package com.msa.fiveio.company.application.facade;

import com.msa.fiveio.company.application.usecase.CompanyService;
import com.msa.fiveio.company.infrastructure.client.ProductCompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyUpdateRequestDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyUpdateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public void deleteCompany(UUID companyId) {
        companyService.deleteCompany(companyId);
    }

    @Override
    public CompanyUpdateResponseDto updateCompany(UUID companyId,
            CompanyUpdateRequestDto companyRequestDto) {
        return companyService.updateCompany(companyId, companyRequestDto);
    }

    @Override
    public ProductCompanyGetResponseDto getProductCompany(UUID companyId) {
        return companyService.getProductCompany(companyId);
    }


}
