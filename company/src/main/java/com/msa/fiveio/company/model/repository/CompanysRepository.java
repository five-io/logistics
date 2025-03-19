package com.msa.fiveio.company.model.repository;

import com.msa.fiveio.company.model.entity.Companys;
import java.util.Optional;
import java.util.UUID;

public interface CompanysRepository {

    Companys save(Companys company);

    Optional<Companys> findById(UUID companyId);

    void deleteById(UUID companyId);
}
