package controllers;

import models.Playlist;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	private static Projeto projeto = new Projeto();
	
    public static Result index() {
        return ok(views.html.index.render(projeto.getSamplePlaylists()));
    }
    
    public static Result novaPlaylist() {
		return ok(views.html.create.render());
	}

    public static Result getPlaylist(int id) {
    	Playlist playlist = projeto.getPlaylist(id);
        return ok(Json.toJson(playlist));
    }
    
    public static Result searchVideo(String keyword){
    
    	/*
			params = urllib.urlencode({'q': vote.encode('utf-8'), 'max-results': '1', 'v': '2', 'alt': 'jsonc'})
	        
	        url = "http://gdata.youtube.com/feeds/api/videos?%s" % params
	        result = simplejson.load(urllib.urlopen(url))
	        item = result['data']['items'][0]
	        
	        video_json = simplejson.dumps({"id": item['id'], "title": item['title']})
	        
	        radio_utils.append(radio_utils.get_path(RADIO_ROOT, 'to_process_votes'), 
	                           video_json)
    	    
    	    return simplejson.dumps(current_status)
    	*/
    	
    	return ok();
    }
}