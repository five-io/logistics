package com.msa.fiveio.company.model.entity;

public enum CompanysType {
    MANUFACTURER("생산업체"),
    RECEIVER("수령업체");

    private final String value;

    CompanysType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
