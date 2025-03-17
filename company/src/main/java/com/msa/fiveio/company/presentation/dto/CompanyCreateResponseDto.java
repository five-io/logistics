package com.msa.fiveio.company.presentation.dto;

import com.msa.fiveio.company.model.entity.CompanysType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CompanyCreateResponseDto {

    private UUID companyId;
    private CompanysType companyType;
    private String companyName;
    private String companyAddress;
    private UUID hubId;
}
