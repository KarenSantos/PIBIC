package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

/**
 * Answer class
 * 
 * @author karen
 * 
 */
@Entity
public class Answer extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@ManyToMany
	private Question question;

	@ManyToOne
	private QuestionOption answerOption;
	private String comment;

	public static Finder<String, Answer> find = new Finder<String, Answer>(
			String.class, Answer.class);

	/**
	 * Creates an empty answer.
	 */
	public Answer() {}
	
	/**
	 * Creates an answer with a question option as the answer.
	 * 
	 * @param answerOption
	 *            The question option to be the answer.
	 */
	public Answer(Question question, QuestionOption answerOption) {
		this.question = question;
		this.answerOption = answerOption;
		this.comment = "No comment.";
	}

	/**
	 * Returns the id of the answer.
	 * 
	 * @return The id of the answer.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the answer.
	 * 
	 * @param id
	 *            The new id of the answer.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the question for the answer.
	 * 
	 * @return The question for the answer.
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * Sets the question for the answer.
	 * 
	 * @param question
	 *            The new question for the answer.
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * Returns the question option that is the answer.
	 * 
	 * @return The question option that is the answer or null if the answer is
	 *         not set.
	 */
	public QuestionOption getAnswer() {
		return answerOption;
	}

	/**
	 * Sets the answer as a question option.
	 * 
	 * @param answer
	 *            The new answer.
	 */
	public void setAnswer(QuestionOption answerOption) {
		this.answerOption = answerOption;
	}

	/**
	 * Returns the comment on the answer.
	 * 
	 * @return The comment on the answer.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets a comment for the answer.
	 * 
	 * @param comment
	 *            The new comment for the answer.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
