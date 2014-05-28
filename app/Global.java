
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import models.*;
import play.Application;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.SimpleResult;
import views.html.error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Global extends GlobalSettings {
	
	private final String ID_SURVEY_PADRAO = "padrao";
	
	private static Logger logger = LoggerFactory.getLogger(Global.class);

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
		
		if (Survey.find.byId(ID_SURVEY_PADRAO) == null){
			criaSurvey();
		}
	}
	
	private void criaSurvey(){
		List<Question> questions = new ArrayList<Question>();
		BufferedReader reader = null;
		try {
			logger.info("vai ler o arquivo");
			String currentLine;
			File surveyText = new File("./public/survey.txt");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(surveyText), "ISO-8859-1"));
			Question q = new Question();
			int optionNum = 1;
//			int questionNum = 0;
			logger.error("achou o arquivo");
			
			while ((currentLine = reader.readLine()) != null) {
				
				String s = currentLine;
				
				if (s.startsWith("q")){
//					q.setId(questionNum + "");
            		q.setQuestion(s.split("-")[1]);
//            		questionNum++;
            	} else if (s.startsWith("o")){
            		QuestionOption opt = new QuestionOption(optionNum, s.split("-")[1]);
            		q.addOption(opt);
            		optionNum++;
            	} else if (s.startsWith(".")){
            		questions.add(q);
            		q = new Question();
            		optionNum = 1;
            	}
			}
			
			salvaSurvey(questions);
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void salvaSurvey(List<Question> questions){
		Survey survey = new Survey(ID_SURVEY_PADRAO);
		survey.setQuestions(questions);
		survey.save();
	}
}