package controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.Playlist;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	private static final int imgWidth = 270;
	private static final int imgHeight = 160;
	
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
    	String playlistId = null;
    	
    	if (playlistForm.hasErrors()) {
			return badRequest(create.render(playlistForm));
		
    	} else {
			projeto.configuraNovaPlaylist(playlistForm.get());
			playlistId = projeto.salvarPlaylist();
		}
    	
    	MultipartFormData body = request().body().asMultipartFormData();
    	FilePart imagem = body.getFile("imagem");
   		File file = imagem.getFile();
   		
   		BufferedImage img;
   		BufferedImage resized = null;
   		//creates an image from the uploaded file and resizes it
		try {
			img = ImageIO.read(file);
			resized = resizeImgTo(img, imgWidth, imgHeight);
		} catch (IOException e) {
			e.printStackTrace();
			flash("erro", "Um problema foi encontrado ao fazer o upload da imagem.");
		}

		//converts the image back to file
		try {
			ImageIO.write(resized, "jpg", new File("public/img/playImgs", playlistId));
		} catch (IOException e) {
			e.printStackTrace();
			flash("erro", "Um problema foi encontrado ao fazer o upload da imagem.");
		}
    	
    	return redirect(routes.Application.survey(playlistId));
    }
    
    public static Result survey(String id){
    	return ok(survey.render(id));
    }
    
    private static BufferedImage resizeImgTo(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
    
    public static Result respostas(){
    	
    	DynamicForm requestData = Form.form().bindFromRequest();
        String q1 = requestData.get("question1");
        String q2 = requestData.get("question2");
    	
    	System.out.println(q1);
    	
    	return redirect(routes.Application.survey("1"));
    }
}