package com.msa.fiveio.product.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.ProductErrorCode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "p_products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Products extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID id;

    @Column(name = "hub_id")
    private UUID hubId;

    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_detail")
    private String productDetail;

    @Column(name = "product_price")
    private double productPrice;

    @Setter
    @Column(name = "product_type")
    private ProductType productType;

    //todo. cascade -> remove? All?
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "stock_id")
    private Stocks stocks;

    @Builder
    public Products(UUID hubId, UUID companyId, String productName, String productDetail,
            double productPrice, ProductType productType, Stocks stocks) {
        this.hubId = hubId;
        this.companyId = companyId;
        this.productName = productName;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        if (stocks.getQuantity() == 0) {
            this.productType = ProductType.OUT_OF_STOCK;
        } else if (stocks.getQuantity() > 0) {
            this.productType = ProductType.ON_SALE;
        } else {
            throw new CustomException(ProductErrorCode.NEGATIVE_INVENTORY_ERROR);
        }
        this.stocks = stocks;
    }
}
