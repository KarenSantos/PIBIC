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


}
