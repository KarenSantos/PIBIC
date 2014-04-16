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
	 * Cria uma playlist com uma lista de músicas da primeira paisagem, uma
	 * lista de músicas da segunda paisagem, uma música de transição um nome e
	 * uma imagem.
	 * 
	 * @param primPaisagem
	 *            A lista de músicas da primeira paisagem.
	 * @param segPaisagem
	 *            A lista de músicas da segunda paisagem.
	 * @param transicao
	 *            A música de transição.
	 * @param primGenero
	 *            O gênero da primeira paisagem musical.
	 * @param segGenero
	 *            O gênero da segunda paisagem musical.
	 * @param nome
	 *            O nome da playlist criada.
	 * @param imagem
	 *            O nome da imagem da playlist.
	 */
	public void criarPlaylist(List<Musica> primPaisagem,
			List<Musica> segPaisagem, Musica transicao, String primGenero, 
			String segGenero, String nome, String imagem) {
		Playlist aPlaylist = new Playlist(id, primPaisagem, segPaisagem,
				transicao, primGenero, segGenero, nome, imagem);
		playlists.add(aPlaylist);
		id++;
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
		andrePais1.add(new Musica("Wonderful Slippery Thing", "Guthrie Govan",
				"http://www.youtube.com/watch?v=01gzVYDV5B8"));
		andrePais1.add(new Musica("Jammin’ on Sunny", "Greg Howe",
				"http://www.youtube.com/watch?v=edVbz_uXJd0"));
		andrePais1.add(new Musica("For the Love of God", "Steve Vai",
				"http://www.youtube.com/watch?v=okLDkcexiVg"));

		Musica andreTrans = new Musica("Instrumedley Live at Budokan",
				"Dream Theater", "http://www.youtube.com/watch?v=aqCkihctr0w");

		List<Musica> andrePais2 = new ArrayList<Musica>();
		andrePais2.add(new Musica("Fullmoon", "Sonata Arctica",
				"http://www.youtube.com/watch?v=sTqX4hY74KA"));
		andrePais2.add(new Musica("Late Redemption", "Angra",
				"http://www.youtube.com/watch?v=RiQwEknXPqo"));
		criarPlaylist(andrePais1, andrePais2, andreTrans, "Rock Instrumental", "Metal Melódico", "Playlist de André","0.jpg");
		
		// Playlist Diego Vilela
		List<Musica> diegoPais1 = new ArrayList<Musica>();
		diegoPais1.add(new Musica("Diga", "Fresno",
				"http://www.youtube.com/watch?v=T7uleRm-4sM"));
		diegoPais1.add(new Musica("Best Of You", "Foo Fighters",
				"http://www.youtube.com/watch?v=MtfE72Ni9_Y"));
		diegoPais1.add(new Musica("Decode", "Paramore",
				"http://www.youtube.com/watch?v=RvnkAtWcKYg"));

		Musica diegoTrans = new Musica("The Joke",
				"Lifehouse", "http://www.youtube.com/watch?v=Ua8d3gDSXyc");

		List<Musica> diegoPais2 = new ArrayList<Musica>();
		diegoPais2.add(new Musica("O Preço", "Charlie Brown Jr.",
				"http://www.youtube.com/watch?v=GH7MB6a35aU"));
		diegoPais2.add(new Musica("A Thousand Years", "Christina Perri",
				"http://www.youtube.com/watch?v=rtOvBOTyX00"));
		diegoPais2.add(new Musica("Forasteiro", "Natiruts",
				"http://www.youtube.com/watch?v=wbKRLtfWPdI"));
		criarPlaylist(diegoPais1, diegoPais2, diegoTrans, "Música Agitada", "Música Lenta", "Playlist de Diego","1.jpg");
		
		// Playlist Andrew Marques
		List<Musica> andrewPais1 = new ArrayList<Musica>();
		andrewPais1.add(new Musica("Make Some Noise", "Chuckie & Junxterjack",
				"http://www.youtube.com/watch?v=wB6fV7VM2AU"));
		andrewPais1.add(new Musica("Levels", "Avicii",
				"http://www.youtube.com/watch?v=_ovdm2yX4MA"));
		andrewPais1.add(new Musica("I Could Be The One", "Avicii vs Nicky Romero",
				"http://www.youtube.com/watch?v=bek1y2uiQGA"));
		andrewPais1.add(new Musica("Thing Called Love", "Above & Beyond feat. Richard Bedford",
				"http://www.youtube.com/watch?v=ZgRnLM9Vi24"));
		andrewPais1.add(new Musica("Don't you Worry Child", "Swedish House Mafia",
				"http://www.youtube.com/watch?v=1y6smkh6c-0"));

		Musica andrewTrans = new Musica("In My Mind",
				"Flo Rida", "http://www.youtube.com/watch?v=MeDyhpcng9c");

		List<Musica> andrewPais2 = new ArrayList<Musica>();
		andrewPais2.add(new Musica("Wild For the Night", "ASAP Rocky",
				"http://www.youtube.com/watch?v=4A-5SexRmtI"));
		andrewPais2.add(new Musica("Scream & Shout (remix)", "Will.I.am",
				"http://www.youtube.com/watch?v=lEKOWKcUxdU"));
		andrewPais2.add(new Musica("Mercy", "Kanye West",
				"http://www.youtube.com/watch?v=7Dqgr0wNyPo"));
		criarPlaylist(andrewPais1, andrewPais2, andrewTrans, "Música Eletrônica", "Rap", "Playlist de Andrew", "2.jpg");
		
		// Playlist Djaildo Quaresma
		List<Musica> djaildoPais1 = new ArrayList<Musica>();
		djaildoPais1.add(new Musica("Mr. Blue Sky", "Electric Light Orchestra",
				"http://www.youtube.com/watch?v=bjPqsDU0j2I"));
		djaildoPais1.add(new Musica("Raindrops Keep Falling On My Head", "BJ Thomas",
				"http://www.youtube.com/watch?v=VILWkqlQLWk"));
		djaildoPais1.add(new Musica("Don’t Worry Be Happy", "Bobby McFerrin",
				"http://www.youtube.com/watch?v=d-diB65scQU"));
		djaildoPais1.add(new Musica("I’m Yours", "Jason Mraz",
				"http://www.youtube.com/watch?v=EkHTsc9PU2"));
		djaildoPais1.add(new Musica("Wouldn’t It Be Nice", "The Beach Boys",
				"http://www.youtube.com/watch?v=-E4FRtrD9aQ"));

		Musica djaildoTrans = new Musica("California Dreamin’",
				"The Mamas & The Papas", "http://www.youtube.com/watch?v=dN3GbF9Bx6E");

		List<Musica> djaildoPais2 = new ArrayList<Musica>();
		djaildoPais2.add(new Musica("London Calling", "The Clash",
				"http://www.youtube.com/watch?v=4vHvzybkqfo"));
		djaildoPais2.add(new Musica("Snowblind", "Black Sabbath",
				"http://www.youtube.com/watch?v=qHal84S_XkI"));
		djaildoPais2.add(new Musica("Gimme Shelter", "The Rolling Stones",
				"http://www.youtube.com/watch?v=n_a0zOLMAfw"));
		djaildoPais2.add(new Musica("Since I’ve Been Loving You", "Led Zeppelin",
				"http://www.youtube.com/watch?v=Bkjv9SscotY"));
		djaildoPais2.add(new Musica("Once", "Van Halen",
				"http://www.youtube.com/watch?v=I19X-m3vWnk"));
		criarPlaylist(djaildoPais1, djaildoPais2, djaildoTrans, "Música Feliz", "Música Melancólica", "Playlist de Djaildo", "3.gif");
		
		// Playlist Vitor Amaral
		List<Musica> vitorPais1 = new ArrayList<Musica>();
		vitorPais1.add(new Musica("Through The Shadows", "Insomnium",
				"http://www.youtube.com/watch?v=sZmK2ZWBxQc"));
		vitorPais1.add(new Musica("Eternal Tears Of Sorrow", "Aurora Borealis",
				"http://www.youtube.com/watch?v=1BhKJX5aiAk"));
		
		Musica vitorTrans = new Musica("From Broken Vessels", "Orphaned Land",
				"http://www.youtube.com/watch?v=UG213nX6cto");

		List<Musica> vitorPais2 = new ArrayList<Musica>();
		vitorPais2.add(new Musica("Beyond the Stars", "Myrath",
				"http://www.youtube.com/watch?v=H9V-AxS2kao"));
		vitorPais2.add(new Musica("New Jerusalem", "Orphaned Land",
				"http://www.youtube.com/watch?v=UB8Qx_Hce1s"));
		vitorPais2.add(new Musica("Setting Sail, Coming Home", "Bastion Soundtrack",
				"http://www.youtube.com/watch?v=GDflVhOpS4E"));
		vitorPais2.add(new Musica("I Was Born for This", "Journey Soundtrack",
				"http://www.youtube.com/watch?v=qizpBpHTzkU"));
		criarPlaylist(vitorPais1, vitorPais2, vitorTrans, "Metal", "Música Árabe", "Playlist de Vitor", "4.jpg");
	}

}
