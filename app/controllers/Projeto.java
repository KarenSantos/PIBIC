package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Musica;
import models.Playlist;

public class Projeto {

	private List<Playlist> playlists;
	private int id;
	
	public Projeto() {
		playlists = new ArrayList<Playlist>();
		id = 0;
	}
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public Playlist getPlaylist(int id) {
		return playlists.get(id);
	}

	public void criarPlaylist(List<Musica> primPaisagem, List<Musica> segPaisagem, 
									Musica transicao, String nome, String imagem) {
		Playlist aPlaylist = new Playlist(id, primPaisagem, segPaisagem, transicao, nome, imagem);
		playlists.add(aPlaylist);
		id ++;
	}
}
