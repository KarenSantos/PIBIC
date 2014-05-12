
import java.util.ArrayList;
import java.util.List;

import models.Musica;
import models.Playlist;
import controllers.Projeto;
import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.*;
import views.html.*;
import static play.mvc.Results.*;

public class Global extends GlobalSettings {

	public Promise<SimpleResult> onError(RequestHeader request, Throwable t) {
		return Promise.<SimpleResult>pure(internalServerError(error.render("Erro encontrado:\n"
													+ t.getMessage())));
    }
	
	public Promise<SimpleResult> onHandlerNotFound(RequestHeader request) {
		return Promise.<SimpleResult>pure(notFound(error.render("A página \"" + request.uri()
				+ "\" não foi encontrada no nosso app. Tente outra coisa =).")));
	}
	
	public Promise<SimpleResult> onBadRequest(RequestHeader request, String error) {
        return Promise.<SimpleResult>pure(badRequest("Don't try to hack the URI!"));
    }
	
	public void onStart(Application app) {
		
		//criar as perguntas e a survey
		
	}  
}