package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.SameAbilityService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;

/**
 * Jax-RS servlet that allows to get a same ability relation between two
 * question for an author !
 * 
 * @author Sid
 *
 */
@Path("GetSameAbility")
public class GetSameAbility {

	@Inject
	SameAbilityService sameAbilityService;

	@Inject
	QuestionService questionService;

	@Inject
	PersonService personService;

	@Path("/{idQuestion1}/{idQuestion2}/{idAuthor}")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getSameAbility(@PathParam("idAuthor") int idAuthor, @PathParam("idQuestion1") int idQuestion1,
			@PathParam("idQuestion2") int idQuestion2) throws Exception {

		SameAbility sameAbility = sameAbilityService.isSameAbility(personService.findPerson(idAuthor),
				questionService.findQuestion(idQuestion1), questionService.findQuestion(idQuestion2));
		return QuestionText.ObjectToJson(SameAbility.class, sameAbility);

	}
}
