package aiss.controller;

import aiss.model.resources.SpotifyResource;
import aiss.model.spotify.searchalbum.AlbumSearch;
import aiss.model.spotify.searchartist.ArtistSearch;
import aiss.model.spotify.searchtrack.TrackSearch;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpotifySearchController extends HttpServlet {

    private static final long serialVersionUID = -6818025976353856770L;
    private static final Logger log = Logger.getLogger(SpotifySearchController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    	String query = req.getParameter("spotifySearchQuery");
		RequestDispatcher rd = null;
    	
    	String accessToken = (String) req.getSession().getAttribute("Spotify-token");

        if (accessToken != null && !"".equals(accessToken)) {

            SpotifyResource spResource = new SpotifyResource(accessToken);
            TrackSearch tracks = spResource.getTrackSearchResults(query);
            ArtistSearch artists = spResource.getArtistsSearchResults(query);
            AlbumSearch albums = spResource.getAlbumsSearchResults(query);
            
        
            if (!tracks.getTracks().getItems().isEmpty() || !artists.getArtists().getItems().isEmpty() || !albums.getAlbums().getItems().isEmpty()){
    			rd = req.getRequestDispatcher("/success.jsp");
    			req.setAttribute("spotifyTracks", tracks.getTracks().getItems());
    			req.setAttribute("spotifyArtists", artists.getArtists().getItems());
    			req.setAttribute("spotifyAlbums", albums.getAlbums().getItems());	
    			
    			
    			
    			
    		} else {
    			log.log(Level.SEVERE, "Spotify tracks: " + tracks + ". Spotify artists: " + artists + ". Spotify albums: " + albums);
    			rd = req.getRequestDispatcher("/error.jsp");
    		}
    		rd.forward(req, resp);
        } else {
            log.info("Trying to access Spotify without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/Spotify").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }

}
