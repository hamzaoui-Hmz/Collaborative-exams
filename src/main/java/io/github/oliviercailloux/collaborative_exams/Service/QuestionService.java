package io.github.oliviercailloux.collaborative_exams.Service;


/**
 * @author modified by Mohamed
 */
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@RequestScoped
public class QuestionService {


	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<Question> getAll() {
		return em.createQuery(helper.selectAll(Question.class)).getResultList();
	}
	
	@Transactional
	public void persist(Question question) {
		if (null == em.find(Person.class, question.getAuthor().getId())) {
			em.persist(question.getAuthor());
		}
		em.persist(question);

		for (Answer a : question.getAnswers()) {
			em.persist(a);

		}

	}

	@Transactional
	public Question findQuestion(int id) throws Exception {
		Question questionResult = em.find(Question.class, id);
		if (questionResult == null)
			throw new Exception("Aucune question correspondante.");

		return questionResult;
	}
	
	@Transactional
	public boolean deletAllQuestion() throws Exception {
		//TypedQuery<Question> query = em.createQuery("SELECT * FROM Question);
		List<Question> question = em.createQuery(helper.selectAll(Question.class)).getResultList();
		
		for(Question q : question){
			Question QuestionDeleted =em.merge(q);
			em.remove(QuestionDeleted);
		}
		if (!question.isEmpty())
			return false;
		return true;
			
		
	
	}
	
	@Transactional
	public boolean deletById(int id) throws Exception{
		Question questionResult = em.find(Question.class, id);
		if (questionResult == null)
			return false;
		em.merge(questionResult);
		em.remove(questionResult);
		return true;
	}
	

	@Transactional
	public boolean deletByAuthor(Person p) throws Exception{
		Question questionResult = em.find(Question.class, p);
		if (questionResult == null)
			return false;
		em.merge(questionResult);
		em.remove(questionResult);
		return false;
	}
   
}
