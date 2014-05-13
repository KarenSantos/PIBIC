package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import models.*;

import org.junit.Before;
import org.junit.Test;

import controllers.Projeto;

public class ProjetoTest {

	private Projeto projeto;
	
	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));
		projeto = new Projeto();
	}

	@Test
	public void deveTerUmaListaDePlaylists() {
		assertNotNull(projeto.getPlaylists());
	}
	
	@Test
	public void deveTerDetalhesSobreAsPlaylists() {
		
		// informacoes de sample playlists criadas
		assertEquals("Playlist de André", projeto.getPlaylist("1").getNome());  
		assertEquals(6, projeto.getPlaylist("1").getTotalDeMusicas());
		assertEquals("1.jpg", projeto.getPlaylist("1").getImagem());
		assertEquals("Rock Instrumental", projeto.getPlaylist("1").getPrimGenero());
		assertEquals("Metal Melódico", projeto.getPlaylist("1").getSegGenero());
	}
	
	@Test
	public void deveCriarAmostraDePlaylists() {
		// amostra de playlists com numero limitado para pagina principal
		assertEquals(5, projeto.getTotalDePlaylists());
		assertEquals(5, projeto.getSamplePlaylists().size());
	}
	
	@Test
	public void devePoderAdicionarMusicas() throws AlocacaoInvalidaException {
		
		projeto.addMusicaPrimPaisagem("mus1", "yW39pSIu4kk");
		projeto.addMusicaSegPaisagem("mus2", "huFra1mnIVE");
		projeto.setMusicaTransicao("transicao", "T0ip40j82ws");
		
		assertEquals(1, projeto.getPrimPaisagem().size());
		assertEquals(1, projeto.getSegPaisagem().size());
		assertNotNull(projeto.getTransicao());
		assertEquals("transicao", projeto.getTransicao().getNome());
	
		projeto.setMusicaTransicao("transicaoNova", "B07iK9uh9qY");
		assertEquals("transicaoNova", projeto.getTransicao().getNome());
		
		projeto.addMusicaPrimPaisagem("mus3", "gxYLlBsGGGM");
		projeto.addMusicaSegPaisagem("mus4", "eZEE0hRR378");
		
		assertEquals(2, projeto.getPrimPaisagem().size());
		assertEquals(2, projeto.getSegPaisagem().size());
	}
	
	@Test
	public void naoDeveAdicionarMusicaRepetidaNaMesmaPlaylist() throws AlocacaoInvalidaException {
		projeto.addMusicaPrimPaisagem("mus1", "yW39pSIu4kk");
		projeto.addMusicaPrimPaisagem("mus2", "huFra1mnIVE");
		projeto.addMusicaPrimPaisagem("mus3", "gxYLlBsGGGM");
		
		try {
			projeto.addMusicaPrimPaisagem("mus1", "yW39pSIu4kk");
			fail("Deveria ter lancado excecao");
		} catch (AlocacaoInvalidaException ae){
			assertEquals("Esta musica ja foi adicionada.", ae.getMessage());
		}
		
		try {
			projeto.addMusicaSegPaisagem("mus1", "yW39pSIu4kk");
			fail("Deveria ter lancado excecao");
		} catch (AlocacaoInvalidaException ae){
			assertEquals("Esta musica ja foi adicionada.", ae.getMessage());
		}
		
		try {
			projeto.setMusicaTransicao("mus1", "yW39pSIu4kk");
			fail("Deveria ter lancado excecao");
		} catch (AlocacaoInvalidaException ae){
			assertEquals("Esta musica ja foi adicionada.", ae.getMessage());
		}
	}
	
	@Test
	public void deveConfigurarNovaPlaylistComDadosDeUmaPlaylistRecebida(){
		// vai ser util pra configurar a nova playlist com a playlist recebida no form
		Playlist playlist = new Playlist();
		playlist.setId("myId");
		playlist.setNome("My Playlist");
		playlist.setImagem("0.jpg");
		
		projeto.configuraNovaPlaylist(playlist);
		
		assertEquals("myId", projeto.getNovaPlaylist().getId());
		assertEquals("My Playlist", projeto.getNovaPlaylist().getNome());
		assertEquals("0.jpg", projeto.getNovaPlaylist().getImagem());
	}
	
	@Test
	public void devePoderSalvarPlaylistNoBD() throws AlocacaoInvalidaException, PlaylistIncompletaException {
		projeto.addMusicaPrimPaisagem("mus1", "yW39pSIu4kk");
		projeto.addMusicaPrimPaisagem("mus1", "huFra1mnIVE");
		projeto.addMusicaPrimPaisagem("mus1", "T0ip40j82ws");
		projeto.addMusicaSegPaisagem("mus2", "B07iK9uh9qY");
		projeto.addMusicaSegPaisagem("mus2", "gxYLlBsGGGM");
		projeto.addMusicaSegPaisagem("mus2", "-am7iYzCfdE");
		projeto.setMusicaTransicao("transicao", "eZEE0hRR378");
		
		Playlist playlist = new Playlist();
		playlist.setId("myId");
		playlist.setNome("My Playlist");
		playlist.setImagem("0.jpg");
		playlist.setPrimGenero("genero1");
		playlist.setSegGenero("genero2");
		
		projeto.configuraNovaPlaylist(playlist);
		projeto.salvarPlaylist();
		
		assertNotNull(Playlist.find.byId("myId"));
	}

	@Test
	public void naoDeveSalvarPlaylistComDadosEmBranco() throws AlocacaoInvalidaException {
		projeto.addMusicaPrimPaisagem("mus1", "yW39pSIu4kk");
		projeto.addMusicaPrimPaisagem("mus1", "huFra1mnIVE");
		projeto.addMusicaPrimPaisagem("mus1", "T0ip40j82ws");
		projeto.addMusicaSegPaisagem("mus2", "B07iK9uh9qY");
		projeto.addMusicaSegPaisagem("mus2", "gxYLlBsGGGM");
		projeto.addMusicaSegPaisagem("mus2", "-am7iYzCfdE");
		projeto.setMusicaTransicao("transicao", "eZEE0hRR378");
		
		Playlist playlist = new Playlist();
		playlist.setId("");
		playlist.setNome("");
		playlist.setImagem("");
		playlist.setPrimGenero("");
		playlist.setSegGenero("");
		projeto.configuraNovaPlaylist(playlist);

		try {
			projeto.salvarPlaylist();
			fail("Deveria ter lancado excecao");
		} catch (PlaylistIncompletaException e) {
			assertEquals("Esta playlist nao pode ser salva pois esta incompleta.", e.getMessage());
		}
		
		playlist.setId("myId");
		playlist.setNome("My Playlist");
		
		try {
			projeto.salvarPlaylist();
			fail("Deveria ter lancado excecao");
		} catch (PlaylistIncompletaException e) {
			assertEquals("Esta playlist nao pode ser salva pois esta incompleta.", e.getMessage());
		}
		
		playlist.setPrimGenero("genero1");
		playlist.setSegGenero("genero2");
		
		try {
			projeto.salvarPlaylist();
			fail("Deveria ter lancado excecao");
		} catch (PlaylistIncompletaException e) {
			assertEquals("Esta playlist nao pode ser salva pois esta incompleta.", e.getMessage());
		}
		// ainda falta imagem
	}
	
	@Test
	public void naoDeveSalvarPlaylistComDadosNulos() throws AlocacaoInvalidaException {
		projeto.addMusicaPrimPaisagem("mus1", "yW39pSIu4kk");
		projeto.addMusicaPrimPaisagem("mus1", "huFra1mnIVE");
		projeto.addMusicaPrimPaisagem("mus1", "T0ip40j82ws");
		projeto.addMusicaSegPaisagem("mus2", "B07iK9uh9qY");
		projeto.addMusicaSegPaisagem("mus2", "gxYLlBsGGGM");
		projeto.addMusicaSegPaisagem("mus2", "-am7iYzCfdE");
		projeto.setMusicaTransicao("transicao", "eZEE0hRR378");
		
		Playlist playlist = new Playlist();
		projeto.configuraNovaPlaylist(playlist);

		try {
			projeto.salvarPlaylist();
			fail("Deveria ter lancado excecao");
		} catch (PlaylistIncompletaException e) {
			assertEquals("Esta playlist nao pode ser salva pois esta incompleta.", e.getMessage());
		}
		
		playlist.setId("myId");
		playlist.setNome("My Playlist");
		
		try {
			projeto.salvarPlaylist();
			fail("Deveria ter lancado excecao");
		} catch (PlaylistIncompletaException e) {
			assertEquals("Esta playlist nao pode ser salva pois esta incompleta.", e.getMessage());
		}
		
		playlist.setPrimGenero("genero1");
		playlist.setSegGenero("genero2");
		
		try {
			projeto.salvarPlaylist();
			fail("Deveria ter lancado excecao");
		} catch (PlaylistIncompletaException e) {
			assertEquals("Esta playlist nao pode ser salva pois esta incompleta.", e.getMessage());
		}
		// ainda falta imagem
	}
	
	@Test
	public void naoDeveSalvarPlaylistComNumeroDeMusicasInvalido() throws AlocacaoInvalidaException {
		projeto.addMusicaPrimPaisagem("mus1", "yW39pSIu4kk");
		projeto.addMusicaPrimPaisagem("mus1", "T0ip40j82ws");
		projeto.addMusicaSegPaisagem("mus2", "B07iK9uh9qY");
		
		Playlist playlist = new Playlist();
		playlist.setId("myId");
		playlist.setNome("My Playlist");
		playlist.setImagem("0.jpg");
		playlist.setPrimGenero("genero1");
		playlist.setSegGenero("genero2");
		
		projeto.configuraNovaPlaylist(playlist);
		try {
			projeto.salvarPlaylist();
			fail("Deveria ter lancado excecao");
		} catch (PlaylistIncompletaException e){
			assertEquals("Esta playlist nao pode ser salva pois esta incompleta.", e.getMessage());
		}
		
		projeto.addMusicaPrimPaisagem("mus1", "huFra1mnIVE");
		projeto.addMusicaPrimPaisagem("mus1", "eZEE0hRR378");
		projeto.addMusicaSegPaisagem("mus2", "gxYLlBsGGGM");
		projeto.addMusicaSegPaisagem("mus2", "-am7iYzCfdE");

		projeto.configuraNovaPlaylist(playlist);
		try {
			projeto.salvarPlaylist();
			fail("Deveria ter lancado excecao");
		} catch (PlaylistIncompletaException e){
			assertEquals("Esta playlist nao pode ser salva pois esta incompleta.", e.getMessage());
		}
		// falta adicionar uma transicao
	}
}
