package controllers;

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

	/**
	 * Cria um PROJETO com um catalogo de playlists.
	 */
	public Projeto() {
		catalogo = new CatalogoDePlaylists();
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
	 * Cria uma playlist com uma lista de músicas da primeira paisagem, uma
	 * lista de músicas da segunda paisagem, uma música de transição um nome e
	 * uma imagem.
	 * 
	 * @param primPaisagem
	 *            A lista de músicas da primeira paisagem.
	 * @param segPaisagem
	 *            A lista de músicas da segunda paisagem.
	 * @param transicao
	 *            A música de transição.
	 * @param primGenero
	 *            O gênero da primeira paisagem musical.
	 * @param segGenero
	 *            O gênero da segunda paisagem musical.
	 * @param nome
	 *            O nome da playlist.
	 * @param imagem
	 *            A imagem da playlist.
	 */
	public void criarPlaylist(List<Musica> primPaisagem,
			List<Musica> segPaisagem, Musica transicao, String primGenero,
			String segGenero, String nome, String imagem) {
		catalogo.criarPlaylist(primPaisagem, segPaisagem, transicao,
				primGenero, segGenero, nome, imagem);
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

}
