package com.msa.fiveio.slack.model.repository;

import static com.msa.fiveio.common.config.QueryDslConfig.getUsableSize;
import static com.msa.fiveio.slack.model.entity.QSlacks.slacks;

import com.msa.fiveio.common.config.QueryDslConfig;
import com.msa.fiveio.slack.model.entity.Slacks;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page<Slacks> findSlacksSearchList(Pageable pageable, UUID id) {

		OrderSpecifier<?>[] orderSpecifiers = QueryDslConfig.getAllOrderSpecifierArr(pageable, slacks);

		List<Slacks> contents = jpaQueryFactory
			.select(slacks)
			.from(slacks)
			.where(
				slacks.id.eq(id)
			)
			.orderBy(orderSpecifiers)
			.offset(pageable.getOffset())
			.limit(getUsableSize(pageable.getPageSize()))
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(slacks.count())
			.from(slacks)
			.where(
				slacks.id.eq(id)
			);

		return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
	}
}
