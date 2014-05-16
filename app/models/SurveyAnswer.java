package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

/**
 * Survey answer class
 * 
 * @author karen
 * 
 */
@Entity
public class SurveyAnswer extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Survey survey;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "survey_answer_answers", joinColumns = @JoinColumn(name = "survey_answer_id"), inverseJoinColumns = @JoinColumn(name = "answer_id"))
	private List<Answer> answers;

	public static Finder<String, SurveyAnswer> find = new Finder<String, SurveyAnswer>(
			String.class, SurveyAnswer.class);

	/**
	 * Creates an empty survey answer.
	 */
	public SurveyAnswer() {
	}

	/**
	 * Creates a survey answer with a survey and a list of answers with the
	 * survey questions with a blank question options.
	 * 
	 * @param survey
	 */
	public SurveyAnswer(Survey survey) {
		this.survey = survey;
		this.answers = new ArrayList<Answer>();
		for (Question question : survey.getQuestions()) {
			Answer answer = new Answer(question);
			answers.add(answer);
		}
	}

	/**
	 * Returns the id of the answered survey.
	 * 
	 * @return The id of the answered survey.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id for the answered survey.
	 * 
	 * @param id
	 *            The new id for the answered survey.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the survey that was answered.
	 * 
	 * @return The survey that was answered.
	 */
	public Survey getSurvey() {
		return survey;
	}

	/**
	 * Sets the survey to be answered.
	 * 
	 * @param survey
	 *            The new survey to be answered.
	 */
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	/**
	 * Returns the list of answers for the survey.
	 * 
	 * @return The list of answers for the survey.
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * Returns the answer for the question with the given number.
	 * 
	 * @param num
	 *            The number of the question starting with 1.
	 * @return The answer for the question with the given number.
	 */
	public Answer getAnswerToQuestion(int num) {
		return answers.get(num - 1);
	}

	/**
	 * Sets the list of answers for the survey.
	 * 
	 * @param answers
	 *            The new list of answers for the survey.
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * Sets the option for one specific answer of the survey answer.
	 * 
	 * @param answer
	 *            The answer to be set the option.
	 * @param option
	 *            The number of the question option to be the answer starting
	 *            with 1.
	 * @throws ParametroInvalidoException
	 *             If the answer is not in the answers list or the option number
	 *             is not a valid option for the question.
	 */
	public void setOptionToAnswer(Answer answer, QuestionOption option)
			throws ParametroInvalidoException {
		
		if (!answers.contains(answer)){
			throw new ParametroInvalidoException("Esta answer não está na lista de answers da survey answer.");
		} else if (!answer.getQuestion().getOptions().contains(option)){
			throw new ParametroInvalidoException("Esta option não está na lista de options da question.");
		}
//		int answerIndex = answers.indexOf(answer);
//		Question quest = answer.getQuestion();
//		answers.get(answerIndex).setAnswer(opt);
		answer.setAnswer(option);
	}

	/**
	 * Sets a comment to the question with the given number.
	 * 
	 * @param num
	 *            The number of the question of the answer.
	 * @param comment
	 *            The new comment for the question of the answer.
	 * @throws ParametroInvalidoException
	 *             If the given number is not a valid question in the survey.
	 */
	public void setAnswerComment(int num, String comment)
			throws ParametroInvalidoException {
		if (num > answers.size()) {
			throw new ParametroInvalidoException(
					"Não existe pergunta com o número indicado.");
		}
		answers.get(num - 1).setComment(comment);
	}

	/**
	 * Returns the survey with the question and the corresponding answer as a
	 * map of strings.
	 * 
	 * @return The survey with the questions and the answers.
	 */
	public Map<String, String> getAnsweredSurvey() {
		Map<String, String> survey = new HashMap<String, String>();

		for (Answer answer : getAnswers()) {
			survey.put(answer.getQuestion().getQuestion(), answer.getAnswer()
					.getOption());
		}
		return survey;
	}

	/**
	 * Returns if the survey answer has at least one real answer.
	 * 
	 * @return True if the survey has at least one answer for one question,
	 *         false otherwise.
	 */
	public boolean isSurveyAnswered() {
		boolean resp = false;
		for (Answer answer : answers) {
			if (answer.isAnswered()) {
				resp = true;
				break;
			}
		}
		return resp;
	}

	/**
	 * Returns the total amount of questions in the survey answer.
	 * 
	 * @return The total amount of questions in the survey answer.
	 */
	public int getTotalQuestions() {
		return answers.size();
	}

}
