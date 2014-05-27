package controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.AlocacaoInvalidaException;
import models.Answer;
import models.ParametroInvalidoException;
import models.Playlist;
import models.PlaylistIncompletaException;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.create;
import views.html.index;
import views.html.survey;

public class Application extends Controller {

	private static final int IMG_WIDTH = 270;
	private static final int IMG_HEIGHT = 160;
	private static final String IMAGEM_PADRAO = "0.jpg";

	private static Projeto projeto = new Projeto();

	public static Result index() {
		projeto.limpaNovaPlaylist();
		return ok(index.render(projeto.getSamplePlaylists()));
	}

	public static Result getPlaylist(String id) {
		Playlist playlist = projeto.getPlaylist(id);
		return ok(Json.toJson(playlist));
	}

	public static Result novaPlaylist() {
		projeto.limpaNovaPlaylist();
		return ok(create.render(Form.form(Playlist.class)));
	}

	public static Result addPrimPaisagem(String nome, String id) {
		try {
			projeto.addMusicaPrimPaisagem(nome, id);
		} catch (AlocacaoInvalidaException a) {
			return badRequest();
		}
		return ok();
	}

	public static Result addSegPaisagem(String nome, String id) {
		try {
			projeto.addMusicaSegPaisagem(nome, id);
		} catch (AlocacaoInvalidaException e) {
			return badRequest();
		}
		return ok();
	}

	public static Result setTransicao(String nome, String id) {
		try {
			projeto.setMusicaTransicao(nome, id);
		} catch (AlocacaoInvalidaException e) {
			return badRequest();
		}
		return ok();
	}

	public static Result removeMusica(String id){
		projeto.removeMusica(id);
		return ok();
	}
	
	public static Result criaPlaylist() {

		Form<Playlist> playlistForm = Form.form(Playlist.class)
				.bindFromRequest();
		Playlist playlist = null;

		if (playlistForm.hasErrors()) {
			return badRequest(create.render(playlistForm));

		} else {

			playlist = playlistForm.get();
			playlist.setId(Playlist.find.all().size() + 1 + "");

			MultipartFormData body = request().body().asMultipartFormData();
			FilePart imagem = body.getFile("imagem");

			if (imagem == null) {
				playlist.setImagem(IMAGEM_PADRAO);
			} else {
				File file = imagem.getFile();
				try {
					configuraImagem(file, playlist.getId());
					playlist.setImagem(playlist.getId() + ".jpg");
				} catch (IOException e) {
					playlist.setImagem(IMAGEM_PADRAO);
					flash("erro",
							"Um problema foi encontrado ao fazer o upload da imagem. Sua playlist foi salva com a imagem padrão.");
				}
			}
			projeto.configuraNovaPlaylist(playlist);

			try {
				projeto.salvarPlaylist();
			} catch (PlaylistIncompletaException e) {
				flash("error",
						"Um erro ocorreu ao tentar salvar sua playlist. Tente mais tarde");
				return redirect(routes.Application.novaPlaylist());
			}
		}

		return redirect(routes.Application.survey(playlist.getId()));
	}

	public static Result survey(String id) {
		try {
			projeto.criaSurveyAnswerParaNovaPlaylist();
		} catch (PlaylistIncompletaException e) {
			flash("error", "Você precisa criar uma playlist para poder responder a survey.");
			return redirect(routes.Application.novaPlaylist());
		}
		return ok(survey.render(Playlist.find.byId(id), projeto.getSurveyAnswer()));
	}

	public static Result respostas() {

		DynamicForm requestData = Form.form().bindFromRequest();

		for (Answer answer : projeto.getSurveyAnswer().getAnswers()) {
			String stringAnswer = requestData.get("question" + answer.getQuestion().getId());
			if (stringAnswer != null && !stringAnswer.equals("")){
				if (answer.getQuestion().hasOptions()) {
					int option;
					try {
						option = Integer.parseInt(stringAnswer);
						projeto.respondePerguntaComOption(answer, projeto.getOptionForQuestion(answer, option));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						projeto.respondePerguntaAberta(answer, stringAnswer);
					} catch (ParametroInvalidoException e) {
						flash("Ocorreu um erro, tente mais tarde.");
						return redirect(routes.Application.novaPlaylist());
					}
				}
			}
		}
		if (projeto.isSurveyAnswerRespondida()) {
			projeto.salvaNovaSurvey();
			flash("success", "Obrigado por submeter sua survey.");
			return redirect("/#work");
		} else {
			return redirect(routes.Application.index());
		}
	}

	private static File configuraImagem(File file, String id)
			throws IOException {
		BufferedImage img;
		BufferedImage resized = null;

		// creates an image from the uploaded file and resizes it
		img = ImageIO.read(file);
		resized = resizeImgTo(img, IMG_WIDTH, IMG_HEIGHT);

		// converts the image back to file
		File newImage = new File("public/img/playImgs", id + ".jpg");
		ImageIO.write(resized, "jpg", newImage);

		return newImage;
	}

	private static BufferedImage resizeImgTo(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}
}