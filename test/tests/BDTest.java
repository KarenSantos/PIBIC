package tests;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import models.*;

import org.junit.Before;
import org.junit.Test;


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
		assertNotNull(Playlist.find.all());
	}
	
	@Test
	public void deveSalvarQuestionsNoBD() {
		
		QuestionOption op1 = new QuestionOption(1, "opcao1");
		QuestionOption op2 = new QuestionOption(2, "opcao2");
		
		Question question = new Question();
		question.setQuestion("qual?");
		question.addOption(op1);
		question.addOption(op2);
		
		question.save();
		
		assertNotNull(Question.find.all().get(0));
		assertEquals("qual?", Question.find.all().get(0).getQuestion());
		assertEquals("opcao1", Question.find.all().get(0).getOptions().get(0).getOption());
	}
	
	@Test
	public void deveSalvarSurveyNoBD() {
		QuestionOption op1 = new QuestionOption(1, "opcao1");
		QuestionOption op2 = new QuestionOption(2, "opcao2");
		
		Question question1 = new Question();
		question1.setQuestion("qual?");
		question1.addOption(op1);
		question1.addOption(op2);
		
		Question question2 = new Question();
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
	
	
	
}
