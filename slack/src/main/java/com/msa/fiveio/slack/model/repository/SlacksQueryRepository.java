package com.msa.fiveio.slack.model.repository;

import static com.msa.fiveio.common.config.QueryDslConfig.getUsableSize;
import static com.msa.fiveio.slack.model.entity.QSlacks.slacks;

import com.msa.fiveio.common.config.QueryDslConfig;
import com.msa.fiveio.slack.model.entity.Slacks;
import com.msa.fiveio.slack.presentation.dto.SlacksSearchRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SlacksQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public Page<Slacks> findSlacksList(Pageable pageable) {

		OrderSpecifier<?>[] orderSpecifiers = QueryDslConfig.getAllOrderSpecifierArr(pageable, slacks);

		List<Slacks> contents = jpaQueryFactory
			.select(slacks)
			.from(slacks)
			.orderBy(orderSpecifiers)
			.offset(pageable.getOffset())
			.limit(getUsableSize(pageable.getPageSize()))
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(slacks.count())
			.from(slacks);

		return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
	}

	public Page<Slacks> findSlacksSearchList(Pageable pageable, SlacksSearchRequestDto.SlacksDto slacksDto) {

		int pageSize = validatePageSize(pageable.getPageSize());

		List<OrderSpecifier<?>> orderSpecifierList = dynamicOrder(pageable);

		List<Slacks> contents = jpaQueryFactory
			.selectFrom(slacks)
			.where(
				getSlacksForSearch(slacksDto)
			)
			.orderBy(orderSpecifierList.toArray(new OrderSpecifier[0]))
			.offset(pageable.getOffset())
			.limit(pageSize)
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(slacks.count())
			.from(slacks)
			.where(
				getSlacksForSearch(slacksDto)
			);

		return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
	}

	private BooleanBuilder getSlacksForSearch(SlacksSearchRequestDto.SlacksDto slacksDto) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (slacksDto.getMessage() != null && !slacksDto.getMessage().isEmpty()) {
			booleanBuilder.and(slacks.message.eq(slacksDto.getMessage()));
		}

		if (slacksDto.getDepartHubName() != null && !slacksDto.getDepartHubName().isEmpty()) {
			booleanBuilder.and(slacks.departHubName.eq(slacksDto.getDepartHubName()));
		}

		if (slacksDto.getTransitPoint() != null && !slacksDto.getTransitPoint().isEmpty()) {
			booleanBuilder.and(slacks.transitPoint.eq(slacksDto.getTransitPoint()));
		}

		if (slacksDto.getArriveHubName() != null && !slacksDto.getArriveHubName().isEmpty()) {
			booleanBuilder.and(slacks.arriveHubName.eq(slacksDto.getArriveHubName()));
		}

		if (slacksDto.getDeliveryAddress() != null && !slacksDto.getDeliveryAddress().isEmpty()) {
			booleanBuilder.and(slacks.deliveryAddress.eq(slacksDto.getDeliveryAddress()));
		}

		if (slacksDto.getRecipientName() != null && !slacksDto.getRecipientName().isEmpty()) {
			booleanBuilder.and(slacks.recipientName.eq(slacksDto.getRecipientName()));
		}

		if (slacksDto.getRecipientSlackId() != null && !slacksDto.getRecipientSlackId().isEmpty()) {
			booleanBuilder.and(slacks.recipientSlackId.eq(slacksDto.getRecipientSlackId()));
		}

		if (slacksDto.getCompanyDeliveryManager() != null && !slacksDto.getCompanyDeliveryManager().isEmpty()) {
			booleanBuilder.and(slacks.companyDeliveryManager.eq(slacksDto.getCompanyDeliveryManager()));
		}

		if (slacksDto.getProductName() != null && !slacksDto.getProductName().isEmpty()) {
			booleanBuilder.and(slacks.productName.eq(slacksDto.getProductName()));
		}

		if (slacksDto.getRequestNotes() != null && !slacksDto.getRequestNotes().isEmpty()) {
			booleanBuilder.and(slacks.requestNotes.eq(slacksDto.getRequestNotes()));
		}

		if (slacksDto.getSendStatus() != null ) {
			booleanBuilder.and(slacks.sendStatus.eq(slacksDto.getSendStatus()));
		}

		return booleanBuilder;
	}

	private int validatePageSize(int pageSize) {
		return Set.of(10, 30, 50).contains(pageSize) ? pageSize : 10;
	}


	private <T> JPAQuery<T> query(Expression<T> expr) {
		return jpaQueryFactory
			.select(expr)
			.from(slacks);

	}

	private List<OrderSpecifier<?>> dynamicOrder(Pageable pageable) {
		List<OrderSpecifier<?>> orderSpecifierList = new ArrayList<>();

		if (pageable.getSort() != null) {
			for (Sort.Order sortOrder : pageable.getSort()) {
				Order direction = sortOrder.isAscending() ? Order.ASC : Order.DESC;

				switch (sortOrder.getProperty()) {
					case "createdAt":
						orderSpecifierList.add(new OrderSpecifier<>(direction, slacks.createdAt));
						break;
					case "updatedAt":
						orderSpecifierList.add(new OrderSpecifier<>(direction, slacks.updatedAt));
						break;
					default:
						orderSpecifierList.add(new OrderSpecifier<>(Order.ASC, slacks.createdAt));
				}
			}
		} else {
			orderSpecifierList.add(new OrderSpecifier<>(Order.ASC, slacks.createdAt));
		}
		return orderSpecifierList;

	}
}
