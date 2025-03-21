package com.msa.fiveio.company.presentation.mapper;

import com.msa.fiveio.company.infrastructure.client.ProductCompanyGetResponseDto;
import com.msa.fiveio.company.model.entity.Companys;
import com.msa.fiveio.company.presentation.dto.request.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyUpdateResponseDto;

public class CompanysMapper {

    public static CompanyCreateResponseDto entityToCreateCompanysResponseDto(Companys companys) {
        return CompanyCreateResponseDto.builder()
                .companyId(companys.getId())
                .companyType(companys.getCompanyTypes())
                .companyAddress(companys.getCompanyAddress())
                .hubId(companys.getHubId())
                .companyName(companys.getCompanyName())
                .build();
    }

    public static Companys CreateRequestDtoToEntity(CompanyCreateRequestDto createRequestDto) {
        return Companys.builder()
                .companyName(createRequestDto.getCompanyName())
                .companyAddress(createRequestDto.getCompanyAddress())
                .companyTypes(createRequestDto.getCompanyType())
                .hubId(createRequestDto.getHubId())
                .build();
    }

    public static CompanyGetResponseDto entityToGetCompanyResponseDto(Companys companys) {
        return CompanyGetResponseDto.builder()
                .companyId(companys.getId())
                .companyType(companys.getCompanyTypes())
                .companyAddress(companys.getCompanyAddress())
                .hubId(companys.getHubId())
                .companyName(companys.getCompanyName())
                .build();
    }

    public static Companys UpdateRequestDtoToEntity(CompanyCreateRequestDto createRequestDto) {
        return Companys.builder()
                .companyName(createRequestDto.getCompanyName())
                .companyAddress(createRequestDto.getCompanyAddress())
                .companyTypes(createRequestDto.getCompanyType())
                .hubId(createRequestDto.getHubId())
                .build();
    }

    public static CompanyUpdateResponseDto entityToUpdateCompanyResponseDto(Companys companys) {
        return CompanyUpdateResponseDto.builder()
                .companyId(companys.getId())
                .companyType(companys.getCompanyTypes())
                .companyAddress(companys.getCompanyAddress())
                .hubId(companys.getHubId())
                .companyName(companys.getCompanyName())
                .build();
    }

    public static ProductCompanyGetResponseDto entityToGetProductCompanyResponseDto(
            Companys companys) {
        return ProductCompanyGetResponseDto.builder()
                .deliveryAddress(companys.getCompanyAddress())
                .arriveHubId(companys.getHubId())
                .build();
    }


}

