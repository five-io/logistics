package com.msa.fiveio.product.application.facade;

import com.msa.fiveio.product.application.usecase.ProductService;
import com.msa.fiveio.product.infrastructure.client.CompanyClient;
import com.msa.fiveio.product.infrastructure.client.OrderProductInfoDto;
import com.msa.fiveio.product.infrastructure.client.ProductCompanyGetResponseDto;
import com.msa.fiveio.product.model.entity.Products;
import com.msa.fiveio.product.presentation.dto.OrderProductGetResponseDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;
    private final CompanyClient companyClient;

    @Override
    public ProductCreateResponseDto createProduct(ProductCreateRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @Override
    public OrderProductGetResponseDto processOrderRequest(UUID productId, UUID receiverCompanyId,
            Long quantity) {
        ProductCompanyGetResponseDto companyDto = companyClient.getProductCompany(
                receiverCompanyId).getBody();
        OrderProductInfoDto orderProductInfoDto = productService.processOrderRequest(productId,
                receiverCompanyId, quantity);
        Products product = orderProductInfoDto.getProduct();
        OrderProductGetResponseDto responseDto =
                OrderProductGetResponseDto.builder()
                        .deliveryAddress(companyDto.getDeliveryAddress())
                        .requesterCompanyId(product.getCompanyId())
                        .departHubId(product.getHubId())
                        .arriveHubId(companyDto.getArriveHubId())
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .productType(product.getProductType())
                        .isOrderable(orderProductInfoDto.getIsOrderable())
                        .build();

        return responseDto;
    }

}
