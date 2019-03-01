package io.github.oliviercailloux.collaborative_exams.helper;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class QueryHelper2 {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public <T> CriteriaQuery<T> selectAll(Class<T> type) {
		final CriteriaBuilder criteriaBuilder = emf.getCriteriaBuilder();
		final CriteriaQuery<T> query = criteriaBuilder.createQuery(type);
		final Root<T> from = query.from(type);
		query.select(from);
		return query;
	}

}