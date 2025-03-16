package com.msa.fiveio.company.presentation.dto;

import com.msa.fiveio.company.model.entity.Companys;
import com.msa.fiveio.company.model.entity.CompanysType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
public class CompanyResponseDto {

    private UUID companyId;
    private CompanysType companyType;
    private String companyName;
    private String companyAddress;
    private UUID hubId;

    public static CompanyResponseDto of(Companys company){
        return CompanyResponseDto.builder()
                .companyId(company.getId())
                .companyName(company.getCompanyName())
                .companyType(company.getCompanyTypes())
                .companyAddress(company.getCompanyAddress())
                .hubId(company.getHubId())
                .build();
    }
}
