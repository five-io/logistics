package com.msa.fiveio.company.infrastructure.repository;

import com.msa.fiveio.company.model.entity.Companys;
import com.msa.fiveio.company.model.repository.CompanysRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPACompanysRepository extends CompanysRepository, JpaRepository<Companys, UUID>, JPACompanysRepositoryCustom {
}
