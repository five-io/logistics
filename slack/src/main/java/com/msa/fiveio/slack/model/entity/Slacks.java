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
	private UUID receiverCompanyId;

	@Column
	private UUID orderId;

	@Column
	private UUID departId;

	@Column
	private String transitPoint;

	@Column
	private UUID arriveId;

	@Column
	private String deliveryStatus;

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
	private String productQuantity;

	@Column
	private String requestNotes;

	@Column(columnDefinition = "TEXT")
	private String message;

	@Builder
	public Slacks(UUID receiverCompanyId, UUID orderId, UUID departId, String transitPoint,
		UUID arriveId, String deliveryStatus, String deliveryAddress, String recipientName,
		String recipientSlackId, String companyDeliveryManager, String productName,
		String productQuantity, String requestNotes, String message) {
		this.receiverCompanyId = receiverCompanyId;
		this.orderId = orderId;
		this.departId = departId;
		this.transitPoint = transitPoint;
		this.arriveId = arriveId;
		this.deliveryStatus = deliveryStatus;
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


