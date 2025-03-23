package com.msa.fiveio.slack.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "p_slacks")
@Entity
@Getter
public class Slacks extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "slack_id")
	private UUID id;

	@Column(unique = true, nullable = false)
	private UUID orderId;

	@Column(length = 100, nullable = false)
	private String departHubName;

	@Column(length = 100, nullable = false)
	private String transitPoint;

	@Column(length = 100, nullable = false)
	private String arriveHubName;

	@Column(nullable = false)
	private String deliveryAddress;

	@Column(length = 100, nullable = false)
	private String recipientName;

	@Column(length = 100, nullable = false)
	private String recipientSlackId;

	@Column(length = 100, nullable = false)
	private String companyDeliveryManager;

	@Column(length = 100, nullable = false)
	private String productName;

	@Column(nullable = false)
	private Long productQuantity;

	@Column(nullable = false)
	private String requestNotes;

	@Column(columnDefinition = "TEXT")
	private String message;

	@Enumerated(EnumType.STRING)
	@Column(name = "send_status", nullable = false)
	private SendStatus sendStatus;

	@Builder
	public Slacks(UUID orderId, String departHubName, String transitPoint, String arriveHubName,
		String deliveryAddress, String recipientName, String recipientSlackId,
		String companyDeliveryManager, String productName, Long productQuantity,
		String requestNotes,
		String message, SendStatus sendStatus) {
		this.orderId = orderId;
		this.departHubName = departHubName;
		this.transitPoint = transitPoint;
		this.arriveHubName = arriveHubName;
		this.deliveryAddress = deliveryAddress;
		this.recipientName = recipientName;
		this.recipientSlackId = recipientSlackId;
		this.companyDeliveryManager = companyDeliveryManager;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.requestNotes = requestNotes;
		this.message = message;
		this.sendStatus = sendStatus;
	}

	public void updateStatus(SendStatus status) {
		this.sendStatus = status;
	}
}


