package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

public class QuestionTest {

	private Person person ;
	private QuestionType questionType;
	private String language = "Francais";
	private String phrasing= "Est ce que Paris c'est la capitale de la france?";
	private Question question;
	
	@Before
	public void setUp() {
		person = new Person("Personne@outlook.fr");
		question= new Question(phrasing,language, person,questionType.TF,true);
	}
	
	@Test
	public void testgetLanguage() throws Exception {
		assertEquals(question.getLanguage(), language);
	}
	
	@Test
	public void testgetAuthor() throws Exception {
		assertEquals(question.getAuthor(), person);
	}
	
	@Test
	public void testgetPhrasing() throws Exception {
		assertEquals(question.getPhrasing(), phrasing);
	}
}
