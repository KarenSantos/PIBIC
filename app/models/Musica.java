package models;

public class Musica {

	private String nome, artista, link;
	
	public Musica(String nome, String artista, String link) {
		this.nome = nome;
		this.artista = artista;
		
		//ajeitar a string do link
		
		this.link = link;
	}
	
	public String getLink(){
		return link;
	}

}
