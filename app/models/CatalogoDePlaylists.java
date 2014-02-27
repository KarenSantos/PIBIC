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
	 * @param nome
	 *            O nome da playlist criada.
	 * @param imagem
	 *            O nome da imagem da playlist.
	 */
	public void criarPlaylist(List<Musica> primPaisagem,
			List<Musica> segPaisagem, Musica transicao, String nome,
			String imagem) {
		Playlist aPlaylist = new Playlist(id, primPaisagem, segPaisagem,
				transicao, nome, imagem);
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
		criarPlaylist(andrePais1, andrePais2, andreTrans, "Nome da playlist",
				"img000.jpg");
	}

}
