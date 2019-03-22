package io.github.oliviercailloux.collaborative_exams.helper;




import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import com.sun.istack.NotNull;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

public class QuestionTest {
	private String jsonQuestion ;
	private Question q1;
	private Answer ans;
	private Person p1,person2;
	@Before
	@NotNull
	public void setUp() throws Exception {

	    p1 = new Person();
	    person2=new Person("mohamed.hamzaoui@dauphine.eu");
	    ans =new Answer("True", true);
		q1 =new Question("2 * 2 = 4 ?", "Francais", p1, QuestionType.TF, ans);	
	}


	@Test
	public void testQuestionToJson() throws Exception{
		jsonQuestion= QuestionText.QuestionToJson(q1);
		assertNotNull(jsonQuestion);
		JsonReader reader = Json.createReader(new StringReader(jsonQuestion));
		JsonObject jsonObject = reader.readObject();
		assertTrue(jsonObject.containsKey("id"));

	}
	@Test
	public void testJsonToQuestion() throws Exception{
    jsonQuestion  =" {\"id\":0,\"author\":{\"id\":0},\"phrasing\":\"2 * 2 = 4 ?\",\"language\":\"Francais\",\"type\":\"TF\",\"answers\":[{\"id\":0,\"text\":\"True\",\"correct\":true}],\"correct\":false}";
	Question testQuestionconvert= 	QuestionText.JsonToQuestion(jsonQuestion);
	assertNotNull(testQuestionconvert);

	}

//	 @Test
//	 public void testObjectToXml() throws NullPointerException, IllegalArgumentException, Exception {
//		     QuestionXml.QuestionToXML(q1,"questionTest.xml");
//	    }
//
//	 @Test
//	 public void testXmlToObject() throws JAXBException, FileNotFoundException {
//		  QuestionXml.unmarshallerXml(q1, "questionTest.xml");
//	    }

	   @Test
		public void testJsonToObject() throws Exception{
			jsonQuestion  =" {\"id\":0,\"author\":{\"id\":0},\"phrasing\":\"2 * 2 = 4 ?\",\"language\":\"Francais\",\"type\":\"TF\",\"answers\":[{\"id\":0,\"text\":\"True\",\"correct\":true}],\"correct\":false}";
			Object jsOb = QuestionText.JsonToObject(Question.class, jsonQuestion);
			assertNotNull(jsOb);
			
			
		}

	  @Test
	  public void testObjectToJson() throws Exception{
	    String JsonObejct= QuestionText.ObjectToJson(Person.class,person2);
	     assertNotNull(JsonObejct);
		JsonReader reader = Json.createReader(new StringReader(JsonObejct));
		JsonObject jsonObject = reader.readObject();
		assertTrue(jsonObject.containsKey("id"));
		assertEquals(person2.getEmail(),jsonObject.getString("email"));
	  }
	}