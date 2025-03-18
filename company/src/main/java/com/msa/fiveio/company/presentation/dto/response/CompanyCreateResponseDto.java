package com.msa.fiveio.company.presentation.dto.response;

import com.msa.fiveio.company.model.entity.CompanysType;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyCreateResponseDto {

    private UUID companyId;
    private CompanysType companyType;
    private String companyName;
    private String companyAddress;
    private UUID hubId;
}
