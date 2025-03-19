package com.msa.fiveio.hub.infrastructure.repository;


import static com.msa.fiveio.common.config.QueryDslConfig.getUsableSize;
import static com.msa.fiveio.hub.model.entity.QHubs.hubs;

import com.msa.fiveio.common.config.QueryDslConfig;
import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class JpaHubsRepositoryCustomImpl implements JpaHubsRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Hubs> searchHubs(HubsRequestDto hubsRequestDto, Pageable pageable) {

        OrderSpecifier<?>[] orderSpecifiers = QueryDslConfig.getAllOrderSpecifierArr(pageable,
            hubs);

        BooleanBuilder builder = createBooleanBuilder(hubsRequestDto);

        JPAQuery<Hubs> baseQuery = query(hubs).where(builder);

        List<Hubs> fetch = baseQuery
            .orderBy(orderSpecifiers)
            .offset(pageable.getOffset())
            .limit(getUsableSize(pageable.getPageSize()))
            .fetch();

        Long totalCount = query(hubs)
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

    private <T> JPAQuery<T> query(Expression<T> expr) {
        return queryFactory
            .select(expr)
            .from(hubs);
    }


}

