package com.msa.fiveio.company.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@SQLRestriction("deleted_at IS NULL")
@Entity
@Table(name = "p_companys")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Companys extends BaseEntity {

    //todo. Enum 타입이 아닌 String으로 저장

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
    public Companys(String companyName, String address, CompanysType companyTypes, UUID hubID) {
        this.companyName = companyName;
        this.companyAddress = address;
        this.companyTypes = companyTypes;
        this.hubId = hubID;
    }







}
