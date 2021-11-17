package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resources.SpotifyResource;


public class SpotifyAddController extends HttpServlet {
	
	 private static final long serialVersionUID = -6818025976353856770L;
	 private static final Logger log = Logger.getLogger(SpotifyAddController.class.getName());

	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		String trackId = req.getParameter("trackId");
		req.setAttribute("trackId", trackId);
		
		RequestDispatcher rd = null;
		
		String accessToken = (String) req.getSession().getAttribute("Spotify-token");
				
		
		
		
		if (accessToken != null && !"".equals(accessToken)) {
		
			
			SpotifyResource spResource = new SpotifyResource(accessToken);
			
			boolean success = spResource.addSongToLibrary(trackId);
			String trackName = spResource.getTrackInfo(trackId).getName();
			req.setAttribute("trackName", trackName);
			
			if(success) {
				req.setAttribute("message", "se añadió a \"Canciones que te gustan\".");
				log.log(Level.FINE, "Song with id=" + trackId + " added. Forwarding to library view.");
				
				rd = req.getRequestDispatcher("/libraryMessage.jsp");
				rd.forward(req, resp);
				
			} else {
				log.log(Level.SEVERE, "Spotify tracks: " + trackId);
    			rd = req.getRequestDispatcher("/error.jsp");
			}
			
		} else {
			log.info("Trying to access Spotify without an access token, redirecting to OAuth servlet");
			req.getRequestDispatcher("/AuthController/Spotify").forward(req, resp);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
