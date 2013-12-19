package models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	private int id;
	private List<Musica> primPaisagem, segPaisagem;
	private Musica transicao;
	private String nome, imagem;
	
	public Playlist(int id, List<Musica> primPaisagem,
			List<Musica> segPaisagem, Musica transicao, String nome, String imagem) {
		this.id = id;
		this.primPaisagem = primPaisagem;
		this.segPaisagem = segPaisagem;
		this.transicao = transicao;
		this.nome = nome;
		this.imagem = imagem;
	}

	public String getNome() {
		return nome;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public List<Musica> getMusicas() {
		List<Musica> musicas = new ArrayList<Musica>();
		musicas.addAll(primPaisagem);
		musicas.add(transicao);
		musicas.addAll(segPaisagem);
		return musicas;
	}

}
