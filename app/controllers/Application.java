package controllers;

import models.Playlist;
import play.mvc.Controller;
import play.mvc.Result;
import play.api.mvc.BodyParser;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Application extends Controller {

	private static Projeto projeto = new Projeto();
	
    public static Result index() {
        return ok(views.html.index.render(projeto.getPlaylists()));
    }
    
    public static Result novaPlaylist() {
		return ok(views.html.create.render());
	}

//    @BodyParser.Of(BodyParser.Json.class)
//    public static Result getPlaylist(int id) {
//    	Playlist play = projeto.getPlaylist(id);
//    	
//    	JsonNode json = request().body().asJson();
//    	ObjectNode result = Json.newObject();
//        result.put("nome", play.getNome());
//        result.put("imagem", play.getImagem());
//        return ok(result);
//    }
    
//    public static Result getTasks(int id) {
//        List<Task> tasks = new Model.Finder(String.class, Task.class).all();
//        return ok(toJson(tasks));
//    }
    
}