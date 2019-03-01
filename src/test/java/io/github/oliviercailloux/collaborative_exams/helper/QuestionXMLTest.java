package io.github.oliviercailloux.collaborative_exams.helper;



import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.dom4j.Element;
import org.dom4j.Document;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

/**
 * @author Mohamed 
 */
public class QuestionXMLTest {
	
	private Question question;
	private Person person ;
	private QuestionType questionType;
	private String language = "Francais";
	private String parshing= "Est ce que 2 * 4 = 12 ?";
	private QuestionXML questionXml;
	private String type = "TF";
	
	
	  @Before
	    public void setUp() {
		    person = new Person("Personne@outlook.fr");
            question = new Question(parshing,language, person,questionType.TF,true);
	    }
	 
	    @After
	    public void tearDown() throws FileNotFoundException, JAXBException {
	    	question = QuestionXML.unmarshallerXml(question, "src/main/java/io/github/oliviercailloux/collaborative_exams/controller/questionXMl");
	    }
	     

	    @Test
	    public void testObjectToXml() throws NullPointerException, IllegalArgumentException, Exception {
	    	QuestionXML.QuestionToXML(question, "src/main/java/io/github/oliviercailloux/collaborative_exams/controller/questionXMl");
	    	File fic = new File("questionXMl");
	    	SAXReader reader =  new SAXReader() ;
	    	 /**
	    	  *  read this file using this reader, and build a Document object
	    	  */
	    	
	    	Document doc = reader.read("src/main/java/io/github/oliviercailloux/collaborative_exams/controller/questionXMl") ;
	    	
	    	/**
	    	 * constructing the root element of the XML document
	    	 * 
	    	 */
	    	Element root = doc.getRootElement() ;

	    	 /** 
	    	  * read attributes of the root element
	    	  * 
	    	  */
	    	List<Element> attributes = root.attributes() ;

	    	 /**
	    	  *  reading the sub-element of the root
	    	  * 
	    	  */
	    	List<Element> elements = root.elements() ;
	    	assertEquals(root.elementText("phrasing"),this.parshing);
	    	assertEquals(root.elementText("language"),this.language);
	    	assertEquals(root.elementText("type"),this.type);
	    	
	    	
	    	
	    	
	    	//System.out.println(attributes.get(0)+"\n");
	    	//System.out.println(elements.get(0)+"\n");
	 
	    

	    }
}
