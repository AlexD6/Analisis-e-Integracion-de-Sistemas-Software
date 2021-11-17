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

import aiss.model.resources.SpotifyResource;
import aiss.model.spotify.albumsongs.AlbumSongs;
import aiss.model.spotify.info.album.AlbumInfo;
import aiss.model.spotify.info.album.Artist;
import aiss.model.spotify.info.album.Image;


public class AlbumPageController extends HttpServlet {
    
    private static final long serialVersionUID = -6818025976353856770L;
    private static final Logger log = Logger.getLogger(AlbumPageController.class.getName());
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumPageController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		String albumId = req.getParameter("albumId");
		
		RequestDispatcher rd = null;
		
		String accessToken = (String) req.getSession().getAttribute("Spotify-token");
	
		if (accessToken != null && !"".equals(accessToken)) {
		
			log.log(Level.FINE, "Searching for track info " + albumId);
			
			//Declaración de los resources
			SpotifyResource spResource = new SpotifyResource(accessToken);
            
            
            AlbumInfo album = spResource.getAlbumInfo(albumId);
            
            //albumName
            String albumName = album.getName();
            
            //albumSongs
            AlbumSongs albumSongs = spResource.getAlbumSongs(albumId);
            
//            albumImage
            List<Image> albumImages = album.getImages();
            String urlAlbumImages = albumImages.get(0).getUrl();
            
//            albumArtist
            List<Artist> albumArtist = album.getArtists();
            String idAlbumArtist = albumArtist.get(0).getId();
            
          //artistsNames
            String artistsNames = "";
            for(Artist a: album.getArtists()) {
            	if(!album.getArtists().get(album.getArtists().size()-1).equals(a)) {
            		artistsNames = artistsNames + a.getName() + ", ";
            	}else {
            		artistsNames = artistsNames + a.getName();
            	}
            }
          		
//            albumReleaseDate
            String albumReleaseDate = album.getReleaseDate();
            
            
			if (albumName!=null){
				rd = req.getRequestDispatcher("/album.jsp");
				req.setAttribute("albumName", albumName);
				req.setAttribute("albumSongs", albumSongs);
				req.setAttribute("artistsNames", artistsNames);
				req.setAttribute("urlAlbumImages", urlAlbumImages);
				req.setAttribute("idAlbumArtist", idAlbumArtist);
				req.setAttribute("albumReleaseDate", albumReleaseDate);
				
			} else {
				log.log(Level.SEVERE, "albumName: " + albumName);
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