package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

@Path("Multi")
public class QuestionMultiFormat {
	
	@Inject
	QuestionService questionService;
	
	@Path("QuestionMultiFormat")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getQuestion(MultivaluedMap<String, String> form, @CookieParam("authorId") Cookie cookieIdAuthor)
			throws Exception {
		
		int QuestionId = Integer.valueOf(form.getFirst("idQuestion"));
		int authId;
		String format = form.getFirst("format");
		
		if (cookieIdAuthor == null) {
			if (form.getFirst("idAuthor").isEmpty())
				throw new Exception("Both Cookie and the input Author Id's field are null, please log-in or register again.");
			authId = Integer.valueOf(form.getFirst("IdAuthor"));
		} else {
			authId = Integer.valueOf(cookieIdAuthor.getValue());
		}
		
		Question question = questionService.findQuestion(QuestionId);
		
		if (format == "Json") {
			System.out.println(form.getFirst("idQuestion"));
			return QuestionText.QuestionToJson(question);
		}	
		else if (format == "XML")
			return QuestionText.questionToXML(question);
		else
			throw new Exception("Invalide format.");
	}	
}