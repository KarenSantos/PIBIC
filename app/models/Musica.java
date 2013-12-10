package models;

import play.data.validation.Constraints.Required;

public class Musica {
	
	@Required
	private String nome;
	@Required
	private String link;
	
	public Musica(String nome, String link) {
		this.nome = nome;
		this.link = link;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
