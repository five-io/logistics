package com.msa.fiveio.company.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

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
    public Companys(String companyName, String companyAddress, CompanysType companyTypes, UUID hubId) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyTypes = companyTypes;
        this.hubId = hubId;
    }
}
