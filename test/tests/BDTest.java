package tests;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import models.*;

import org.junit.Before;
import org.junit.Test;

import controllers.Projeto;


public class BDTest {

	
	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
	public void deveSalvarMusicasNoBD(){
		assertTrue(Musica.find.all().isEmpty());

		Musica musica = new Musica("nome", "id");
		musica.save();
		assertFalse(Musica.find.all().isEmpty());
		assertNotNull(Musica.find.byId("id"));
	}
	
	@Test
	public void deveSalvarPlaylistNoBD() {
		Playlist playlist = new Playlist();
		playlist.setId("nova");
		playlist.setNome("Nome");
		playlist.setImagem("imagem.jpg");
		playlist.save();
		
		assertNotNull(Playlist.find.byId("nova"));
		
	}
	
	@Test
	public void deveRecuperarSamplePlaylistsDoBD() {
		//Projeto ja inicia com sample playlists criadas
		Projeto projeto = new Projeto();
		assertNotNull(Playlist.find.all().get(0));
		assertNotNull(projeto.getPlaylists().get(0));
	}
	
	@Test
	public void deveSalvarQuestionOptionNoBD() {
		QuestionOption op1 = new QuestionOption(1, "opcao1");
		
		op1.save();
		QuestionOption option = QuestionOption.find.where().eq("option", op1.getOption()).findUnique();
		assertNotNull(option);
	}
	
	@Test
	public void deveSalvarQuestionsNoBD() {
		
		QuestionOption op1 = new QuestionOption(1, "opcao1");
		QuestionOption op2 = new QuestionOption(2, "opcao2");
		
		Question question = new Question();
//		question.setId("primeira");
		question.setQuestion("qual?");
		question.addOption(op1);
		question.addOption(op2);
		
		question.save();
		
		Question ques = Question.find.where().eq("question", question.getQuestion()).findUnique();
		assertNotNull(ques);
		assertEquals("qual?", ques.getQuestion());
		assertEquals("opcao1", ques.getOptions().get(0).getOption());
	}
	
	@Test
	public void deveSalvarSurveyNoBD() throws ParametroInvalidoException {
		QuestionOption op1 = new QuestionOption(1, "opcao1");
		QuestionOption op2 = new QuestionOption(2, "opcao2");
		
		Question question1 = new Question();
//		question1.setId("q1");
		question1.setQuestion("qual?");
		question1.addOption(op1);
		question1.addOption(op2);
		
		Question question2 = new Question();
//		question2.setId("q2");
		question2.setQuestion("quem?");
		question2.addOption(op1);
		question2.addOption(op2);
		
		Survey survey = new Survey("id");
		survey.addQuestion(question1);
		survey.addQuestion(question2);
		survey.save();
		
		assertNotNull(Survey.find.byId("id"));
		assertEquals("qual?", Survey.find.byId("id").getQuestion(1).getQuestion());
	}
	
	@Test
	public void deveRecuperarSurveyPadraoDoBD() {
		//Projeto inicia com survey padrao
		assertNotNull(Survey.find.all().get(0));
	}
	
	@Test
	public void devePoderSalvarAnswerNoBD() throws ParametroInvalidoException {
		
		assertEquals(0, Answer.find.all().size());
		
		Question question = Question.find.all().get(0);
		QuestionOption option = question.getOption(1);
		Answer answer = new Answer(question, option);
		answer.save();
		
		assertEquals(1, Answer.find.all().size());
	}
	
	@Test
	public void devePoderSalvarSurveyAnswerNoBD() throws ParametroInvalidoException {
		
		assertEquals(0, SurveyAnswer.find.all().size());
		
		SurveyAnswer sa = new SurveyAnswer(Survey.find.byId("padrao"));
		sa.save();

		assertNotNull(SurveyAnswer.find.all().get(0));
		assertEquals(1, SurveyAnswer.find.all().size());

		assertNotNull(Answer.find.all().get(0));
		assertEquals(10, Answer.find.all().size());
		
		assertEquals("Sem resposta", SurveyAnswer.find.all().get(0).getAnswerToQuestion(1).getAnswerOption());
		
		Answer ans1 = sa.getAnswerToQuestion(1);
		QuestionOption opt1 = ans1.getQuestion().getOption(2);
		sa.setOptionToAnswer(ans1, opt1);
		sa.save();
		
		assertEquals(10, Answer.find.all().size());
		
		assertEquals("Pelo menos uma vez por semana", SurveyAnswer.find.all().get(0).getAnswerToQuestion(1).getAnswerOption());
		assertEquals(1, SurveyAnswer.find.all().size());
		
		Answer ans2 = sa.getAnswerToQuestion(2);
		QuestionOption opt2 = ans2.getQuestion().getOption(4);
		sa.setOptionToAnswer(ans2, opt2);
		sa.save();
		
		assertEquals(10, Answer.find.all().size());
		
		assertEquals("Gosto de qualquer tipo de música", SurveyAnswer.find.all().get(0).getAnswerToQuestion(2).getAnswerOption());
		assertEquals(1, SurveyAnswer.find.all().size());
		
		ans1 = sa.getAnswerToQuestion(1);
		opt1 = ans1.getQuestion().getOption(3);
		sa.setOptionToAnswer(ans1, opt1);
		sa.save();
		assertEquals("Mais de uma vez por semana", SurveyAnswer.find.all().get(0).getAnswerToQuestion(1).getAnswerOption());

	}
}
