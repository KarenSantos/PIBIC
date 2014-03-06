package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import models.Musica;

import org.junit.Before;
import org.junit.Test;

import controllers.Projeto;

public class ProjetoTest {

	private Projeto projeto;
	
	@Before
	public void setUp() throws Exception {
		projeto = new Projeto();
	}

	@Test
	public void deveTerUmaListaDePlaylists() {
		assertNotNull(projeto.getPlaylists());
	}
	
	@Test
	public void deveTerDetalhesSobreAsPlaylists() {
		
		Musica transicao = new Musica("mySong", "artista", "youtube.com/watch=435D15AMJ");
		List<Musica> primPaisagem = new ArrayList<Musica>();
		List<Musica> segPaisagem = new ArrayList<Musica>();
		primPaisagem.add(transicao);
		segPaisagem.add(transicao);
		
		// playlist sample já criada, esta é a playlist 2
		projeto.criarPlaylist(primPaisagem, segPaisagem, transicao, "My Playlist", "img.jpg");
		
		assertEquals("Playlist de André", projeto.getPlaylist(0).getNome());  
		assertEquals(6, projeto.getPlaylist(0).getTotalDeMusicas());
		assertEquals("0.jpg", projeto.getPlaylist(0).getImagem());
	}
	
	@Test
	public void deveCriarAmostraDePlaylists() {
		
		assertEquals(5, projeto.getTotalDePlaylists());
		assertEquals(5, projeto.getSamplePlaylists().size());
	}

}
