package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

/**
 * Classe de música.
 * 
 * @author karen
 * 
 */
@Entity
@Table(name = "musicas")
public class Musica extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String nome;
	
	public static Finder<String, Musica> find = new Finder<String, Musica>(
			String.class, Musica.class);
	
	/**
	 * Cria uma musica vazia.
	 */
	public Musica(){}
	
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
	
	/**
	 * Uma musica eh igual a outra se tem o mesmo id.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Musica) {
			Musica mus = (Musica) obj;
			if (mus.getId() == getId()) {
				return true;
			}
		}
		return false;
	}

}
