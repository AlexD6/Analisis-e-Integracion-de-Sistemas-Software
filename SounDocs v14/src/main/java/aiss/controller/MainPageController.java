package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageController extends HttpServlet {

    private static final long serialVersionUID = -6818025976353856770L;
    private static final Logger log = Logger.getLogger(MainPageController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String accessToken = (String) req.getSession().getAttribute("Spotify-token");

        if (accessToken != null && !"".equals(accessToken)) {

        	req.getRequestDispatcher("/main.html").forward(req, resp);
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