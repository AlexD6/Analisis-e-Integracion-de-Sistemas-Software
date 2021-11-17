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
import aiss.model.spotify.userssavedtracks.UsersSavedTracks;


public class SpotifyLibraryController extends HttpServlet {
	
	 private static final long serialVersionUID = -6818025976353856770L;
	 private static final Logger log = Logger.getLogger(SpotifyLibraryController.class.getName());

	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		String trackId = req.getParameter("trackId");
		req.setAttribute("trackId", trackId);
		
		RequestDispatcher rd = null;
		
		String accessToken = (String) req.getSession().getAttribute("Spotify-token");
				
		
		
		
		if (accessToken != null && !"".equals(accessToken)) {
			// Añadir track a favoritas
			
			SpotifyResource spResource = new SpotifyResource(accessToken);
			
			UsersSavedTracks usersSavedTracks = spResource.getUsersSavedTracks();
			String total = usersSavedTracks.getTotal().toString();
			
			req.setAttribute("usersSavedTracks", usersSavedTracks);
			req.setAttribute("total", total);
			
			if(!usersSavedTracks.equals(null)) {
				
				rd = req.getRequestDispatcher("/library.jsp");
				
				rd.forward(req, resp);
			} else {
				log.log(Level.SEVERE, "Spotify tracks: " + usersSavedTracks);
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
