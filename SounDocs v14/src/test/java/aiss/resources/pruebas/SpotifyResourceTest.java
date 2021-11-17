package aiss.resources.pruebas;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.Test;
import aiss.model.resources.SpotifyResource;
import aiss.model.spotify.searchalbum.AlbumSearch;
import aiss.model.spotify.searchartist.ArtistSearch;
import aiss.model.spotify.searchtrack.TrackSearch;

public class SpotifyResourceTest {
	
//	Asegurar que estan activos los scopes de user-library-read y user-library-modify en spotify dashboard
	private String access_token = "BQARsJZzVbGoKiXMhujBHswqKYO65hkKXtpLeCrwsAJgoGODhe83k7sONj9cjgLx-LhKzNPFaam7HHU0Eb_5wSoSgrfHj4PYWyPNN5Tr64ttTPF9GYPft01eaRXhy1hXxXE34jrQdF9fYHkLv3B43p4puDwqYt_WiRaU";
	SpotifyResource spotify = new SpotifyResource(access_token);
	
	@Test
	public void testGetTrackSearchResults() throws UnsupportedEncodingException {
		String busqueda = "Lost in the Echo";
		System.out.println("Test de Spotify - Canciones: Probando búsqueda de " + busqueda);
		TrackSearch res = spotify.getTrackSearchResults(busqueda);
		
		if (res.getTracks().getItems().isEmpty()) {
			res = null;
		} if (res != null) {
			System.out.println("Encontrado correctamente");
		}
		assertNotNull("La lista de canciones no puede ser null", res);
	}

	@Test
	public void testGetArtistsSearchResults() throws UnsupportedEncodingException {
		String busqueda = "Linkin Park";
		System.out.println("Test de Spotify - Artista: Probando búsqueda de " + busqueda);
		ArtistSearch res = spotify.getArtistsSearchResults(busqueda);
		
		if (res.getArtists().getItems().isEmpty()) {
			res = null;
		} if (res != null) {
			System.out.println("Encontrado correctamente");
		}
		assertNotNull("La lista de artistas no puede ser null", res);
	}

	@Test
	public void testGetAlbumsSearchResults() throws UnsupportedEncodingException {
		String busqueda = "Living Things";
		System.out.println("Test de Spotify - Albums: Probando búsqueda de " + busqueda);
		AlbumSearch res = spotify.getAlbumsSearchResults(busqueda);
		
		if (res.getAlbums().getItems().isEmpty()) {
			res = null;
		} if (res != null) {
			System.out.println("Encontrado correctamente");
		}
		assertNotNull("La lista de albums no puede ser null", res);
	}

	@Test
	public void testAddTrack() throws UnsupportedEncodingException {
		String trackId = "60a0Rd6pjrkxjPbaKzXjfq";
		System.out.println("Test de Spotify: Añadir canciones a me gusta");
		Boolean res = spotify.addSongToLibrary(trackId);
		if (res == true) {
			System.out.println("Operacion realizada correctamente");
		}

		assertTrue("La canción no se añadió correctamente", res);
	}

	@Test
	public void testDeleteTrack() throws UnsupportedEncodingException {
		String trackId = "60a0Rd6pjrkxjPbaKzXjfq";
		System.out.println("Test de Spotify: Eliminar canciones de me gusta");
		Boolean res = spotify.removeSongFromLibrary(trackId);
		if (res == true) {
			System.out.println("Operacion realizada correctamente");
		}

		assertTrue("La canción no se añadió correctamente", res);
	}
	
}
