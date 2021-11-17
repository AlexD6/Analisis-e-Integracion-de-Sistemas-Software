package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


import aiss.model.spotify.albumsongs.AlbumSongs;
import aiss.model.spotify.artistalbums.ArtistAlbums;
import aiss.model.spotify.artistsongs.ArtistSongs;
import aiss.model.spotify.checkuserssavedtracks.CheckUsersSavedTracks;
import aiss.model.spotify.info.album.AlbumInfo;
import aiss.model.spotify.info.artist.ArtistInfo;
import aiss.model.spotify.info.track.TrackInfo;
//import aiss.model.spotify.Playlists;
import aiss.model.spotify.searchalbum.AlbumSearch;
import aiss.model.spotify.searchartist.ArtistSearch;
import aiss.model.spotify.searchtrack.TrackSearch;
import aiss.model.spotify.userssavedtracks.UsersSavedTracks;

public class SpotifyResource {

    private static final Logger log = Logger.getLogger(SpotifyResource.class.getName());
    
    private final String access_token;
    private final String baseURL = "https://api.spotify.com/v1";

    public SpotifyResource(String access_token) {
        this.access_token = access_token;
    }

    
    public String getTrackAlbumImageURL(String query) throws UnsupportedEncodingException {
    	return getAlbumsSearchResults(query).getAlbums().getItems().get(0).getImages().get(0).getUrl();
    }
    
    public String getTrackAlbumType(String query) throws UnsupportedEncodingException {
    	return getAlbumsSearchResults(query).getAlbums().getItems().get(0).getAlbumType();
    }
    
    public UsersSavedTracks getUsersSavedTracks() {
    	//GET https://api.spotify.com/v1/me/tracks
    	String usersSavedTracksURL = baseURL + "/me/tracks?limit=50";

    	ClientResource cr = new ClientResource(usersSavedTracksURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        UsersSavedTracks usersSavedTracks = null;
        
        try {
        	usersSavedTracks = cr.get(UsersSavedTracks.class);
            return usersSavedTracks;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify user's tracks: " + cr.getResponse().getStatus());
            log.warning(usersSavedTracksURL);
            return null;
        }
    }
    
    public boolean checkSong(String trackId) {
    	//GET https://api.spotify.com/v1/me/tracks/contains?ids=0udZHhCi7p1YzMlvI4fXoK
    	String checkSongURL = baseURL + "/me/tracks/contains?ids=" + trackId;

    	ClientResource cr = new ClientResource(checkSongURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        //UsersSavedTracks usersSavedTracks = null;
        Boolean[] spotifyCheckList;
        boolean success = true;
        try {
        	spotifyCheckList = cr.get(Boolean[].class);
            success = spotifyCheckList[0];

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify checkings: " + cr.getResponse().getStatus());
            log.warning(checkSongURL);
            success = false;
        }
		return success;
    }
    
    
    public boolean addSongToLibrary(String trackId) {
		ClientResource cr = new ClientResource(baseURL + "/me/tracks?ids=" + trackId);
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);        
		chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);
		boolean success = true;
		try {
			cr.setEntityBuffering(true);	
			cr.put(trackId);
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the song: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
	}
    
    public boolean removeSongFromLibrary(String trackId) {
		ClientResource cr = new ClientResource(baseURL + "/me/tracks?ids=" + trackId);
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);        
		chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);
		boolean success = true;
		try {
			cr.setEntityBuffering(true);		
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the song: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
	}
    

    
    
