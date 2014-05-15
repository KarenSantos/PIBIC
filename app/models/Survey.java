package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

/**
 * Survey class.
 * 
 * @author Karen
 * 
 */
@Entity
public class Survey extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "survey_questions", joinColumns = @JoinColumn(name = "survey_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> questions;

	public static Finder<String, Survey> find = new Finder<String, Survey>(
			String.class, Survey.class);

	/**
	 * Creates an empty survey.
	 */
	public Survey() {
	}

	/**
	 * Creates a survey with an id.
	 * 
	 * @param id
	 *            The id of the survey.
	 */
	public Survey(String id) {
		this.id = id;
		this.questions = new ArrayList<Question>();
	}

	/**
	 * Creates a survey with the corresponding playlist id and a list of
	 * questions.
	 * 
	 * @param playlistId
	 *            The corresponding playlist id.
	 * @param questions
	 *            The list of questions for the survey.
	 */
	public Survey(String playlistId, List<Question> questions) {
		this.id = playlistId;
		this.questions = questions;
	}

	/**
	 * Returns the id of the survey which is the playlist id as well.
	 * 
	 * @return Returns the id of the survey.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the id of the survey which is the playlist id.
	 * 
	 * @param playlistId
	 *            The new id.
	 */
	public void setId(String playlistId) {
		this.id = playlistId;
	}

	/**
	 * Returns the list of questions of the survey.
	 * 
	 * @return The list of questions.
	 */
	public List<Question> getQuestions() {
		return Collections.unmodifiableList(this.questions);
	}

	/**
	 * Sets the list of questions for the survey.
	 * 
	 * @param questions
	 *            The new list of questions.
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * Returns the question ordered with the given number from the questions
	 * list.
	 * 
	 * @param num
	 *            The number of the question from the list starting with 1.
	 * @return The question with the given number.
	 * @throws NumeroInvalidoException
	 *             If the given number is not a valid question.
	 */
	public Question getQuestion(int num) throws NumeroInvalidoException {
		if (num < 0 || num > questions.size()) {
			throw new NumeroInvalidoException("Não existe pergunta com o número indicado.");
		}
		return this.questions.get(num - 1);
	}

	/**
	 * Adds a question to the list of questions of the survey.
	 * 
	 * @param question
	 *            A new question to be added.
	 */
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
}
