package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(name = "survey_questions", joinColumns = @JoinColumn(name = "survey_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> questions;

	public static Finder<String, Survey> find = new Finder<String, Survey>(
			String.class, Survey.class);
	
	/**
	 * Creates a survey with blank id and empty list of questions.
	 */
	public Survey() {
		this.id = "";
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
	 * Adds a question to the list of questions of the survey.
	 * 
	 * @param question
	 *            A new question to be added.
	 */
	public void addQuestion(Question question) {
		this.questions.add(question);
	}

	/**
	 * Sets an answer for one specific questions of the survey.
	 * 
	 * @param question
	 *            The number of the question in the list starting with 1.
	 * @param answer
	 *            The number of the question option to be the answer starting
	 *            with 1.
	 */
	public void setAnswerToQuestion(int question, int answer) {
		this.questions.get(question - 1).setAnswer(answer);
	}

	/**
	 * Sets all the answers for all the questions of the survey.
	 * 
	 * @param answers
	 *            The array with all the answers for the questions.
	 */
	public void setAllAnswers(int[] answers) {
		for (int i = 0; i < answers.length; i++) {
			this.questions.get(i).setAnswer(answers[i]);
		}
	}

	/**
	 * Returns the survey with the question and the corresponding answer as a
	 * map.
	 * 
	 * @return The survey with the questions and the answers.
	 */
	public Map<String, String> getSurvey() {
		Map<String, String> survey = new HashMap<String, String>();

		survey.put("Playlist ID", this.id);

		for (Question question : this.questions) {
			survey.put(question.getQuestion(), question.getAnswer());
		}
		return survey;
	}

}
