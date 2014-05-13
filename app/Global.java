
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;
import play.Application;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.SimpleResult;
import views.html.error;

public class Global extends GlobalSettings {

	public Promise<SimpleResult> onError(RequestHeader request, Throwable t) {
		return Promise.<SimpleResult>pure(internalServerError(error.render("Erro encontrado:\n"
													+ t.getStackTrace())));
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