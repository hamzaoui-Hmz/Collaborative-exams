package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.persistence.PostUpdate;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;



/**
 * @author Mohamed
 * Servlet that allows to delete questions from DB 
 */
@Path("Delete")
public class DeletQuestion {
	private boolean isdelet ; 
	private QuestionText questiontext;

	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;
	
	
	
	@Path("allQuestions")
	@PostUpdate
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String questDeleted() throws Exception {
		
		this.isdelet = questionService.deletAllQuestion();
		if(isdelet==false)
			throw new Exception("Questions is not deleted from DB");
		return questiontext.MessageTonJson(new ResultMessage("OK.Quesitons is deleted", 200));
		
	
		
	}
	
	@Path("/byId")
	@PostUpdate
	@Consumes(MediaType.TEXT_PLAIN)
	public String  questionDeletedById(@QueryParam("id") int id) throws Exception {
		this.isdelet=questionService.deletById(id);
		if(isdelet==false)
			throw new Exception("Questions is not deleted from DB");
		return questiontext.MessageTonJson(new ResultMessage("OK.Quesitons is deleted", 200));
	}
	
	
	@Path("/byAuthor")
	@PostUpdate
	@Consumes(MediaType.TEXT_PLAIN)
	public String  questionDeletedByAuthor(@QueryParam("person") Person person) throws Exception {
	this.isdelet=	questionService.deletByAuthor(person);
		if(isdelet==false)
			throw new Exception("Questions is not deleted from DB");
		return questiontext.MessageTonJson(new ResultMessage("OK. Quetion deleted", 200));
	}
}
