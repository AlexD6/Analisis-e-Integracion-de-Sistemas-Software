package aiss.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resources.LyricsOVHResource;
import aiss.model.resources.SpotifyResource;
import aiss.model.resources.YoutubeResource;
import aiss.model.spotify.info.track.Artist;
import aiss.model.spotify.info.track.TrackInfo;
import aiss.model.spotify.searchalbum.AlbumSearch;

public class TrackPageController extends HttpServlet {
    
    private static final long serialVersionUID = -6818025976353856770L;
    private static final Logger log = Logger.getLogger(TrackPageController.class.getName());
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrackPageController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
    	
		String trackId = req.getParameter("trackId");
		
		RequestDispatcher rd = null;
		
		String accessToken = (String) req.getSession().getAttribute("Spotify-token");
	
		if (accessToken != null && !"".equals(accessToken)) {
		
			log.log(Level.FINE, "Searching for track info " + trackId);
			
			//Declaración de los resources
			SpotifyResource spResource = new SpotifyResource(accessToken);
            YoutubeResource ytResource = new YoutubeResource();
            LyricsOVHResource lyResource = new LyricsOVHResource();
            
            
            TrackInfo track = spResource.getTrackInfo(trackId);
            
            
            //trackName
            String trackName = track.getName();
            
            //artistsNames
            String artistsNames = "";
            for(Artist a: track.getArtists()) {
            	if(!track.getArtists().get(track.getArtists().size()-1).equals(a)) {
            		artistsNames = artistsNames + a.getName() + ", ";
            	}else {
            		artistsNames = artistsNames + a.getName();
            	}
            }
            
            //albumName
            String albumName = track.getAlbum().getName();
            
            //albumType
            String albumType_ = track.getAlbum().getAlbumType();
            String albumType = "";
            if(albumType_.equals("single")) {
            	albumType = "(Single)";
            }else {
            	albumType = "";
            }
           
            //trackAlbumImageURL
            List<String> artistsList = new LinkedList<>();
            for(Artist a: track.getArtists()) {
            	artistsList.add(a.getName());
            }
            String artistsNamesSpaces = "";
            for(String a: artistsList) {
            	if(!artistsList.get(artistsList.size()-1).equals(a)) {
            		artistsNamesSpaces = artistsNamesSpaces + a + " ";
            	}else {
            		artistsNamesSpaces = artistsNamesSpaces + a;
            	}
            }
            
            String albumQuery = track.getAlbum().getName() + " " + artistsNamesSpaces; //Hybrid Theory Linkin Park
            log.log(Level.FINE, "Searching for album image URL " + albumQuery);
            AlbumSearch albumsearch = spResource.getAlbumsSearchResults(albumQuery);
            String trackAlbumImageURL = albumsearch.getAlbums().getItems().get(0).getImages().get(0).getUrl();
            
            
            
            //videoURL
            String youtubeQuery = track.getName() + artistsNamesSpaces + "video"; //Crawling Linkin Park video
            String videoURL = ytResource.getUrlMV(youtubeQuery);
            	//Intentar hacer 5 busquedas con videoId como parámetro para obtener el resultado correcto
            
            
            //lyrics
            String lyrics = lyResource.getLyrics(artistsNamesSpaces, trackName).getLyrics();
            
//            trackArtist
            List<Artist> trackArtist = track.getArtists();
            String idTrackArtist = trackArtist.get(0).getId();
            
//          trackArtist
            String trackAlbum = track.getAlbum().getId();
            
            //elementos obtenidos: trackName, artistsNames, albumName, videoURL, trackAlbumImageURL, albumType,
         
            
            				
			if (trackName!=null){
				rd = req.getRequestDispatcher("/track.jsp");	
				req.setAttribute("trackName", trackName);
				req.setAttribute("artistsNamesSpaces", artistsNamesSpaces);
				req.setAttribute("albumName", albumName);
				req.setAttribute("videoURL", videoURL);
				req.setAttribute("trackAlbumImageURL", trackAlbumImageURL);
				req.setAttribute("albumType", albumType);
				req.setAttribute("artistsNames", artistsNames);
				req.setAttribute("albumQuery", albumQuery);
				req.setAttribute("lyrics", lyrics);
				req.setAttribute("idTrackArtist", idTrackArtist);
				req.setAttribute("trackAlbum", trackAlbum);
				req.setAttribute("trackId", trackId);
				
			} else {
				log.log(Level.SEVERE, "trackName: " + trackName);
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
