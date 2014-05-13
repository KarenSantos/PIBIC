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
		Question question = new Question();
		question.setId("1");
		question.setQuestion("qual?");
		question.addOption("opcao1");
		
		question.save();
		
		assertNotNull(Question.find.byId("1"));
		assertEquals("qual?", Question.find.byId("1").getQuestion());
		assertEquals("opcao1", Question.find.byId("1").getOptions().get(0));
	}
	
}
