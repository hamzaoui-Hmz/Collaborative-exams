package io.github.oliviercailloux.collaborative_exams.model.entity;

import java.util.List;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

/**
 * This Class represent a Person Person is Immuable
 *
 * @author badga & Sid
 */
@JsonbPropertyOrder({ "id", "email" })
@Entity
@XmlRootElement(name = "author")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	/**
	 * Represents the person's Id
	 */
	private int id;
	

	@OneToMany(mappedBy = "author")
	private List<Question> questions;

	/**
	 * Represents the person's email
	 */
	@Column(nullable = false)
	@XmlElement
	private String email;
	
	
	@Column(nullable = false)
	@XmlElement
	private String mdp;

	public Person() {

	}

	/**
	 * Construct a Person
	 *
	 * @param email
	 */
	public Person(String email) {

		this.email = email;

	}

	/**
	 * Returns this Person Id.
	 *
	 * @return Int not <code>null</code>.
	 */

	public int getId() {
		return id;
	}

	/**
	 * Returns this Person email.
	 *
	 * @return String not <code>null</code>.
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * @return this person's questions list
	 */
	@JsonbTransient
	public List<Question> getQuestions() {

		return this.questions;

	}

}
