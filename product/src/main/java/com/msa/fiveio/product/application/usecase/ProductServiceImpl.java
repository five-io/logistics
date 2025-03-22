package com.msa.fiveio.product.application.usecase;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.ProductErrorCode;
import com.msa.fiveio.product.infrastructure.client.OrderProductInfoDto;
import com.msa.fiveio.product.model.entity.ProductType;
import com.msa.fiveio.product.model.entity.Products;
import com.msa.fiveio.product.model.entity.Stocks;
import com.msa.fiveio.product.model.repository.ProductsRepository;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;
import com.msa.fiveio.product.presentation.mapper.ProductsMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    //상품생성
    @Override
    public ProductCreateResponseDto createProduct(ProductCreateRequestDto RequestDto) {

        Products product = ProductsMapper.ProductCreateRequestDtoToEntity(RequestDto);
        productsRepository.save(product);

        ProductCreateResponseDto responseDto = ProductsMapper.EntityToProductCreateResponseDto(
                product);
        return responseDto;
    }

    @Override
    public OrderProductInfoDto processOrderRequest(UUID productId, UUID receiverCompanyId,
            Long orderQuantity) {
        Products product = productsRepository.findById(productId).orElseThrow(
                () -> new CustomException(ProductErrorCode.PRODUCT_NOT_FOUND));
        Stocks stocks = product.getStocks();

        //주문수량확인해서 재고 입력값보다 작은지 큰지 비교
        boolean isOrderable;
        Long stocksQuantity = stocks.getQuantity();

        if (stocksQuantity <= orderQuantity) {
            //재고가 부족할 경우 :
            isOrderable = false;
        } else {
            //재고가 충분할 경우 :
            isOrderable = true;

            //주문이 들어오면 재고관리에서 주문만큼 수량 차감
            stocks.update(stocksQuantity - orderQuantity);
            if (stocksQuantity == 0) {
                product.setProductType(ProductType.OUT_OF_STOCK);
            } else {
                product.setProductType(ProductType.ON_SALE);
            }
        }

        //todo.
        // 상품에 hubId 받아오기
        return new OrderProductInfoDto(product, isOrderable);
    }

}
