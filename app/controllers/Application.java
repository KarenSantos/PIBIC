package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	private static Projeto projeto = new Projeto();
	
    public static Result index() {
        return ok(views.html.index.render(projeto.getPlaylists()));
    }
    
    public static Result novaPlaylist() {
		return ok(views.html.create.render());
	}

}