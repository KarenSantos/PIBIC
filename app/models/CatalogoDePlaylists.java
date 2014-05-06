package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Classe de catálogo de playlists.
 * 
 * @author karen
 * 
 */
public class CatalogoDePlaylists {

	private List<Playlist> playlists;
	private int id;

	/**
	 * Cria um Catalogo de Playlists com uma lista de playlists inicialmente
	 * vazia;
	 */
	public CatalogoDePlaylists() {
		playlists = new ArrayList<Playlist>();
		id = 0;

		createSamplePlaylists();
	}

	/**
	 * Retorna a lista com todas as playlists criadas.
	 * 
	 * @return A lista de playlists.
	 */
	public List<Playlist> getPlaylists() {
		return Collections.unmodifiableList(playlists);
	}

	/**
	 * Retorna uma playlist a partir de um id.
	 * 
	 * @param id
	 *            O id da playlist desejada.
	 * @return A playlist do id indicado.
	 */
	public Playlist getPlaylist(int id) {
		return playlists.get(id);
	}

	/**
	 * Retorna uma playlist a partir de um id.
	 * 
	 * @param id
	 *            O id da playlist desejada.
	 * @return A playlist do id indicado.
	 */
	public Playlist getPlaylist(String id) {
		return playlists.get(Integer.parseInt(id));
	}

	/**
	 * Retorna a quantidade total de playlists criadas.
	 * 
	 * @return A quantidade de playlists criadas.
	 */
	public int getTotalDePlaylists() {
		return getPlaylists().size();
	}

	/**
	 * Adiciona uma playlist a lista de playlists do catalogo e seta seu id.
	 * 
	 * @param playlist
	 *            A playlist a ser adicionada.
	 */
	public void addPlaylist(Playlist playlist) {
		playlist.setId(this.id);
		//salvar playlist no BD
		playlists.add(playlist);
		id++;
		//fazer update? de this
	}

	/**
	 * Retorna uma lista com uma amostra aleatória de uma determinada quantidade
	 * de playlists.
	 * 
	 * @param num
	 *            A quantidade de playlists da amostra.
	 * @return A lista de amostras com playlists.
	 */
	public List<Playlist> getSamplePlaylists(int num) {
		List<Playlist> sample = new ArrayList<Playlist>();

		Random randomGenerator = new Random();
		Set<Integer> indices = new HashSet<Integer>();
		int amount = num;
		if (getTotalDePlaylists() < num) {
			amount = getTotalDePlaylists();
		}
		while (indices.size() < amount) {
			indices.add(randomGenerator.nextInt(getTotalDePlaylists()));
		}

		for (Integer indice : indices) {
			sample.add(getPlaylists().get(indice));
		}
		return sample;
	}

