package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.Constraints.Required;

public class Playlist {
	
	@Required
	private List<Musica> primPaisagem;
	@Required
	private Musica transicao;
	@Required
	private List<Musica> segPaisagem;
	
	
	public Playlist() {
		primPaisagem = new ArrayList<Musica>();
		segPaisagem = new ArrayList<Musica>();
	}

	public List<Musica> getPrimPaisagem() {
		return primPaisagem;
	}
	
	public List<Musica> segPrimPaisagem() {
		return segPaisagem;
	}
	
	public Musica getTransicao() {
		return transicao;
	}
	
	public void addMusicaPrimPaisagem (Musica musica){
		primPaisagem.add(musica);
	}
	
	public void addMusicaSegPaisagem (Musica musica) {
		segPaisagem.add(musica);
	}
	
	public void setTransicao(Musica musica){
		transicao = musica;
	}
	
	public List<Musica> getAll(){
		List<Musica> all = new ArrayList<Musica>();
		for (Musica musica : primPaisagem) {
			all.add(musica);
		}
		all.add(transicao);
		for (Musica musica : segPaisagem) {
			all.add(musica);
		}
		return all;
	}
	
}
