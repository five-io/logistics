package com.msa.fiveio.company.presentation.dto;

import com.msa.fiveio.company.model.entity.CompanysType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CompanyRequestDto {

    private CompanysType companyType;
    private String companyAddress;
    private UUID hubId;
    private String companyName;

}
