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
 * This Class represent a Author Author is Immuable
 *
 * @author Amine
 */
@JsonbPropertyOrder({ "id", "email" })
@Entity
@XmlRootElement(name = "Author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	/**
	 * Represents the Author's Id
	 */
	private int id;
	

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(mappedBy = "author")
	private List<Question> questions;

	/**
	 * Represents the Author's email
	 */
	@Column(nullable = false)
	@XmlElement
	private String email;
	
	
	@Column(nullable = false)
	@XmlElement
	private String mdp;

	public Author() {

	}

	/**
	 * Construct a Author
	 *
	 * @param email
	 */
	public Author(String email ,String mdp) {

		this.email = email;
		this.mdp = mdp;

	}

	/**
	 * Returns this Author Id.
	 *
	 * @return Int not <code>null</code>.
	 */

	public int getId() {
		return id;
	}

	/**
	 * Returns this Author email.
	 *
	 * @return String not <code>null</code>.
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * @return this Author's questions list
	 */
	@JsonbTransient
	public List<Question> getQuestions() {

		return this.questions;

	}

}
