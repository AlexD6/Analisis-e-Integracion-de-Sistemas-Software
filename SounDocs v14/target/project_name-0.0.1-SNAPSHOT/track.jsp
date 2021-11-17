<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>
<head>
	<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
	<link rel="stylesheet" type="text/css" href="css/styleSheet.css" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title><c:out value="${requestScope.trackName}" /></title>
</head>
<body>
	<div class="tituloCancion">
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV izq.png" alt="resultados" width="75"
			height="100" />
		</a>
		<p class="tituloCancion">
			<c:out value="${requestScope.trackName}" />
		</p>
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV der.png" alt="resultados" width="75"
			height="100" />
		</a>
	</div>
	<div class="datosGeneral">
		<div class="tituloDatos">
			<p class="tituloDatos">Datos:</p>
		</div>
		<div class="datosAbajo">
			<p class="datosAbajo">
				Artista:
				<a class="resultados"
				href="ArtistPageController?artistId=${requestScope.idTrackArtist}">
				<c:out value="${requestScope.artistsNames}" /><c:out value="${artist.name}"/></a>
			</p>
			<p class="datosAbajo">
				Álbum:
				<a class="resultados"
				href="AlbumPageController?albumId=${requestScope.trackAlbum}">
				<c:out value="${requestScope.albumName} ${requestScope.albumType}" /><c:out value="${album.name}"/></a>
			</p>
			<div class="albumCentrado">
				<img src="${requestScope.trackAlbumImageURL}"
					alt="${requestScope.albumName}" width="230px" height="230px">
			</div>
		</div>
	</div>
	<div class="videoclip">
		<iframe width="600" height="336" src="${requestScope.videoURL}"
			allowfullscreen="true" frameborder="0"></iframe>
			<div class="Like">
			<a title="Añade esta canción a me gusta!" href="SpotifyAddController?trackId=${requestScope.trackId}"><img
				src="images/MeGusta3.png" alt="portada" width="160" height="100" /></a>
	</div>
	</div>



	<div class="lyricGeneral">
		<div class="lyricArriba">
			<p class="lyricArriba">
				Letra:
			</p>
		</div>
		<div  class="lyricAbajo">
			<pre><p class="lyricAbajo"><c:out value="${requestScope.lyrics}"/><br><br> [Fin] <br> </p></pre>
		</div>
	</div>

	
</body>
</html>