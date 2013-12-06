package models;

import java.util.List;

import play.data.validation.Constraints.Required;

public class Playlist {
	
	@Required
	private List<Musica>[] primPaisagem;
	@Required
	private Musica transicao;
	@Required
	private List<Musica>[] segPaisagem;
	
}
