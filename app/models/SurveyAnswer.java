package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@ManyToOne
	private Survey survey;
	@ManyToMany
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
			QuestionOption option = new QuestionOption(0, "Sem resposta");
			Answer answer = Answer.find.where().eq("question", question)
					.eq("answerOption", option).findUnique();
			if (answer == null) {
				answer = new Answer(question, option);
			}
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
	 * Sets the list of answers for the survey.
	 * 
	 * @param answers
	 *            The new list of answers for the survey.
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * Sets an answer for one specific questions of the survey.
	 * 
	 * @param question
	 *            The number of the question in the list starting with 1.
	 * @param answer
	 *            The number of the question option to be the answer starting
	 *            with 1.
	 * @throws NumeroInvalidoException
	 *             If the given numbers are not valid for questions or/and
	 *             question options
	 */
	public void setAnswerToQuestion(int question, int answer)
			throws NumeroInvalidoException {
		Question quest = survey.getQuestion(question - 1);
		QuestionOption option = quest.getOption(answer - 1);
		Answer ans = Answer.find.where().eq("question", quest)
				.eq("answerOption", option).findUnique();
		if (ans == null) {
			ans = new Answer(quest, quest.getOption(answer - 1));
		}
		answers.set(question - 1, ans);

	}

	/**
	 * Sets all the answers for all the questions of the survey if the amount of
	 * answers given is smaller or is the same of the questions.
	 * 
	 * @param answers
	 *            The array with all the numbers of the question options for the
	 *            questions.
	 * @throws NumeroInvalidoException
	 *             If the given numbers for the question options are not valid
	 *             for a question.
	 */
	public void setAllAnswers(int[] answers) throws NumeroInvalidoException {
		if (answers.length <= getAnswers().size()) {
			for (int i = 0; i < answers.length; i++) {
				setAnswerToQuestion(i + 1, answers[i] + 1);
			}
		}
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
			survey.put(answer.getQuestion().getQuestion(), answer.getAnswer().getOption());
		}
		return survey;
	}

}
