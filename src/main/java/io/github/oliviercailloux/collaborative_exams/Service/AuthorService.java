package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Author;

@RequestScoped
public class AuthorService {

	private QueryHelper helper;


	@PersistenceContext
	private EntityManager em;
	
	
	public EntityManager getEm() {
		return em;
	}


	public AuthorService(QueryHelper qh)
	{
		em = qh.getEmf().createEntityManager();
	}

	@Transactional
	public List<Author> getAll() {
		return em.createQuery(helper.selectAll(Author.class)).getResultList();
	}

	@Transactional
	public void persist(Author author) {

		 em.getTransaction().begin();
		 em.persist(author);
		 em.getTransaction().commit();
	}

	@Transactional
	public Author findAuthor(int id) {
		return em.find(Author.class, id);
	}
	
	public Author findAuthorByEmail(String email, String mdp)
	{
		TypedQuery<Author> query = this.getEm().createQuery("SELECT a FROM Author a where a.mail ='"+ email + "' and a.mdp = '"+ mdp +"'", Author.class);
	    //La liste doit impérativement comporter qu'un seul élément 
		List<Author> results = query.getResultList();
		System.out.println(results.size());
	    if (results.size()>0)
			return results.get(0);
	    else
	    	return null;
	    
	}

}
