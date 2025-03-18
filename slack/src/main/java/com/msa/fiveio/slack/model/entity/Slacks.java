package com.msa.fiveio.slack.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name="p_slack")
@Entity
@Getter
public class Slacks extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "slack_id")
	private UUID id;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private UUID receiveId;

	@Column(nullable = false)
	private UUID orderId;

	@Column(columnDefinition = "TEXT")
	private String message;

	@Column
	private LocalDateTime deliveryTime;

	@Builder
	public Slacks(Long userId, UUID receiveId, UUID orderId, String message, LocalDateTime deliveryTime) {
		this.userId = userId;
		this.receiveId = receiveId;
		this.orderId = orderId;
		this.message = message;
		this.deliveryTime = deliveryTime;
	}

	public void update(String message, LocalDateTime deliveryTime) {
		this.message = message;
		this.deliveryTime = deliveryTime;
	}
}


