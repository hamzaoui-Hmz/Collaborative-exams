package io.github.oliviercailloux.collaborative_exams.controller;

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

import io.github.oliviercailloux.collaborative_exams.helper.QuestionXML;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

@RunWith(Arquillian.class)
public class GetQuestionTest {
	
	private Question question;
	private Person person ;
	private QuestionType questionType;
	private String language = "Francais";
	private String parshing= "Est ce que 2 * 4 = 12 ?";
	private QuestionXML questionXml;
	private String type = "TF";

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(GetQuestionTest.class.getCanonicalName());

	@Deployment(testable = false)
	public static WebArchive createDeployment() {

		final WebArchive war = ShrinkWrap.create(WebArchive.class, "collaborative-exams-0.0.1.war")
				.addPackage(GetQuestionTest.class.getPackage());
		return war;
	}

    @ArquillianResource
 	private URL baseURL;
    
	@Before
    public void setUp() {
	    person = new Person("Personne@outlook.fr");
        question = new Question(parshing,language, person,questionType.TF,true);
        LOGGER.info("Creat person && question before test");
    }
	
	@Test
	public void getQuestionServletTest() throws Exception {
		final Client client = ClientBuilder.newClient();
        assertEquals(question.getPhrasing(),this.parshing);
        assertEquals(question.getLanguage(),this.language);
        assertEquals(question.getAuthor(),this.person);
        
		client.close();
	}

}