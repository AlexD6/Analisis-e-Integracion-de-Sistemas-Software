package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.lastfm.SimilarArtistsSearch;

public class LastFMResource {

	private static final String LASTFM_API_KEY = "cf5bba46ec4af56e578975cac16d4401";
	private static final Logger log = Logger.getLogger(LastFMResource.class.getName());
	
	public SimilarArtistsSearch getSimilarArtists(String query) throws UnsupportedEncodingException{
		String uri = "http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=" + URLEncoder.encode(query, "UTF-8") + "&api_key=" + LASTFM_API_KEY + "&format=json&limit=5";
		log.log(Level.FINE, "LastFM URI: " + uri);
		ClientResource cr = new ClientResource(uri);
		SimilarArtistsSearch lastFMSearch = cr.get(SimilarArtistsSearch.class);
		return lastFMSearch;
	}
}
