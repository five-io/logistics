package com.msa.fiveio.product.model.entity;

public enum ProductType {
    
    ON_SALE("판매중"),
    DISCONTINUED("판매중지"),
    OUT_OF_STOCK("품절");

    private String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
