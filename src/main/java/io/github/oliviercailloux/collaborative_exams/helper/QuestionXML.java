package io.github.oliviercailloux.collaborative_exams.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

/**
 * @author Mohamed 
 */
public class QuestionXML {
	//@SuppressWarnings("unused")
	//private static final Logger LOGGER = Logger.getLogger(GetQuestionTest.class.getCanonicalName());

	public QuestionXML(){
		super();
	}

	public static void QuestionToXML(Question question, String FILEPATH) throws NullPointerException, IllegalArgumentException, Exception {
		try {
			SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			System.out.println("ok");

			/**
			 *  creat JAXB  for  class Question
			 */
			JAXBContext context = JAXBContext.newInstance(Question.class);

			/**
			 * Creat marshaller context
			 */
			Marshaller marshaller = context.createMarshaller();

			/**
			 *  Marshaller will use UTF-8 encoding when generating XML data 
			 */

			marshaller.setProperty("jaxb.encoding", "UTF-8") ;

			/**
			 * setting the property to show xml format output
			 * tells the Marshaller to generate XML with the appropriate indentation
			 * 
			 */
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//marshaller.setSchema(schema);

			/**
			 * write the XML file on the fileName
			 */
			marshaller.marshal(question, new File(FILEPATH));
		} catch (JAXBException ex) {
			ex.printStackTrace();
			//LOGGER.warning("Question non sérialiser");
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			//LOGGER.warning("Question non sérialiser");
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			//LOGGER.warning("Question non sérialiser");
		}
	}


    /**
     * 
     * @param question
     * @param FILEPATH
     *
     * Delet XML with JAXB in a objet Java,
     * @return 
     * 
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Question unmarshallerXml(Question question,String FILEPATH) throws JAXBException, FileNotFoundException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Question questiontUnmarshalled = (Question) jaxbUnmarshaller.unmarshal(new FileReader(FILEPATH));
		return questiontUnmarshalled;

	}

	
	

}
