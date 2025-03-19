package com.msa.fiveio.company.presentation.dto.request;

import com.msa.fiveio.company.model.entity.CompanysType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CompanyUpdateRequestDto {

    private CompanysType companyType;
    private String companyAddress;
    private UUID hubId;
    private String companyName;
}
