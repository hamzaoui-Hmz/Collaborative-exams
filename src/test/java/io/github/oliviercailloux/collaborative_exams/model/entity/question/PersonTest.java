package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

public class PersonTest {
	
	private Person person;
	private String email;
	private List<Question> liste;
	
	private QuestionType questionType;
	private String language;
	private String phrasing1;
	private String phrasing2;
	private Question question1;
	private Question question2;
	
	@Before
	public void setUp() {
		email = "personne@gmail.com";
		person = new Person(email);
		
		language = "Francais";
		phrasing1= "Est ce que Paris est la capitale de la france ?";
		phrasing2= "combien fait 3*5 ?";
		question1= new Question(phrasing1,language, person,questionType.TF,true);
		question2= new Question(phrasing2,language, person,questionType.TF,true);
		
		liste= new ArrayList<>();
		liste.add(question1);
		liste.add(question2);
	}
	
	@Test
	public void testgetEmail() throws Exception {
		assertEquals(person.getEmail(), email);
	}
	
	@Test
	public void testgetId() throws Exception {
		assertEquals(person.getId(), 0);
	}
	
	@Test
	public void testgetQuestions() throws Exception {
		assertEquals(person.getQuestions(), null);
	}
}
