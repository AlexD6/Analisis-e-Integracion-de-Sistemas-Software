package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.lyricsOVH.LyricsSearch;

public class LyricsOVHResource {
	public static LyricsOVHResource _instance = null;
	private static final Logger log = Logger.getLogger(LyricsOVHResource.class.getName());
	public LyricsSearch getLyrics (String artista, String cancion) throws UnsupportedEncodingException{
	
//		Parámetro: lo que se introduce en el formulario de busqueda (artista y cancion)
//		Returns: resultado de la busqueda

		String uri = "https://api.lyrics.ovh/v1/" + URLEncoder.encode(artista, "UTF-8") + "/" + URLEncoder.encode(cancion, "UTF-8");
		log.log(Level.FINE, "lyricsOVH URI:" + uri);
		ClientResource cr = new ClientResource(uri);
		LyricsSearch lyricsSearch = cr.get(LyricsSearch.class);
		return lyricsSearch;
	}
}
