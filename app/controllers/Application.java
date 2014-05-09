package controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import models.Playlist;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
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
    	
    	MultipartFormData body = request().body().asMultipartFormData();
    	FilePart picture = body.getFile("picture");
   		String fileName = picture.getFilename();
   		String contentType = picture.getContentType(); 
   		File file = picture.getFile();
   		System.out.println(file.getAbsolutePath());
    	
    	return redirect(routes.Application.survey());
    }
    
    public static Result survey(){
    	return ok(survey.render());
    }
    
    public static Result salvarPlaylist(){
    	if (projeto.isTransicaoSet()){
    		projeto.salvarPlaylist();
    		return ok();
    	} else {
    		return badRequest();
    	}
    }
    
    public static Result teste(){
    	return ok(teste.render(Form.form(Playlist.class)));
    }
    
    public static Result upload(){
    	Form<Playlist> playlistForm = Form.form(Playlist.class).bindFromRequest();
    	Playlist play = new Playlist();
    	if (playlistForm.hasErrors()) {
			return badRequest(create.render(playlistForm));
    	} else {
    		play = playlistForm.get();
		}
    	
    	MultipartFormData body = request().body().asMultipartFormData();
    	FilePart imagem = body.getFile("imagem");
   		String fileName = imagem.getFilename();
   		String contentType = imagem.getContentType(); 
   		File file = imagem.getFile();
   		
   		System.out.println(file.length());
   		File playImg = new File("public/img/playImgs", fileName);
   		
//   		try {
//            FileUtils.moveFile(file, playImg);
//        } catch (IOException ioe) {
//        	ioe.printStackTrace();
//        }
   		
   		BufferedImage img;
   		BufferedImage resized = null;
		try {
			img = ImageIO.read(playImg);
			resized = resizeImgTo(img, 10, 10);
		} catch (IOException e) {
			e.printStackTrace();
			flash("erro", e.getMessage());
		}

		try {
			ImageIO.write(resized, FilenameUtils.removeExtension(fileName), playImg);
		} catch (IOException e) {
			e.printStackTrace();
			flash("erro", e.getMessage());
		}
    	
    	return redirect(routes.Application.teste());
    }
    
    private static BufferedImage resizeImgTo(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}