package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Question class.
 * 
 * @author Karen
 * 
 */
public class Question {

	private String question;
	private List<String> options;
	private int answer;

	/**
	 * Creates an object question with no question, an empty list of options and
	 * the answer set to -1.
	 */
	public Question() {
		this.question = "";
		this.options = new ArrayList<String>();
		this.answer = -1;
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
	public Question(String question, List<String> options) {
		this.question = question;
		this.options = options;
		this.answer = -1;
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
	public List<String> getOptions() {
		return this.options;
	}

	/**
	 * Sets the list of options to answer the question.
	 * 
	 * @param options
	 *            The list of options.
	 */
	public void setOptions(List<String> options) {
		this.options = options;
	}

	/**
	 * Adds an option to the list of options that answer the question.
	 * 
	 * @param opt
	 *            The new option to be added.
	 */
	public void addOptions(String opt) {
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
			resp = "Question not answered";
		} else {
			resp = options.get(answer);
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

}
