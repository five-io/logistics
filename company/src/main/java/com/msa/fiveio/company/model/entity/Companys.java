package com.msa.fiveio.company.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import com.msa.fiveio.company.presentation.dto.request.CompanyUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@SQLRestriction("deleted_at IS NULL")
@Entity
@Table(name = "p_companys")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE p_company SET deleted_at = now() WHERE id = ?")
public class Companys extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "company_id")
    private UUID id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "company_type")
    @Enumerated(EnumType.STRING)
    private CompanysType companyTypes;

    @Column(name = "hub_id")
    private UUID hubId;

    @Builder
    public Companys(String companyName, String companyAddress, CompanysType companyTypes,
            UUID hubId) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyTypes = companyTypes;
        this.hubId = hubId;
    }

    public void update(CompanyUpdateRequestDto companyUpdateRequestDto) {
        this.companyName = companyUpdateRequestDto.getCompanyName();
        this.companyAddress = companyUpdateRequestDto.getCompanyAddress();
        this.companyTypes = companyUpdateRequestDto.getCompanyType();
        this.hubId = companyUpdateRequestDto.getHubId();

    }

}
