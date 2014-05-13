package models;

public class PlaylistIncompletaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PlaylistIncompletaException(String message){
		super(message);
	}

}
