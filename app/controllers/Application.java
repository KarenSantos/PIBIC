package controllers;

import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.create;

public class Application extends Controller {

	private static Projeto projeto = new Projeto();
	
    public static Result index() {
        return ok(index.render(projeto.getSamplePlaylists()));
    }

    public static Result getPlaylist(int id) {
    	Playlist playlist = projeto.getPlaylist(id);
        return ok(Json.toJson(playlist));
    }
    
    public static Result novaPlaylist() {
    	return ok(create.render(Form.form(Playlist.class)));
    }

    public static Result criaPlaylist(){
    	
    	Playlist novaPlaylist = null;
    	
    	Form<Playlist> playlistForm = Form.form(Playlist.class).bindFromRequest();
		if (playlistForm.hasErrors()) {
			return badRequest(create.render(playlistForm));
		} else {
			novaPlaylist = playlistForm.get();
		}
		flash("error", novaPlaylist.getNome());
		return redirect(routes.Application.novaPlaylist());
    }
}