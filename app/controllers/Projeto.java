package controllers;

import java.util.ArrayList;
import java.util.List;

import models.CatalogoDePlaylists;
import models.Musica;
import models.Playlist;

/**
 * Classe controlador do PROJETO.
 * 
 * @author karen
 * 
 */
public class Projeto {

	private final int SAMPLE = 12;
	private CatalogoDePlaylists catalogo;
	private Playlist novaPlaylist;
	private List<Musica> primPaisagem;
	private List<Musica> segPaisagem;
	private Musica transicao;

	/**
	 * Cria um PROJETO com um catalogo de playlists.
	 */
	public Projeto() {
		catalogo = new CatalogoDePlaylists();

		novaPlaylist = new Playlist();
		primPaisagem = new ArrayList<Musica>();
		segPaisagem = new ArrayList<Musica>();
		transicao = null;
	}

	/**
	 * Retorna uma lista com todas as playlists criadas.
	 * 
	 * @return A lita com todas as playlists criadas.
	 */
	public List<Playlist> getPlaylists() {
		return catalogo.getPlaylists();
	}

	/**
	 * Retorna uma playlist especifica da lista de playlists.
	 * 
	 * @param id
	 *            O id da playlist.
	 * @return A playlist.
	 */
	public Playlist getPlaylist(int id) {
		return catalogo.getPlaylist(id);
	}

	/**
	 * Retorna o total de playlists criadas.
	 * 
	 * @return O total de playlists criadas.
	 */
	public int getTotalDePlaylists() {
		return catalogo.getTotalDePlaylists();
	}

	/**
	 * Retorna a nova playlist que esta sendo criada.
	 * 
	 * @return A nova playlist que esta sendo criada.
	 */
	public Playlist getNovaPlaylist() {
		return this.novaPlaylist;
	}

	/**
	 * Configura a nova playlist que esta sendo criada com informacoes basicas
	 * da playlist do form e as musicas ja adicionadas.
	 * 
	 * @param playlist
	 *            A playlist do form da pagina.
	 */
	public void configuraNovaPlaylist(Playlist playlist) {
		this.novaPlaylist.setNome(playlist.getNome());
		this.novaPlaylist.setImagem(playlist.getImagem());
		this.novaPlaylist.setPrimGenero(playlist.getPrimGenero());
		this.novaPlaylist.setSegGenero(playlist.getSegGenero());
		this.novaPlaylist.setPrimPaisagem(this.primPaisagem);
		this.novaPlaylist.setSegPaisagem(this.segPaisagem);
		this.novaPlaylist.setTransicao(this.transicao);
	}

	/**
	 * Retorna uma lista com playlists aleatórias selecionadas do total de
	 * playlists.
	 * 
	 * @param num
	 *            O número de playlists da amostra.
	 * @return A lista com playlists selecionadas aleatoriamente.
	 */
	public List<Playlist> getSamplePlaylists() {
		return catalogo.getSamplePlaylists(SAMPLE);
	}

	/**
	 * Retorna a lista com as musicas da primeira paisagem da playlist sendo
	 * criada.
	 * 
	 * @return A lista com as musicas da primeira paisagem.
	 */
	public List<Musica> getPrimPaisagem() {
		return this.primPaisagem;
	}

	/**
	 * Adiciona uma musica com um nome e um id na primeira paisagem da playlist
	 * sendo criada.
	 * 
	 * @param nome
	 *            O nome da musica a ser adicionada.
	 * @param id
	 *            O id da musica a ser adicionada.
	 */
	public void addMusicaPrimPaisagem(String nome, String id) {
		Musica musica = Musica.find.byId(id);
		if (musica == null) {
			musica = new Musica(nome, id);
		}
		this.primPaisagem.add(musica);
	}

	/**
	 * Retorna a lista com as musicas da segunda paisagem da playlist sendo
	 * criada.
	 * 
	 * @return A lista com as musicas da segunda paisagem.
	 */
	public List<Musica> getSegPaisagem() {
		return this.segPaisagem;
	}

	/**
	 * Adiciona uma musica com um nome e um id na segunda paisagem da playlist
	 * sendo criada.
	 * 
	 * @param nome
	 *            O nome da musica a ser adicionada.
	 * @param id
	 *            O id da musica a ser adicionada.
	 */
	public void addMusicaSegPaisagem(String nome, String id) {
		Musica musica = Musica.find.byId(id);
		if (musica == null) {
			musica = new Musica(nome, id);
		}
		this.segPaisagem.add(musica);
	}

	/**
	 * Retorna a musica de transicao da playlist sendo criada.
	 * 
	 * @return A musica de transicao.
	 */
	public Musica getTransicao() {
		return this.transicao;
	}

	/**
	 * Define uma musica de transicao para a playlist sendo criada.
	 * 
	 * @param nome
	 *            O nome da musica de transicao.
	 * @param id
	 *            O id da musica de transicao.
	 */
	public void setMusicaTransicao(String nome, String id) {
		Musica musica = Musica.find.byId(id);
		if (musica == null) {
			musica = new Musica(nome, id);
		}
		this.transicao = musica;
	}

	/**
	 * Limpa a playlist que esta sendo criada.
	 */
	public void limpaNovaPlaylist() {
		this.novaPlaylist = null;
		this.primPaisagem = new ArrayList<Musica>();
		this.segPaisagem = new ArrayList<Musica>();
		this.transicao = null;
	}

	/**
	 * Retorna o total de musicas da primeira paisagem.
	 * 
	 * @return O total de musicas da primeira paisagem.
	 */
	public int getTotalMusicasPrimPaisagem() {
		return this.primPaisagem.size();
	}

	/**
	 * Retorna o total de musicas da seg paisagem.
	 * 
	 * @return O total de musicas da seg paisagem.
	 */
	public int getTotalMusicasSegPaisagem() {
		return this.segPaisagem.size();
	}

	/**
	 * Retorna se a musica de transicao ja foi setada.
	 * 
	 * @return True se a musica de transicao ja foi setada ou false caso
	 *         contrario.
	 */
	public boolean isTransicaoSet() {
		return this.transicao != null;
	}

	/**
	 * Adiciona a nova playlist no catalogo de playlists.
	 */
	public void salvarPlaylist() {
		catalogo.salvarPlaylist(novaPlaylist);
		limpaNovaPlaylist();
	}

}
