package controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.AlocacaoInvalidaException;
import models.Playlist;
import models.PlaylistIncompletaException;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.*;

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
    	return ok(create.render(Form.form(Playlist.class)));
    }
    
    public static Result addPrimPaisagem(String nome, String id){
    	try{
    		projeto.addMusicaPrimPaisagem(nome, id);
    	} catch (AlocacaoInvalidaException a){
    		return badRequest();
    	}
    	return ok();
    }
    
    public static Result addSegPaisagem(String nome, String id){
    	try {
			projeto.addMusicaSegPaisagem(nome, id);
		} catch (AlocacaoInvalidaException e) {
			return badRequest();
		}
    	return ok();
    }
 
	public static Result setTransicao(String nome, String id){
		try {
			projeto.setMusicaTransicao(nome, id);
		} catch (AlocacaoInvalidaException e) {
			return badRequest();
		}
		return ok();
	}

	public static Result criaPlaylist(){

    	Form<Playlist> playlistForm = Form.form(Playlist.class).bindFromRequest();
    	Playlist playlist = null;
    	
    	if (playlistForm.hasErrors()) {
			return badRequest(create.render(playlistForm));
		
    	} else {
    		
    		playlist = playlistForm.get();
    		playlist.setId(Playlist.find.all().size() + 1 + "");
    		System.out.println("playlist do form ok");
    		
    		MultipartFormData body = request().body().asMultipartFormData();
    		FilePart imagem = body.getFile("imagem");
    		
    		if (imagem == null) {
    			System.out.println("imagem é nula");
    			playlist.setImagem(IMAGEM_PADRAO);
    		} else {
    			System.out.println("imagem nao é nula");
    			File file = imagem.getFile();
    			try {
    				configuraImagem(file, playlist.getId());
    				playlist.setImagem(playlist.getId());
    			} catch (IOException e){
    				playlist.setImagem(IMAGEM_PADRAO);
    				flash("erro", "Um problema foi encontrado ao fazer o upload da imagem. Sua playlist foi salva com a imagem padrão.");
    			}
    		}
    		System.out.println("passou das coisas da imagem");
			projeto.configuraNovaPlaylist(playlist);
			
			try {
				projeto.salvarPlaylist();
			} catch (PlaylistIncompletaException e) {
				flash("error", "Um erro ocorreu ao tentar salvar sua playlist. Tente mais tarde");
				return redirect(routes.Application.novaPlaylist());
			}
		}
    	
    	return redirect(routes.Application.survey(playlist.getId()));
    }
    
    public static Result survey(String id){
    	return ok(survey.render(id));
    }
    
    public static Result respostas(){
    	
    	DynamicForm requestData = Form.form().bindFromRequest();
        String q1 = requestData.get("question1");
        String q2 = requestData.get("question2");
    	
    	System.out.println(q1);
    	
    	return redirect(routes.Application.survey("1"));
    }

    private static File configuraImagem(File file, String id) throws IOException{
    	BufferedImage img;
		BufferedImage resized = null;
		
		//creates an image from the uploaded file and resizes it
		img = ImageIO.read(file);
		resized = resizeImgTo(img, IMG_WIDTH, IMG_HEIGHT);
		
		//converts the image back to file
		File newImage = new File("public/img/playImgs", id + ".jpg");
		ImageIO.write(resized, "jpg", newImage);

		return newImage;
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