	/**
	 * Cria as playlists dos entrevistados no projeto.
	 */
	private void createSamplePlaylists() {

		// Playlist Andre Rocha
		List<Musica> andrePais1 = new ArrayList<Musica>();
		andrePais1.add(new Musica("Wonderful Slippery Thing", 
				"http://www.youtube.com/watch?v=01gzVYDV5B8"));
		andrePais1.add(new Musica("Jammin’ on Sunny", 
				"http://www.youtube.com/watch?v=edVbz_uXJd0"));
		andrePais1.add(new Musica("For the Love of God", 
				"http://www.youtube.com/watch?v=okLDkcexiVg"));

		Musica andreTrans = new Musica("Instrumedley Live at Budokan",
				"http://www.youtube.com/watch?v=aqCkihctr0w");

		List<Musica> andrePais2 = new ArrayList<Musica>();
		andrePais2.add(new Musica("Fullmoon", 
				"http://www.youtube.com/watch?v=sTqX4hY74KA"));
		andrePais2.add(new Musica("Late Redemption", 
				"http://www.youtube.com/watch?v=RiQwEknXPqo"));
		Playlist playlist = new Playlist(andrePais1, andrePais2, andreTrans, "Rock Instrumental", "Metal Melódico", "Playlist de André","0.jpg");
		addPlaylist(playlist);
		
		// Playlist Diego Vilela
		List<Musica> diegoPais1 = new ArrayList<Musica>();
		diegoPais1.add(new Musica("Diga", 
				"http://www.youtube.com/watch?v=T7uleRm-4sM"));
		diegoPais1.add(new Musica("Best Of You", 
				"http://www.youtube.com/watch?v=MtfE72Ni9_Y"));
		diegoPais1.add(new Musica("Decode", 
				"http://www.youtube.com/watch?v=RvnkAtWcKYg"));

		Musica diegoTrans = new Musica("The Joke",
				"http://www.youtube.com/watch?v=Ua8d3gDSXyc");

		List<Musica> diegoPais2 = new ArrayList<Musica>();
		diegoPais2.add(new Musica("O Preço", 
				"http://www.youtube.com/watch?v=GH7MB6a35aU"));
		diegoPais2.add(new Musica("A Thousand Years", 
				"http://www.youtube.com/watch?v=rtOvBOTyX00"));
		diegoPais2.add(new Musica("Forasteiro", 
				"http://www.youtube.com/watch?v=wbKRLtfWPdI"));
		playlist = new Playlist(diegoPais1, diegoPais2, diegoTrans, "Música Agitada", "Música Lenta", "Playlist de Diego","1.jpg");
		addPlaylist(playlist);
		
		// Playlist Andrew Marques
		List<Musica> andrewPais1 = new ArrayList<Musica>();
		andrewPais1.add(new Musica("Make Some Noise", 
				"http://www.youtube.com/watch?v=wB6fV7VM2AU"));
		andrewPais1.add(new Musica("Levels", 
				"http://www.youtube.com/watch?v=_ovdm2yX4MA"));
		andrewPais1.add(new Musica("I Could Be The One", 
				"http://www.youtube.com/watch?v=bek1y2uiQGA"));
		andrewPais1.add(new Musica("Thing Called Love", 
				"http://www.youtube.com/watch?v=ZgRnLM9Vi24"));
		andrewPais1.add(new Musica("Don't you Worry Child", 
				"http://www.youtube.com/watch?v=1y6smkh6c-0"));

		Musica andrewTrans = new Musica("In My Mind",
				 "http://www.youtube.com/watch?v=MeDyhpcng9c");

		List<Musica> andrewPais2 = new ArrayList<Musica>();
		andrewPais2.add(new Musica("Wild For the Night", 
				"http://www.youtube.com/watch?v=4A-5SexRmtI"));
		andrewPais2.add(new Musica("Scream & Shout (remix)", 
				"http://www.youtube.com/watch?v=lEKOWKcUxdU"));
		andrewPais2.add(new Musica("Mercy", 
				"http://www.youtube.com/watch?v=7Dqgr0wNyPo"));
		playlist = new Playlist(andrewPais1, andrewPais2, andrewTrans, "Música Eletrônica", "Rap", "Playlist de Andrew", "2.jpg");
		addPlaylist(playlist);
		
		
		// Playlist Djaildo Quaresma
		List<Musica> djaildoPais1 = new ArrayList<Musica>();
		djaildoPais1.add(new Musica("Mr. Blue Sky", 
				"http://www.youtube.com/watch?v=bjPqsDU0j2I"));
		djaildoPais1.add(new Musica("Raindrops Keep Falling On My Head", 
				"http://www.youtube.com/watch?v=VILWkqlQLWk"));
		djaildoPais1.add(new Musica("Don’t Worry Be Happy", 
				"http://www.youtube.com/watch?v=d-diB65scQU"));
		djaildoPais1.add(new Musica("I’m Yours", 
				"http://www.youtube.com/watch?v=EkHTsc9PU2"));
		djaildoPais1.add(new Musica("Wouldn’t It Be Nice", 
				"http://www.youtube.com/watch?v=-E4FRtrD9aQ"));

		Musica djaildoTrans = new Musica("California Dreamin’",
				 "http://www.youtube.com/watch?v=dN3GbF9Bx6E");

		List<Musica> djaildoPais2 = new ArrayList<Musica>();
		djaildoPais2.add(new Musica("London Calling", 
				"http://www.youtube.com/watch?v=4vHvzybkqfo"));
		djaildoPais2.add(new Musica("Snowblind", 
				"http://www.youtube.com/watch?v=qHal84S_XkI"));
		djaildoPais2.add(new Musica("Gimme Shelter", 
				"http://www.youtube.com/watch?v=n_a0zOLMAfw"));
		djaildoPais2.add(new Musica("Since I’ve Been Loving You", 
				"http://www.youtube.com/watch?v=Bkjv9SscotY"));
		djaildoPais2.add(new Musica("Once", 
				"http://www.youtube.com/watch?v=I19X-m3vWnk"));
		playlist = new Playlist(djaildoPais1, djaildoPais2, djaildoTrans, "Música Feliz", "Música Melancólica", "Playlist de Djaildo", "3.gif");
		addPlaylist(playlist);
		
		
		// Playlist Vitor Amaral
		List<Musica> vitorPais1 = new ArrayList<Musica>();
		vitorPais1.add(new Musica("Through The Shadows", 
				"http://www.youtube.com/watch?v=sZmK2ZWBxQc"));
		vitorPais1.add(new Musica("Eternal Tears Of Sorrow", 
				"http://www.youtube.com/watch?v=1BhKJX5aiAk"));
		
		Musica vitorTrans = new Musica("From Broken Vessels", 
				"http://www.youtube.com/watch?v=UG213nX6cto");

		List<Musica> vitorPais2 = new ArrayList<Musica>();
		vitorPais2.add(new Musica("Beyond the Stars", 
				"http://www.youtube.com/watch?v=H9V-AxS2kao"));
		vitorPais2.add(new Musica("New Jerusalem", 
				"http://www.youtube.com/watch?v=UB8Qx_Hce1s"));
		vitorPais2.add(new Musica("Setting Sail, Coming Home", 
				"http://www.youtube.com/watch?v=GDflVhOpS4E"));
		vitorPais2.add(new Musica("I Was Born for This", 
				"http://www.youtube.com/watch?v=qizpBpHTzkU"));
		playlist = new Playlist(vitorPais1, vitorPais2, vitorTrans, "Metal", "Música Árabe", "Playlist de Vitor", "4.jpg");
		addPlaylist(playlist);
	}

}
