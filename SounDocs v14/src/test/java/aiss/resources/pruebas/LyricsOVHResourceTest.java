package aiss.resources.pruebas;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.Test;

import aiss.model.lyricsOVH.LyricsSearch;
import aiss.model.resources.LyricsOVHResource;

public class LyricsOVHResourceTest {
	
	LyricsOVHResource lyricsOVH = new LyricsOVHResource();
	
	@Test
	public void testGetLyrics() throws UnsupportedEncodingException{
		String artista = "Linkin Park";
		String cancion = "Lost in the Echo";
		System.out.println("Test de LyricsOVH: Probando búsqueda de " + cancion + " (de " + artista + ")");
		LyricsSearch res = lyricsOVH.getLyrics(artista, cancion);
		
		if (res.getLyrics().isEmpty()) {
			res = null;
		} if (res != null) {
			System.out.println("Encontrado correctamente");
		}
		
		assertNotNull("La lista de letras no puede ser null", res);
	}

}
