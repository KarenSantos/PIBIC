package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		
		// informacoes da playlist sample criada
		
		assertEquals("Playlist de André", projeto.getPlaylist(0).getNome());  
		assertEquals(6, projeto.getPlaylist(0).getTotalDeMusicas());
		assertEquals("0.jpg", projeto.getPlaylist(0).getImagem());
		assertEquals("Rock Instrumental", projeto.getPlaylist(0).getPrimGenero());
		assertEquals("Metal Melódico", projeto.getPlaylist(0).getSegGenero());
	}
	
	@Test
	public void deveCriarAmostraDePlaylists() {
		
		assertEquals(5, projeto.getTotalDePlaylists());
		assertEquals(5, projeto.getSamplePlaylists().size());
	}

}
