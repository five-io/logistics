package com.msa.fiveio.company.application.facade;

import com.msa.fiveio.company.presentation.dto.CompanyRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyResponseDto;

public interface CompanysFacade {
    CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto);

}
