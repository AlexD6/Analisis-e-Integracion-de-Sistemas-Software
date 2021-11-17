package aiss.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.lastfm.SimilarArtistsSearch;
import aiss.model.resources.LastFMResource;
import aiss.model.resources.SpotifyResource;
import aiss.model.spotify.artistalbums.ArtistAlbums;
import aiss.model.spotify.artistsongs.ArtistSongs;
import aiss.model.spotify.info.artist.ArtistInfo;
import aiss.model.spotify.info.artist.Image;

public class ArtistPageController extends HttpServlet {
    
    private static final long serialVersionUID = -6818025976353856770L;
    private static final Logger log = Logger.getLogger(ArtistPageController.class.getName());
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistPageController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
    	
		String artistId = req.getParameter("artistId");
		
		RequestDispatcher rd = null;
		
		String accessToken = (String) req.getSession().getAttribute("Spotify-token");
	
		if (accessToken != null && !"".equals(accessToken)) {
		
			log.log(Level.FINE, "Searching for artist info " + artistId);
			
			//Declaración de los resources
			SpotifyResource spResource = new SpotifyResource(accessToken);
            LastFMResource lastFMResource = new LastFMResource();
            
            ArtistInfo artist = spResource.getArtistInfo(artistId);
            
            
            //artistName
            String artistName = artist.getName();
            
            //artistAlbums
            ArtistAlbums artistAlbums = spResource.getArtistAlbums(artistId);
            
//          artistImages
            List<Image> artistImages = artist.getImages();
            String urlArtistImages = artistImages.get(0).getUrl();
              
//          artistSongs
            ArtistSongs artistSongs = spResource.getArtistSongs(artistId);
            
//          SimilarArtists
            SimilarArtistsSearch similarArtists = lastFMResource.getSimilarArtists(artistName);
            
//          SimilarArtistsId
            String as0 = similarArtists.getSimilarartists().getArtist().get(0).getName();
            String as1 = similarArtists.getSimilarartists().getArtist().get(1).getName();
            String as2 = similarArtists.getSimilarartists().getArtist().get(2).getName();
            String as3 = similarArtists.getSimilarartists().getArtist().get(3).getName();
            String as4 = similarArtists.getSimilarartists().getArtist().get(4).getName();
            
            String idas0 = null;
            if (spResource.getArtistsSearchResults(as0).getArtists().getItems().isEmpty()) {
            	idas0 = null;
            } else {
            	idas0 = spResource.getArtistsSearchResults(as0).getArtists().getItems().get(0).getId();
            }
            
            String idas1 = null;
            if (spResource.getArtistsSearchResults(as1).getArtists().getItems().isEmpty()) {
            	idas1 = null;
            } else {
            	idas1 = spResource.getArtistsSearchResults(as1).getArtists().getItems().get(0).getId();
            }
            
            String idas2 = null;
            if (spResource.getArtistsSearchResults(as2).getArtists().getItems().isEmpty()) {
            	idas2 = null;
            } else {
            	idas2 = spResource.getArtistsSearchResults(as2).getArtists().getItems().get(0).getId();
            }
            
            String idas3 = null;
            if (spResource.getArtistsSearchResults(as3).getArtists().getItems().isEmpty()) {
            	idas3 = null;
            } else {
            	idas3 = spResource.getArtistsSearchResults(as3).getArtists().getItems().get(0).getId();
            }
            
            String idas4 = null;
            if (spResource.getArtistsSearchResults(as4).getArtists().getItems().isEmpty()) {
            	idas4 = null;
            } else {
            	idas4 = spResource.getArtistsSearchResults(as4).getArtists().getItems().get(0).getId();
            }
            
           	if (artistName!=null){
				rd = req.getRequestDispatcher("/artist.jsp");
				req.setAttribute("artistName", artistName);
				req.setAttribute("artistAlbums", artistAlbums);
				req.setAttribute("urlArtistImages", urlArtistImages);
				req.setAttribute("artistSongs", artistSongs);
				req.setAttribute("similarArtists", similarArtists);
				req.setAttribute("as0", as0);
				req.setAttribute("as1", as1);
				req.setAttribute("as2", as2);
				req.setAttribute("as3", as3);
				req.setAttribute("as4", as4);
				req.setAttribute("idas0", idas0);
				req.setAttribute("idas1", idas1);
				req.setAttribute("idas2", idas2);
				req.setAttribute("idas3", idas3);
				req.setAttribute("idas4", idas4);
				
			} else {
				log.log(Level.SEVERE, "artistName: " + artistName);
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