package io.github.oliviercailloux.collaborative_exams.helper;

import java.io.StringWriter;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

public class QuestionText {

	public static Question JsonToQuestion(String json) throws Exception {

		try (Jsonb jsonb = JsonbBuilder
				.create(new JsonbConfig().withFormatting(true).withAdapters(new QuestionAdapter()))) {

			Question question = jsonb.fromJson(json, Question.class);
			return question;
		}
	}

	public static String QuestionToJson(Question question) throws Exception {

		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true))) {
			return jsonb.toJson(question);
		}
	}

	public static <T> T JsonToObject(Class<T> className, String json) throws Exception {
		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true).withNullValues(true))) {
			T objectMarshelled = jsonb.fromJson(json, className);

			return objectMarshelled;
		}
	}

	public static <T> String ObjectToJson(Class<T> className, T objectT) throws Exception {
		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true))) {
			return jsonb.toJson(objectT);
		}
	}

	public static String QuestionsToJson(List<Question> question) throws Exception {

		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true))) {
			return jsonb.toJson(question);
		}
	}
    
	public static String MessageTonJson(Object o) throws Exception{
		 ObjectMapper mapperObj = new ObjectMapper();   
	            String jsonStr = mapperObj.writeValueAsString(o);
	            return jsonStr;
	           
	}
	
	public static String questionToXML(Question question) {
	    String xmlQuestion = "";
	    try {
	        JAXBContext context = JAXBContext.newInstance(Question.class);
	        Marshaller m = context.createMarshaller();

	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

	        StringWriter sw = new StringWriter();
	        m.marshal(question, sw);
	        xmlQuestion = sw.toString();

	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }

	    return xmlQuestion;
	}
}
