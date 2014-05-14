package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

/**
 * Question class.
 * 
 * @author Karen
 * 
 */
@Entity
public class Question extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private String id;
	private String question;

	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "option_id"))
	private List<QuestionOption> options;
	private int answer;
	private String comment;
	
	public static Finder<String, Question> find = new Finder<String, Question>(
			String.class, Question.class);

	/**
	 * Creates an object question with no question, an empty list of options and
	 * the answer set to -1.
	 */
	public Question() {
		this.question = "";
		this.options = new ArrayList<QuestionOption>();
		this.answer = -1;
		this.comment = "";
	}

	/**
	 * Creates an object question with a question, a list of options and the
	 * answer set to -1.
	 * 
	 * @param question
	 *            The question.
	 * @param options
	 *            The list of options to answer the question.
	 */
	public Question(String id, String question, List<QuestionOption> options) {
		this.id = id;
		this.question = question;
		this.options = options;
		this.answer = -1;
	}

	/**
	 * Returns the id of the question.
	 * 
	 * @return The Id of the question.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the id of the question.
	 * 
	 * @param id
	 *            The new id of the question.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the question.
	 * 
	 * @return The question.
	 */
	public String getQuestion() {
		return this.question;
	}

	/**
	 * Sets the question.
	 * 
	 * @param question
	 *            The question.
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Returns the list of options that answer the question.
	 * 
	 * @return The list of options that answer the question.
	 */
	public List<QuestionOption> getOptions() {
		return this.options;
	}

	/**
	 * Sets the list of options to answer the question.
	 * 
	 * @param options
	 *            The list of options.
	 */
	public void setOptions(List<QuestionOption> options) {
		this.options = options;
	}

	/**
	 * Adds an option to the list of options that answer the question.
	 * 
	 * @param opt
	 *            The new option to be added.
	 */
	public void addOption(QuestionOption opt) {
		this.options.add(opt);
	}

	/**
	 * Returns the answer for the question.
	 * 
	 * @return Returns the answer for the question from the options list or
	 *         Question not answered if the answer is not set.
	 */
	public String getAnswer() {
		String resp;
		if (answer == -1) {
			resp = "Pergunta não respondida.";
		} else {
			resp = options.get(answer).getOption();
		}
		return resp;
	}

	/**
	 * Sets an answer for the question as the number of the chosen option.
	 * 
	 * @param answer
	 *            The number for the chosen option starting with 1.
	 */
	public void setAnswer(int answer) {
		this.answer = answer - 1;
	}

	/**
	 * Return the comment on the question.
	 * 
	 * @return The comment on the question.
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Adds a comment for the question.
	 * 
	 * @param comment
	 *            The comment for the question.
	 */
	public void addComment(String comment) {
		this.comment = comment;
	}

}
