package tests;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import models.Playlist;

import org.junit.Before;
import org.junit.Test;

import controllers.Projeto;

public class BDTest {

	
	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void deveRecuperarSamplePlaylistsDoBD() {
		assertNotNull(Playlist.find.all());
	}
	
}
