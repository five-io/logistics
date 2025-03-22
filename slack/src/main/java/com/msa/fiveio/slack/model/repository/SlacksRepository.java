package com.msa.fiveio.slack.model.repository;

import com.msa.fiveio.slack.model.entity.Slacks;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlacksRepository extends JpaRepository<Slacks, UUID> {

	Optional<Slacks> findByOrderId(UUID orderId);
}
