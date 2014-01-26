package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	private static Projeto projeto = new Projeto();
	
    public static Result index() {
        return ok(views.html.index.render(projeto.getPlaylists()));
    }

}