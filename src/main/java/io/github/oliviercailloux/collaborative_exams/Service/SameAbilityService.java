package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@RequestScoped
public class SameAbilityService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<SameAbility> getAll() {
		return em.createQuery(helper.selectAll(SameAbility.class)).getResultList();
	}

	@Transactional
	public void persist(SameAbility same) {

		em.persist(same);

	}

	@Transactional
	public SameAbility findSameAbility(int id) {
		return em.find(SameAbility.class, id);
	}

	@Transactional
	public boolean isSameAbility(Person idAuthor, Question idQuestion1, Question idQuestion2) {

		/*
		 * idQuestion1 < idQuestion2
		 */

		// The relation is reflexive
		if (idQuestion1.getId() == idQuestion2.getId())
			return true;

		TypedQuery<SameAbility> query;

		if (idQuestion1.getId() < idQuestion2.getId()) {
			query = em.createQuery(
					"SELECT s FROM SameAbility s WHERE (s.author = :idAuthor and) ((s.question1 = :idQuestion1 and s.question2 = :idQuestion2))",
					SameAbility.class);

			query.setParameter("idAuthor", idAuthor);
			query.setParameter("idQuestion1", idQuestion1);
			query.setParameter("idQuestion2", idQuestion2);

		} else {
			query = em.createQuery(
					"SELECT s FROM SameAbility s WHERE (s.author = :idAuthor and) ((s.question1 = :idQuestion2 and s.question2 = :idQuestion1))",
					SameAbility.class);

			query.setParameter("idAuthor", idAuthor);
			query.setParameter("idQuestion1", idQuestion2);
			query.setParameter("idQuestion2", idQuestion1);
		}

		List<SameAbility> results = query.getResultList();
		if (results.isEmpty())
			return false;
		else
			return true;
	}
	
	@Transactional
	public void deleteSameAbility(Person idAuthor, Question idQuestion1, Question idQuestion2) {
		if (idQuestion1.getId() < idQuestion2.getId()) {
			em.createQuery(
					"DELETE FROM SameAbility s WHERE ((s.author = :idAuthor) and (s.question1 = :idQuestion1) and (s.question2 = :idQuestion2))",
					SameAbility.class);
			
		} else {
			em.createQuery(
					"DELETE FROM SameAbility s WHERE ((s.author = :idAuthor) and (s.question1 = :idQuestion2) and (s.question2 = :idQuestion1))",
					SameAbility.class);
		}
	}
}