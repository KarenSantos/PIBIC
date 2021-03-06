package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@ManyToOne(cascade = CascadeType.ALL)
	private Question question;

	@ManyToOne(cascade = CascadeType.ALL)
	private QuestionOption answerOption;
	private String comment;

	public static Finder<String, Answer> find = new Finder<String, Answer>(
			String.class, Answer.class);

	/**
	 * Creates an empty answer.
	 */
	public Answer() {
	}

	public Answer(Question question) {
		this.question = question;
		this.answerOption = null;
	}

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
	 * Returns the chosen question option for the answer.
	 * 
	 * @return The string question option for the answer or "Sem resposta" if
	 *         question option is null.
	 */
	public String getAnswerOption() {
		String option;
		if (getAnswer() == null) {
			option = "Sem resposta";
		} else {
			option = answerOption.getOption();
		}
		return option;
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
	 * @return The comment on the answer or "Sem comentario" if comment is
	 *         empty.
	 */
	public String getComment() {
		String com;
		if (comment == null || comment.equals("")) {
			com = "Sem comentario";
		} else {
			com = comment;
		}
		return com;
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

	/**
	 * Returns if the answer was answered or not.
	 * 
	 * @return True if the answer has a question option or a comment, and false
	 *         otherwise.
	 */
	public boolean isAnswered() {
		boolean resp = false;
		if (answerOption != null) {
			resp = true;
		} else if (comment != null) {
			resp = true;
		}
		return resp;
	}

	@Override
	public String toString() {
		String s;
		if (getQuestion().hasOptions()) {
			s = getAnswerOption();
		} else {
			s = getComment();
		}
		return "Answer: " + s;
	}

}
