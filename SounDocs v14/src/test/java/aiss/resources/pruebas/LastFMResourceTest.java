package aiss.resources.pruebas;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.Test;

import aiss.model.lastfm.SimilarArtistsSearch;
import aiss.model.resources.LastFMResource;

public class LastFMResourceTest {

	LastFMResource lastFM  = new LastFMResource();
	
	@Test
	public void test() throws UnsupportedEncodingException {
		String busqueda = "Linkin Park";
		System.out.println("Test de LastFM: Probando búsqueda de artistas similares a " + busqueda);
		SimilarArtistsSearch res = lastFM.getSimilarArtists(busqueda);
		
//	En caso de no haber resultados
		if (res.getSimilarartists().getArtist().isEmpty()) {
			res = null;
		} if (res != null) {
			System.out.println("Encontrado correctamente");
		}
		
		assertNotNull("La lista de artistas similares no puede ser null", res);
	}

}