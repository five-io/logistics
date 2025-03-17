package com.msa.fiveio.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

	public static PageRequest getNormalPageable(Integer page, Integer size, String orderby, String sort) {

		Sort sortAndOrderBy = Sort.by(OrderBy.from(orderby).fieldName);
		sort = sort != null ? sort.toUpperCase() : "DESC";
		sortAndOrderBy = "ASC".equals(sort) ? sortAndOrderBy.ascending() : sortAndOrderBy.descending() ;
		if (page == null) {
			page = 1;
		}
		if (size == null) {
			size = 10;
		}
		return PageRequest.of(page - 1 , getUsableSize(size), sortAndOrderBy);
	}

	private static int getUsableSize(int size) {
		switch (size) {
			case 30 -> {
				return 30;
			}
			case 50 -> {
				return 50;
			}
			default -> {
				return 10;
			}
		}
	}

	@Getter
	@AllArgsConstructor
	public enum OrderBy {
		CREATED("createdAt"),
		UPDATED("updatedAt");

		private final String fieldName;

		public static OrderBy from(String value) {
			for (OrderBy type : OrderBy.values()) {
				if (type.name().equalsIgnoreCase(value)) {
					return type;
				}
			}
			throw new IllegalArgumentException("orderBy 는 CREATED 또는 UPDATED 여야 합니다.");
		}
	}
}
