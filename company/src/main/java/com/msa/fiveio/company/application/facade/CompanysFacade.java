package com.msa.fiveio.company.application.facade;

import com.msa.fiveio.company.presentation.dto.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.CompanyGetResponseDto;

import java.util.UUID;

public interface CompanysFacade {
    CompanyCreateResponseDto createCompany(CompanyCreateRequestDto companyRequestDto);
    CompanyGetResponseDto getCompany(UUID companyId);

}
