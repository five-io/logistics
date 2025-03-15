
package com.msa.fiveio.hub.infrastructure.repository;


import static com.msa.fiveio.hub.model.entity.QHubs.hubs;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class JpaHubsRepositoryCustomImpl implements JpaHubsRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Hubs> searchHubs(HubsRequestDto hubsRequestDto, Pageable pageable) {

        int pageSize = validatePageSize(pageable.getPageSize());

        List<OrderSpecifier<?>> orderSpecifierList = dynamicOrder(pageable);

        BooleanBuilder builder = createBooleanBuilder(hubsRequestDto);

        JPAQuery<Hubs> baseQuery = query(hubs).where(builder);

        List<Hubs> fetch = baseQuery
            .orderBy(orderSpecifierList.toArray(new OrderSpecifier[0]))
            .offset(pageable.getOffset())
            .limit(pageSize)
            .fetch();

        Long totalCount =query(hubs)
            .where(builder)
            .select(Wildcard.count)
            .fetchOne();


        if (totalCount == null) {
            totalCount = 0L;
        }

        return new PageImpl<>(fetch, pageable, totalCount);
    }

    private BooleanBuilder createBooleanBuilder(HubsRequestDto hubsRequestDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (hubsRequestDto.hubName() != null && !hubsRequestDto.hubName().isEmpty()) {
            builder.and(hubs.hubName.containsIgnoreCase(hubsRequestDto.hubName()));
        }
        if (hubsRequestDto.address() != null && !hubsRequestDto.address().isEmpty()) {
            builder.and(hubs.address.containsIgnoreCase(hubsRequestDto.address()));
        }
        if (hubsRequestDto.latitude() != null) {
            builder.and(hubs.latitude.eq(hubsRequestDto.latitude()));
        }
        if (hubsRequestDto.longitude() != null) {
            builder.and(hubs.longitude.eq(hubsRequestDto.longitude()));
        }
        return builder;
    }

    private int validatePageSize(int pageSize) {
        return Set.of(10, 30, 50).contains(pageSize) ? pageSize : 10;
    }


    private <T> JPAQuery<T> query(Expression<T> expr) {
        return queryFactory
            .select(expr)
            .from(hubs);

    }


 private List<OrderSpecifier<?>> dynamicOrder(Pageable pageable) {
        List<OrderSpecifier<?>> orderSpecifierList = new ArrayList<>();

        if (pageable.getSort() != null) {
            for (Sort.Order sortOrder : pageable.getSort()) {
                Order direction = sortOrder.isAscending() ? Order.ASC : Order.DESC;

                switch (sortOrder.getProperty()) {
                    case "createdAt":
                        orderSpecifierList.add(new OrderSpecifier<>(direction, hubs.createdAt));
                        break;
                    case "updatedAt":
                        orderSpecifierList.add(new OrderSpecifier<>(direction, hubs.updatedAt));
                        break;
                    default:
                        orderSpecifierList.add(new OrderSpecifier<>(Order.ASC, hubs.createdAt));
                }
            }
        } else {
            orderSpecifierList.add(new OrderSpecifier<>(Order.ASC, hubs.createdAt));
        }
        return orderSpecifierList;

    }



}

