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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="p_slack")
@Entity
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

	public void update(String message, LocalDateTime deliveryTime) {
		this.message = message;
		this.deliveryTime = deliveryTime;
	}
}


