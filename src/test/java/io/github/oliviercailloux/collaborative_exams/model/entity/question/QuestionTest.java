package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

public class QuestionTest {

	private Person person ;
	private QuestionType questionType;
	private String language = "Francais";
	private String phrasing= "Est ce que Paris est la capitale de la france?";
	private Question question;
	private List<Answer> answers;
	private String text1;
	private String text2;
	private Answer answer1;
	private Answer answer2;
	
	@Before
	public void setUp() {
		person = new Person("Personne@outlook.fr");
		question= new Question(phrasing,language, person,questionType.TF,true);
		text1 = "réponse1";
		text2 = "réponse2";
		answer1 = new Answer(text1, true);
		answer2 = new Answer(text2, true);
		answers = new ArrayList<>();
		answers.add(answer1);
		answers.add(answer2);
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
	
	@Test
	public void testgetType() throws Exception {
		assertEquals(question.getType(), questionType.TF);
	}
	
	@Test
	public void testgetAnswers() throws Exception {
		assertEquals(question.getAnswers(), new ArrayList());
	}
}
