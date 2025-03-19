package com.msa.fiveio.order.application.facade;

import com.msa.fiveio.order.application.usecase.ExternalService;
import com.msa.fiveio.order.application.usecase.OrderService;
import com.msa.fiveio.order.infrastructure.client.dto.response.CompanyResponseDto;
import com.msa.fiveio.order.model.entity.Order;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import com.msa.fiveio.order.presentation.mapper.OrderMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrdersFacade {

    private final OrderService orderService;
    private final ExternalService externalService;

    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        CompanyResponseDto companyResponseDto = externalService.sendCompanyRequest(
            orderCreateRequestDto);
        Order order = orderCreateRequestDto.createOrder(companyResponseDto.getRequesterCompanyId());
        Order savedOrder = orderService.createOrder(companyResponseDto, order);

        externalService.sendDeliveryRequest(savedOrder.getOrderId(), companyResponseDto,
            orderCreateRequestDto);
        return OrderMapper.orderIdToOrderCreateResponseDto(savedOrder);
    }

    @Override
    public Page<OrderResponseDto> readOrders(OrderSearchRequestDto requestDto, Pageable pageable) {
        return orderService.readOrders(requestDto, pageable);
    }

    @Override
    public OrderResponseDto readOrder(UUID orderId) {
        return orderService.readOrder(orderId);
    }

}