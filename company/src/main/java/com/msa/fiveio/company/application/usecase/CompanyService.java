package com.msa.fiveio.company.application.usecase;

import com.msa.fiveio.company.infrastructure.client.ProductCompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyUpdateRequestDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyUpdateResponseDto;
import java.util.UUID;

public interface CompanyService {

    //업체생성
    CompanyCreateResponseDto createCompanys(CompanyCreateRequestDto requestDto);

    //업체단건조회
    CompanyGetResponseDto getCompanys(UUID companyId);

    //업체삭제
    void deleteCompany(UUID companyId);

    //업체수정
    CompanyUpdateResponseDto updateCompany(UUID companyId, CompanyUpdateRequestDto requestDto);

    // 주문상품업체정보 조회(product로 넘김)
    ProductCompanyGetResponseDto getProductCompany(UUID companyId);
}
