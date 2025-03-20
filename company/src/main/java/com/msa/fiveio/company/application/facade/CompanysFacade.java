package com.msa.fiveio.company.application.facade;

import com.msa.fiveio.company.infrastructure.client.ProductCompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyUpdateRequestDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyUpdateResponseDto;
import java.util.UUID;

public interface CompanysFacade {

    CompanyCreateResponseDto createCompany(CompanyCreateRequestDto companyRequestDto);

    CompanyGetResponseDto getCompany(UUID companyId);

    void deleteCompany(UUID companyId);

    CompanyUpdateResponseDto updateCompany(UUID companyId,
            CompanyUpdateRequestDto companyRequestDto);

    ProductCompanyGetResponseDto getProductCompany(UUID companyId);
}
