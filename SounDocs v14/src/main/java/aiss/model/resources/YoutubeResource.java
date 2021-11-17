package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.youtube.Item;
import aiss.model.youtube.VideoSearch;

public class YoutubeResource {

	private static final String YOUTUBE_API_KEY = "AIzaSyC5Zv8PH7vLPQDxzlAUPX4eQNLhJ4CJB3I";
	private static final Logger log = Logger.getLogger(YoutubeResource.class.getName());
	

	
	public VideoSearch getVideos(String query) throws UnsupportedEncodingException {
		
		/*Parámetro: lo que se introduce en el formulario de busqueda (query)
		 * Returns: resultado de la busqueda
		 */

		String uri = "https://www.googleapis.com/youtube/v3/search?part=id&q=" + URLEncoder.encode(query, "UTF-8") + "&key=" + YOUTUBE_API_KEY;
		
		log.log(Level.FINE, "Youtube URI: " + uri);
		
		ClientResource cr = new ClientResource(uri);
		VideoSearch youtubeSearch = cr.get(VideoSearch.class);
		
		return youtubeSearch;
	}
	
	public String getUrlMV(String query) {
		VideoSearch busqueda = null;
		try {
			busqueda = getVideos(query);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Item> resultadosDeBusqueda = busqueda.getItems();
		String idPrimerVideo = resultadosDeBusqueda.get(0).getId().getVideoId();
		String url = "https://www.youtube.com/embed/" + idPrimerVideo;
		return url;
	}
	
}
