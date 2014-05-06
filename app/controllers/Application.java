package controllers;

import models.Playlist;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	private static Projeto projeto = new Projeto();
		
    public static Result index() {
    	projeto.limpaNovaPlaylist();
        return ok(index.render(projeto.getSamplePlaylists()));
    }

    public static Result getPlaylist(int id) {
    	Playlist playlist = projeto.getPlaylist(id);
        return ok(Json.toJson(playlist));
    }
    
    public static Result novaPlaylist() {
    	return ok(create.render(Form.form(Playlist.class)));
    }
    
    public static Result addPrimPaisagem(String nome, String id){
    	projeto.addMusicaPrimPaisagem(nome, id);
    	return ok();
    }
    
    public static Result addSegPaisagem(String nome, String id){
    	projeto.addMusicaSegPaisagem(nome, id);
    	return ok();
    }
 
	public static Result setTransicao(String nome, String id){
		projeto.setMusicaTransicao(nome, id);
		return ok();
	}

    public static Result criaPlaylist(){

    	Form<Playlist> playlistForm = Form.form(Playlist.class).bindFromRequest();
    	
    	if (playlistForm.hasErrors()) {
			return badRequest(create.render(playlistForm));
		
    	} else {
			projeto.configuraNovaPlaylist(playlistForm.get());
			flash("error", "ok, estamos na proxima pagina");
		}
    	return redirect(routes.Application.survey());
    }
    
    public static Result survey(){
    	return ok(survey.render());
    }
    
    public static Result salvarPlaylist(){
    	projeto.salvarPlaylist();
    	return redirect(routes.Application.index());
    }
}