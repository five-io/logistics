package com.msa.fiveio.slack.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="p_slack_messages")
@Entity
public class SlackMessages {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "slack_id")
	private UUID id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private String receiveId;

	@Column(nullable = false)
	private String orderId;

	@Column(columnDefinition = "TEXT")
	private String message;

	private LocalDateTime deliveryTime;

}


