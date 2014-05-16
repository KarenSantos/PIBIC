package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	private int id;
	private String question;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "option_id"))
	private List<QuestionOption> options;

	public static Finder<String, Question> find = new Finder<String, Question>(
			String.class, Question.class);

	/**
	 * Creates an empty question with a generated id.
	 */
	public Question() {
		this.question = "";
		this.options = new ArrayList<QuestionOption>();
	}

	/**
	 * Creates an object question with an id, a question and a list of options.
	 * 
	 * @param id
	 *            The id of the question.
	 * @param question
	 *            The question.
	 * @param options
	 *            The list of options to answer the question.
	 */
	public Question(String question, List<QuestionOption> options) {
//		this.id = id;
		this.question = question;
		this.options = options;
	}

	/**
	 * Returns the id of the question.
	 * 
	 * @return The Id of the question.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the id of the question.
	 * 
	 * @param id
	 *            The new id of the question.
	 */
	public void setId(int id) {
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
	 * Returns the question option with the given number.
	 * 
	 * @param num
	 *            The number of the question option starting with 1.
	 * @return The question option with the given number.
	 * @throws ParametroInvalidoException
	 *             If the given number is not a valid question option.
	 */
	public QuestionOption getOption(int num) throws ParametroInvalidoException {
		if (num < 0 || num > options.size()) {
			throw new ParametroInvalidoException(
					"Não existe uma opção com o número indicado.");
		}
		return options.get(num - 1);
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
	 * Returns if the questions has a list of options or is an open question
	 * with no options.
	 * 
	 * @return True if the question has options and false otherwise.
	 */
	public boolean hasOptions() {
		return getOptions().size() > 0;
	}

	@Override
	public String toString() {
		return "Question: " + getQuestion();
	}
}
