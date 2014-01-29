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
		
		createSamplePlaylists();
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

	private void createSamplePlaylists() {

		// Playlist Andre Rocha
		List<Musica> andrePais1 = new ArrayList<Musica>();
		andrePais1.add(new Musica("Wonderful Slippery Thing", "Guthrie Govan", "http://www.youtube.com/watch?v=01gzVYDV5B8"));
		andrePais1.add(new Musica("Jammin’ on Sunny", "Greg Howe", "http://www.youtube.com/watch?v=edVbz_uXJd0"));
		andrePais1.add(new Musica("For the Love of God", "Steve Vai", "http://www.youtube.com/watch?v=okLDkcexiVg"));
		
		Musica andreTrans = new Musica("Instrumedley Live at Budokan", "Dream Theater", "http://www.youtube.com/watch?v=aqCkihctr0w");
		
		List<Musica> andrePais2 = new ArrayList<Musica>();
		andrePais2.add(new Musica("Fullmoon", "Sonata Arctica", "http://www.youtube.com/watch?v=sTqX4hY74KA"));
		andrePais2.add(new Musica("Late Redemption", "Angra", "http://www.youtube.com/watch?v=RiQwEknXPqo"));
		criarPlaylist(andrePais1, andrePais2, andreTrans, "Nome da playlist", "img000.jpg");
	}
}
