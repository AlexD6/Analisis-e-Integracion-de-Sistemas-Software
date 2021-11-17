package aiss.resources.pruebas;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.Test;

import aiss.model.resources.YoutubeResource;
import aiss.model.youtube.VideoSearch;

public class YoutubeResourceTest {

	YoutubeResource youtube = new YoutubeResource();
	 
	@Test
	public void testGetVideos() throws UnsupportedEncodingException{
		String busqueda = "Lost in the Echo";
		System.out.println("Test de Youtube: Probando búsqueda de " + busqueda);
		VideoSearch res = youtube.getVideos(busqueda);
		
//	En caso de no haber resultados
		if (res.getItems().isEmpty()) {
			res = null;
		} if (res != null) {
			System.out.println("Encontrado correctamente");
		}
		
		assertNotNull("La lista de videos no puede ser null", res);
	}

}
