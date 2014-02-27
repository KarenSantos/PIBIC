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
	private String nome, imagem;
	private List<Musica> primPaisagem, segPaisagem;
	private Musica transicao;

	/**
	 * Cria uma playlist com um id, uma lista de músicas da primeira paisagem,
	 * uma lista de músicas da segunda paisagem, uma música de transição, um
	 * nome e uma imagem.
	 * 
	 * @param id
	 *            O id da playlist.
	 * @param primPaisagem
	 *            A lista de músicas da primeira paisagem.
	 * @param segPaisagem
	 *            A lista de músicas da segunda paisagem.
	 * @param transicao
	 *            A música de transição.
	 * @param nome
	 *            O nome da playlist.
	 * @param imagem
	 *            O endereço da imagem da playlist.
	 */
	public Playlist(int id, List<Musica> primPaisagem,
			List<Musica> segPaisagem, Musica transicao, String nome,
			String imagem) {
		this.id = id;
		this.primPaisagem = primPaisagem;
		this.segPaisagem = segPaisagem;
		this.transicao = transicao;
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
	 * Retorna o nome da playlist.
	 * 
	 * @return O nome da playlist.
	 */
	public String getNome() {
		return nome;
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
