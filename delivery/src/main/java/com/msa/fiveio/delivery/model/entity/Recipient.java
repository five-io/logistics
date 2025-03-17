package com.msa.fiveio.delivery.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder(access = AccessLevel.PROTECTED)
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipient {

    private String recipientName;
    private String slackId;

}


