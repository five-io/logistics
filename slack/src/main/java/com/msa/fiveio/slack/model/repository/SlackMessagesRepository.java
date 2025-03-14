package com.msa.fiveio.slack.model.repository;

import com.msa.fiveio.slack.model.entity.SlackMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlackMessagesRepository extends JpaRepository<SlackMessages, Long> {
}
