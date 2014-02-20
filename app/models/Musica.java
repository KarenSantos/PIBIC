package models;

/**
 * Classe de música.
 * 
 * @author karen
 * 
 */
public class Musica {

	private String nome, artista, link;

	/**
	 * Cria uma música com um nome, um artista e um link da música para o
	 * youtube.
	 * 
	 * @param nome
	 *            O nome da música.
	 * @param artista
	 *            O artista da música.
	 * @param link
	 *            O link da música do youtube.
	 */
	public Musica(String nome, String artista, String link) {
		this.nome = nome;
		this.artista = artista;

		this.link = link.split("=")[1];
	}

	/**
	 * Retorna o nome da música.
	 * 
	 * @return O nome da música.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna o artista da música.
	 * 
	 * @return O artista da música.
	 */
	public String getArtista() {
		return artista;
	}

	/**
	 * Retorna o link da música.
	 * 
	 * @return O link da música.
	 */
	public String getLink() {
		return link;
	}

}
