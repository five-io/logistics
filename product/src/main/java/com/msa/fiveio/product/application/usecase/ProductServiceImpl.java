package com.msa.fiveio.product.application.usecase;

import com.msa.fiveio.product.model.entity.Products;
import com.msa.fiveio.product.model.repository.ProductsRepository;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;
import com.msa.fiveio.product.presentation.mapper.ProductsMapper;
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

}
