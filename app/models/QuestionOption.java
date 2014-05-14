package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Option class for questions
 * 
 * @author karen
 * 
 */
@Entity
@Table(name = "options")
public class QuestionOption extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String option;
	private int num;

	public static Finder<String, QuestionOption> find = new Finder<String, QuestionOption>(
			String.class, QuestionOption.class);
	
	/**
	 * Creates an empty option.
	 */
	public QuestionOption() {
	}

	/**
	 * Creates a question option with an id and an option.
	 * 
	 * @param num
	 *            The number of the question option.
	 * @param option
	 *            The option.
	 */
	public QuestionOption(int num, String option) {
		this.num = num;
		this.option = option;
	}

	/**
	 * Returns the id of the question option.
	 * 
	 * @return The id of the question option.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the question option.
	 * 
	 * @param id
	 *            The new id of the option.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the number of the question option.
	 * 
	 * @return The number of the question option.
	 */
	public int getNumber() {
		return num;
	}

	/**
	 * Sets the number of the question option.
	 * 
	 * @param num
	 *            The new number of the option.
	 */
	public void setNumber(int num) {
		this.num = num;
	}
	
	/**
	 * Returns the string with the option.
	 * 
	 * @return The string with the option.
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Sets the option for the question option.
	 * 
	 * @param option
	 *            The new option.
	 */
	public void setOption(String option) {
		this.option = option;
	}
}
