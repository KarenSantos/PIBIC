package models;

/**
 * Classe de música.
 * 
 * @author karen
 * 
 */
public class Musica {

	private String nome, id;

	/**
	 * Cria uma música com um nome e o id da música para o youtube.
	 * 
	 * @param nome
	 *            O nome da música.
	 * @param id
	 *            O id da música do youtube.
	 */
	public Musica(String nome, String id) {
		this.nome = nome;
		this.id = id;
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
	 * Altera o nome da musica.
	 * 
	 * @param nome
	 *            O novo nome da musica.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o id da música para o youtube.
	 * 
	 * @return O id da música.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Altera o id da musica para o youtube.
	 * 
	 * @param id
	 *            O novo id da musica.
	 */
	public void setId(String id) {
		this.id = id;
	}

}
