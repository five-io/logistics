package com.msa.fiveio.slack.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@Column
	private UUID orderId;

	@Column
	private String departHubName;

	@Column
	private String transitPoint;

	@Column
	private String arriveHubName;

	@Column
	private String deliveryAddress;

	@Column
	private String recipientName;

	@Column
	private String recipientSlackId;

	@Column
	private String companyDeliveryManager;

	@Column
	private String productName;

	@Column
	private Long productQuantity;

	@Column
	private String requestNotes;

	@Column(columnDefinition = "TEXT")
	private String message;

	@Builder
	public Slacks(UUID orderId, String departHubName, String transitPoint, String arriveHubName,
		String deliveryAddress, String recipientName, String recipientSlackId,
		String companyDeliveryManager, String productName, Long productQuantity,
		String requestNotes,
		String message) {
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
	}

	public void update(String message) {
		this.message = message;
	}
}


