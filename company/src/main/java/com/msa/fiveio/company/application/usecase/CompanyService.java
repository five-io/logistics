package com.msa.fiveio.company.application.usecase;

import com.msa.fiveio.company.presentation.dto.CompanyRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyResponseDto;

public interface CompanyService {

    //업체생성
    CompanyResponseDto createCompanys(CompanyRequestDto requestDto);
}
