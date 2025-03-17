package com.msa.fiveio.company.application.usecase;

import com.msa.fiveio.company.presentation.dto.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.CompanyRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyCreateResponseDto;

import java.util.UUID;

public interface CompanyService {

    //업체생성
    CompanyCreateResponseDto createCompanys(CompanyCreateRequestDto requestDto);

    //업체단건조회
    CompanyGetResponseDto getCompanys(UUID companyId);


}
