package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.SameAbilityService;

@Path("NewSameAbility")
public class DeleteSameAbility {
	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;

	@Inject
	private SameAbilityService sameAbilityService;

	@POST
	public void deleteSameAbility(@QueryParam("idQuestion1") Integer idQuestion1,
			@QueryParam("idQuestion2") Integer idQuestion2, @QueryParam("idAuthor") Integer idAuthor) throws Exception {

		if (questionService.findQuestion(idQuestion1) == null)
			throw new NotFoundException("the question id :" + idQuestion1 + " doesn't exist.");

		if (questionService.findQuestion(idQuestion2) == null)
			throw new NotFoundException("the question id :" + idQuestion2 + " doesn't exist.");

		if (personService.findPerson(idAuthor) == null)
			throw new NotFoundException("the author id :" + idAuthor + " doesn't exist.");

		if (idQuestion1 == idQuestion2) {
			throw new IllegalArgumentException("You indicated the same id Question for both of the questions.");

		}

		/*
		 * Test if the object exists in database
		 */

		if (sameAbilityService.isSameAbility(personService.findPerson(idAuthor),
				questionService.findQuestion(idQuestion1), questionService.findQuestion(idQuestion2))) {

			/*
			 * Delete object from database
			 */
			sameAbilityService.deleteSameAbility(personService.findPerson(idAuthor),
					questionService.findQuestion(idQuestion1), questionService.findQuestion(idQuestion2));
		} else {
			throw new NotFoundException("Object doesn't exist in database");
		}
	}
}
