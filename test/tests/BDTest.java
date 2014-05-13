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
	
}
