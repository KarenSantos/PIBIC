package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe de playlists.
 * 
 * @author karen
 * 
 */
public class Playlist {

	private int id;
	private String nome, imagem, primGenero, segGenero;
	private List<Musica> primPaisagem, segPaisagem;
	private Musica transicao;

	/**
	 * Cria uma playlist vazia com listas de musicas vazias.
	 */
	public Playlist() {
	}

	/**
	 * Cria uma playlist com uma lista de músicas da primeira paisagem,
	 * uma lista de músicas da segunda paisagem, uma música de transição, um
	 * nome e uma imagem.
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
	 *            O endereço da imagem da playlist.
	 */
	public Playlist(List<Musica> primPaisagem,
			List<Musica> segPaisagem, Musica transicao, String primGenero,
			String segGenero, String nome, String imagem) {
		this.primPaisagem = primPaisagem;
		this.segPaisagem = segPaisagem;
		this.transicao = transicao;
		this.primGenero = primGenero;
		this.segGenero = segGenero;
		this.nome = nome;
		this.imagem = imagem;
	}

	/**
	 * Retorna o id da playlist.
	 * 
	 * @return O id da playlist;
	 */
	public int getId() {
		return id;
	}

	/**
	 * Altera o id da playlist.
	 * 
	 * @param id
	 *            O novo id da playlist.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna o nome da playlist.
	 * 
	 * @return O nome da playlist.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome da playlist.
	 * 
	 * @param nome
	 *            O novo nome da playlist.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retonra o endereço para a imagem da playlist.
	 * 
	 * @return O endereço para a imagem da playlist.
	 */
	public String getImagem() {
		return imagem;
	}

	/**
	 * Altera a imagem da playlist.
	 * 
	 * @param imagem
	 *            A nova imagem da playlist.
	 */
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	/**
	 * Retorna o genero da primeira paisagem musical.
	 * 
	 * @return O genero da primeira paisagem musical.
	 */
	public String getPrimGenero() {
		return this.primGenero;
	}

	/**
	 * Altera o genero da primeira paisagem musical da playlist.
	 * 
	 * @param primGenero
	 *            O nome genero da primeira paisagem musical da playlist.
	 */
	public void setPrimGenero(String primGenero) {
		this.primGenero = primGenero;
	}

	/**
	 * Retorna o genero da segunda paisagem musical.
	 * 
	 * @return O genero da segunda paisagem musical.
	 */
	public String getSegGenero() {
		return this.segGenero;
	}

	/**
	 * Altera o genero da segunda paisagem musical da playlist.
	 * 
	 * @param segGenero
	 *            O nome genero da segunda paisagem musical da playlist.
	 */
	public void setSegGenero(String segGenero) {
		this.segGenero = segGenero;
	}

	/**
	 * Retorna a lista com as musicas da primeira paisagem musical.
	 * 
	 * @return A lista com as musicas da primeira paisagem musical.
	 */
	public List<Musica> getPrimPaisagem() {
		return this.primPaisagem;
	}

	/**
	 * Altera a lista das musicas da primeira paisagem musical.
	 * 
	 * @param primPaisagem
	 *            A nova lista das musicas da primeira paisagem musical.
	 */
	public void setPrimPaisagem(List<Musica> primPaisagem) {
		this.primPaisagem = primPaisagem;
	}

	/**
	 * Retorna a lista com as musicas da segunda paisagem musical.
	 * 
	 * @return A lista com as musicas da segunda paisagem musical.
	 */
	public List<Musica> getSegPaisagem() {
		return this.segPaisagem;
	}

	/**
	 * Altera a lista das musicas da segunda paisagem musical.
	 * 
	 * @param segPaisagem
	 *            A nova lista das musicas da segunda paisagem musical.
	 */
	public void setSegPaisagem(List<Musica> segPaisagem) {
		this.segPaisagem = segPaisagem;
	}

	/**
	 * Retorna a musica de transicao da playlist.
	 * 
	 * @return A musica de transicao da playlist.
	 */
	public Musica getTransicao() {
		return this.transicao;
	}

	/**
	 * Altera a musica de transicao da playlist.
	 * 
	 * @param transicao
	 *            A nova musica de transicao da playlist.
	 */
	public void setTransicao(Musica transicao) {
		this.transicao = transicao;
	}

	/**
	 * Retorna uma lista com todas as músicas da playlist ordenadas por primeira
	 * paisagem, música de transição e segunda paisagem.
	 * 
	 * @return A lista com todas as músicas da playlist.
	 */
	public List<Musica> getMusicas() {
		List<Musica> musicas = new ArrayList<Musica>();
		musicas.addAll(primPaisagem);
		musicas.add(transicao);
		musicas.addAll(segPaisagem);
		return Collections.unmodifiableList(musicas);
	}

	/**
	 * Retorna a quantidade total de músicas da playlist.
	 * 
	 * @return A quantidade total de músicas.
	 */
	public int getTotalDeMusicas() {
		return getMusicas().size();
	}

}
