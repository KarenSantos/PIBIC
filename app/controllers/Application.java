package controllers;

import models.Playlist;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	private static Projeto projeto = new Projeto();
	
    public static Result index() {
        return ok(views.html.index.render(projeto.getSamplePlaylists()));
    }
    
    public static Result novaPlaylist() {
		return ok(views.html.create.render());
	}

    public static Result getPlaylist(int id) {
    	Playlist play = projeto.getPlaylist(id);
        return ok(Json.toJson(play));
    }
}