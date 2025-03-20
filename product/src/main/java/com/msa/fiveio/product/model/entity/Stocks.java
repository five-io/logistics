package com.msa.fiveio.product.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_stocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stocks extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "stock_id")
    private String id;

    @Column(name = "stock_quantity")
    private int quantity;

    @Builder
    public Stocks(int quantity) {
        this.quantity = quantity;
    }
}
