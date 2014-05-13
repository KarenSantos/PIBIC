package controllers;

import java.util.ArrayList;
import java.util.List;

import models.*;

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
	public Playlist getPlaylist(String id) {
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
	 * Retorna a nova playlist que esta sendo criada.
	 * 
	 * @return A nova playlist.
	 */
	public Playlist getNovaPlaylist() {
		return this.novaPlaylist;
	}

	/**
	 * Adiciona uma musica com um nome e um id na primeira paisagem da playlist
	 * sendo criada.
	 * 
	 * @param nome
	 *            O nome da musica a ser adicionada.
	 * @param id
	 *            O id da musica a ser adicionada.
	 * @throws AlocacaoInvalidaException
	 *             Se a musica a ser adicionada ja estiver na playlist.
	 */
	public void addMusicaPrimPaisagem(String nome, String id)
			throws AlocacaoInvalidaException {

		if (isAlreadyAdded(id)) {
			throw new AlocacaoInvalidaException(
					"Esta musica ja foi adicionada.");
		}

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
	 * @throws AlocacaoInvalidaException
	 *             Se a musica a ser adicionada ja estiver na playlist.
	 */
	public void addMusicaSegPaisagem(String nome, String id)
			throws AlocacaoInvalidaException {

		if (isAlreadyAdded(id)) {
			throw new AlocacaoInvalidaException(
					"Esta musica ja foi adicionada.");
		}

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
	 * @throws AlocacaoInvalidaException
	 *             Se a musica a ser adicionada ja estiver na playlist.
	 */
	public void setMusicaTransicao(String nome, String id)
			throws AlocacaoInvalidaException {

		if (isAlreadyAdded(id)) {
			throw new AlocacaoInvalidaException(
					"Esta musica ja foi adicionada.");
		}

		Musica musica = Musica.find.byId(id);
		if (musica == null) {
			musica = new Musica(nome, id);
		}
		this.transicao = musica;
	}

	/**
	 * Retorna a lista de todas as musicas da nova playlist.
	 * 
	 * @return A lista de todas as musicas da nova playlist.
	 */
	public List<Musica> getMusicasDaNovaPlaylist() {
		List<Musica> musicas = new ArrayList<Musica>();
		musicas.addAll(primPaisagem);
		if (isTransicaoSet()) {
			musicas.add(transicao);
		}
		musicas.addAll(segPaisagem);
		return musicas;
	}

	/**
	 * Configura a nova playlist que esta sendo criada com informacoes basicas
	 * da playlist recebida e as musicas ja adicionadas.
	 * 
	 * @param playlist
	 *            A playlist do form da pagina.
	 */
	public void configuraNovaPlaylist(Playlist playlist) {
		if (playlist.getId() != null) {
			this.novaPlaylist.setId(playlist.getId());
		}
		if (playlist.getNome() != null) {
			this.novaPlaylist.setNome(playlist.getNome());
		}
		if (playlist.getImagem() != null) {
			this.novaPlaylist.setImagem(playlist.getImagem());
		}
		if (playlist.getPrimGenero() != null) {
			this.novaPlaylist.setPrimGenero(playlist.getPrimGenero());
		}
		if (playlist.getSegGenero() != null) {
			this.novaPlaylist.setSegGenero(playlist.getSegGenero());
		}
		this.novaPlaylist.setPrimPaisagem(this.primPaisagem);
		this.novaPlaylist.setSegPaisagem(this.segPaisagem);
		if (isTransicaoSet()) {
			this.novaPlaylist.setTransicao(this.transicao);
		}
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
	 * 
	 * @throws PlaylistIncompletaException
	 *             Se a playlist tiver dados nulos ou em branco, ou se o numero
	 *             minimo de musicas nao foi alcancado.
	 */
	public void salvarPlaylist() throws PlaylistIncompletaException {
		if (musicIsMissing() || dataIsMissing()) {
			throw new PlaylistIncompletaException(
					"Esta playlist nao pode ser salva pois esta incompleta.");
		}

		catalogo.salvarPlaylist(novaPlaylist);
		limpaNovaPlaylist();
	}

	/**
	 * Indica se a musica do id recebido ja esta adicionada na nova playlist.
	 * 
	 * @param id
	 *            O id da musica.
	 * @return True se a musica com este id ja esta na nova playlist e false
	 *         caso contrario.
	 */
	private boolean isAlreadyAdded(String id) {
		boolean resp = false;
		for (Musica mus : getMusicasDaNovaPlaylist()) {
			if (mus.getId().equals(id)) {
				resp = true;
				break;
			}
		}
		return resp;
	}

	/**
	 * Indica se a nova playlist que vai ser salva tem musicas a menos do que
	 * deveria em cada parte.
	 * 
	 * @return True se a nova playlist tem musicas a menos.
	 */
	private boolean musicIsMissing() {
		boolean resp = false;
		if (this.primPaisagem.size() < 3){
			resp = true;
		} else if (this.segPaisagem.size() < 3){
			resp = true;
		} else if (!isTransicaoSet()){
			resp = true;
		}
		return resp;
	}

	/**
	 * Indica se a nova playlist que vai ser salva tem algum dado nulo ou em
	 * branco.
	 * 
	 * @return True se a nova playlist tem algum dado nulo ou em branco e false
	 *         caso contrario.
	 */
	private boolean dataIsMissing() {
		boolean resp = false;
		if (this.novaPlaylist.getId() == null
				|| this.novaPlaylist.getId().equals("")) {
			resp = true;
		} else if (this.novaPlaylist.getNome() == null
				|| this.novaPlaylist.getNome().equals("")) {
			resp = true;
		} else if (this.novaPlaylist.getImagem() == null
				|| this.novaPlaylist.getImagem().equals("")) {
			resp = true;
		} else if (this.novaPlaylist.getPrimGenero() == null
				|| this.novaPlaylist.getPrimGenero().equals("")) {
			resp = true;
		} else if (this.novaPlaylist.getSegGenero() == null
				|| this.novaPlaylist.getSegGenero().equals("")) {
			resp = true;
		}

		return resp;
	}
}
