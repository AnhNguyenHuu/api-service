package com.example.demo.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Component
public class ReferenceObjectSpecification<T> {
	public Specification<T> searchReact(final String title) {
		return (root, cq, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(title)) {
				predicates.add(cb.like(root.get("title"), "%" + title.trim() + "%"));
			}
			return cb.and(predicates.stream().toArray(Predicate[]::new));
		};
	}
}
