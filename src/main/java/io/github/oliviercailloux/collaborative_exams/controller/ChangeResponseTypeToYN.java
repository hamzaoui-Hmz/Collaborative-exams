package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

/**
 * Jax-RS Servlet that allows to change a true/false question to a Yes/No question
 */
@Path("ChangeResponseTypeToYN")

public class ChangeResponseTypeToYN {

	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;
	
	/**
	 * Using @FormParam inject form data in method arguments
	 * 
	 * @param idQuestion
	 * @param cookieIdAuthor
	 * @param authorIdFromCookie : contains the new authorId that can be null
	 * @return id of modified question
	 * @throws Exception if the type of the question is already TF or the type
	 *                   cannot be changed to TF or IdQuestion is invalid
	 */
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	
	public int getYNquestionID(@FormParam("idQuestion") String idQuestion, @FormParam("idAuthor") String newIdAuthor, @CookieParam("authorId") Cookie cookieIdAuthor)
			throws Exception {
		int QuestionId = Integer.valueOf(idQuestion);
		int newAuthorId;

		if (cookieIdAuthor == null) {
			if (newIdAuthor.isEmpty())
				throw new Exception ("Both Cookie and the input Author Id's field are null, please log-in or register again.");

			newAuthorId = Integer.valueOf(newIdAuthor);
		} else {
			newAuthorId = Integer.valueOf(cookieIdAuthor.getValue());
		}

		Question question = questionService.findQuestion(QuestionId);
		Person newAuthor = personService.findPerson(newAuthorId);

		Question modifiedQuestion;

		QuestionType questionType = question.getType();

		if (questionType == QuestionType.TF) {
			modifiedQuestion = new Question(question.getPhrasing(), question.getLanguage(), newAuthor, QuestionType.YN,
					question.getCorrect());

		} else if (questionType == QuestionType.YN) {
			throw new Exception("Question type is already YN ! ");
		} else
			throw new Exception("This type of question can't change");

		questionService.persist(modifiedQuestion);
		
		return Integer.valueOf(modifiedQuestion.getId());
	}
}
