package io.github.oliviercailloux.collaborative_exams.helper;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class QueryHelper {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public QueryHelper() {
		 emf = Persistence.createEntityManagerFactory("myDbFile.odb");
	}

	public <T> CriteriaQuery<T> selectAll(Class<T> type) {
		final CriteriaBuilder criteriaBuilder = emf.getCriteriaBuilder();
		final CriteriaQuery<T> query = criteriaBuilder.createQuery(type);
		final Root<T> from = query.from(type);
		query.select(from);
		return query;
	}

}