    public ArtistAlbums getArtistAlbums(String artistId) {
    	//https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?market=ES
    	String artistAlbumsURL = baseURL + "/artists/" + artistId + "/albums?market=ES";

    	ClientResource cr = new ClientResource(artistAlbumsURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        ArtistAlbums artistAlbums = null;
        
        try {
        	artistAlbums = cr.get(ArtistAlbums.class);
            return artistAlbums;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify artistAlbums: " + cr.getResponse().getStatus());
            log.warning(artistAlbumsURL);
            return null;
        }
    }
    
    public ArtistSongs getArtistSongs(String artistId) {
    	//https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?market=ES
    	String artistSongsURL = baseURL + "/artists/" + artistId + "/top-tracks?country=ES";

    	ClientResource cr = new ClientResource(artistSongsURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        ArtistSongs artistSongs = null;
        
        try {
        	artistSongs = cr.get(ArtistSongs.class);
            return artistSongs;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify artistSongs: " + cr.getResponse().getStatus());
            log.warning(artistSongsURL);
            return null;
        }
    }
    
    public AlbumSongs getAlbumSongs(String albumId) {
		//https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK/tracks?market=ES
    	String albumSongsURL = baseURL + "/albums/" + albumId + "/tracks?market=ES";

    	ClientResource cr = new ClientResource(albumSongsURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        AlbumSongs albumSongs = null;
        
        try {
        	albumSongs = cr.get(AlbumSongs.class);
            return albumSongs;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify albumSongs: " + cr.getResponse().getStatus());
            log.warning(albumSongsURL);
            return null;
        }
	}
    public TrackInfo getTrackInfo(String trackId) throws UnsupportedEncodingException{
    	
    	String trackInfoURL = baseURL + "/tracks/" + trackId + "?market=ES";

    	ClientResource cr = new ClientResource(trackInfoURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        TrackInfo track = null;
        
        try {
            track = cr.get(TrackInfo.class);
            return track;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify trackInfo: " + cr.getResponse().getStatus());
            log.warning(trackInfoURL);
            return null;
        }
    	
    }
    
    public ArtistInfo getArtistInfo(String artistId) throws UnsupportedEncodingException{
    	
    	String artistInfoURL = baseURL + "/artists/" + artistId + "?market=ES";

    	ClientResource cr = new ClientResource(artistInfoURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        ArtistInfo artist = null;
        
        try {
            artist = cr.get(ArtistInfo.class);
            return artist;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify artistInfo: " + cr.getResponse().getStatus());
            log.warning(artistInfoURL);
            return null;
        }
    	
    }
    
    public AlbumInfo getAlbumInfo(String albumId) throws UnsupportedEncodingException{
    	
    	String albumInfoURL = baseURL + "/albums/" + albumId + "?market=ES";

    	ClientResource cr = new ClientResource(albumInfoURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        AlbumInfo album = null;
        
        try {
        	album = cr.get(AlbumInfo.class);
            return album;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify albumInfo: " + cr.getResponse().getStatus());
            log.warning(albumInfoURL);
            return null;
        }
    	
    }
    
    
    
    
    
    
    
    
    
    

    public TrackSearch getTrackSearchResults(String query) throws UnsupportedEncodingException{
    	
    	String trackGetterURL = baseURL + "/search?q=" + URLEncoder.encode(query, "UTF-8") + "&type=track&market=ES";

    	ClientResource cr = new ClientResource(trackGetterURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        TrackSearch tracks = null;
        try {
            tracks = cr.get(TrackSearch.class);
            return tracks;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify tracks: " + cr.getResponse().getStatus());
            log.warning(trackGetterURL);
            return null;
        }
    }
    
    public ArtistSearch getArtistsSearchResults(String query) throws UnsupportedEncodingException {
    	
    	String artistGetterURL = baseURL + "/search?q=" + URLEncoder.encode(query, "UTF-8") + "&type=artist&market=ES";

    	ClientResource cr = new ClientResource(artistGetterURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        ArtistSearch artists = null;
        try {
        	artists = cr.get(ArtistSearch.class);
            return artists;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify artists: " + cr.getResponse().getStatus());
            log.warning(artistGetterURL);
            return null;
        }
    }
    
    public AlbumSearch getAlbumsSearchResults(String query) throws UnsupportedEncodingException{
    	
    	String albumGetterURL = baseURL + "/search?q=" + URLEncoder.encode(query, "UTF-8") + "&type=album&market=ES";

    	ClientResource cr = new ClientResource(albumGetterURL);
        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(access_token);
        cr.setChallengeResponse(chr);

        AlbumSearch albums = null;
        try {
        	albums = cr.get(AlbumSearch.class);
            return albums;

        } catch (ResourceException re) {
            log.warning("Error when retrieving Spotify albums: " + cr.getResponse().getStatus());
            log.warning(albumGetterURL);
            return null;
        }
    }

//    public String getTrackAlbumImageURL(String query) throws UnsupportedEncodingException {
//    	return getAlbumsSearchResults(query).getAlbums().getItems().get(0).getImages().get(0).getUrl();
//    }
    
    public String getArtistId(String query) throws UnsupportedEncodingException{
    	return getArtistsSearchResults(query).getArtists().getItems().get(0).getId();
    }







	
    
    
    
    
    
    
    
    
    
   
}

