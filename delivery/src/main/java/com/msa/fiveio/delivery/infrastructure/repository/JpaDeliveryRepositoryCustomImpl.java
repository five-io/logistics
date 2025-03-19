package com.msa.fiveio.delivery.infrastructure.repository;

import static com.msa.fiveio.delivery.model.entity.QDelivery.delivery;

import com.msa.fiveio.common.config.QueryDslConfig;
import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.presentation.dto.request.DeliverySearchRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class JpaDeliveryRepositoryCustomImpl implements JpaDeliveryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Delivery> readDeliveries(DeliverySearchRequestDto requestDto, Pageable pageable) {
        OrderSpecifier<?>[] orderSpecifiers = QueryDslConfig.getAllOrderSpecifierArr(pageable,
            delivery);
        BooleanBuilder builder = createBooleanBuilder(requestDto);

        JPAQuery<Delivery> baseQuery = queryFactory
            .selectFrom(delivery)
            .where(builder);

        List<Delivery> fetch = baseQuery
            .orderBy(orderSpecifiers)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long totalCount = getTotalCount(builder);

        return new PageImpl<>(fetch, pageable, totalCount);
    }

    private BooleanBuilder createBooleanBuilder(DeliverySearchRequestDto requestDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (requestDto.getDeliveryStatus() != null) {
            builder.and(delivery.deliveryStatus.eq(requestDto.getDeliveryStatus()));
        }
        if (requestDto.getDepartHubId() != null) {
            builder.and(delivery.departHubId.eq(requestDto.getDepartHubId()));
        }
        if (requestDto.getArriveHubId() != null) {
            builder.and(delivery.arriveHubId.eq(requestDto.getArriveHubId()));
        }
        if (requestDto.getDeliveryAddress() != null) {
            builder.and(delivery.deliveryAddress.contains(requestDto.getDeliveryAddress()));
        }
        if (requestDto.getRecipientName() != null) {
            builder.and(delivery.recipient.recipientName.contains(requestDto.getRecipientName()));
        }
        if (requestDto.getRecipientSlackId() != null) {
            builder.and(delivery.recipient.slackId.contains(requestDto.getRecipientSlackId()));
        }
        if (requestDto.getCompanyDeliveryManager() != null) {
            builder.and(
                delivery.companyDeliveryManagerId.eq(requestDto.getCompanyDeliveryManager()));
        }
        if (requestDto.getOrderId() != null) {
            builder.and(delivery.orderId.eq(requestDto.getOrderId()));
        }

        return builder;
    }

    private Long getTotalCount(BooleanBuilder builder) {
        return Optional.ofNullable(
            queryFactory.select(Wildcard.count)
                .from(delivery)
                .where(builder)
                .fetchOne()
        ).orElse(0L);
    }
}
