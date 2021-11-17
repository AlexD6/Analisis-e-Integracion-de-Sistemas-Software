<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>
<head>
	<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
 	<link rel="stylesheet" type="text/css" href="css/styleSheet.css" /> 
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title><c:out value="${requestScope.albumName}" /></title>
</head>
<body>

	<div class="tituloCancion">
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV izq.png" alt="resultados" width="75"
			height="100" />
		</a>
		<p class="tituloCancion">
			<c:out value="${requestScope.albumName}" />
		</p>
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV der.png" alt="resultados" width="75"
			height="100" />
		</a>
	</div>

	<div class="tracks">
		<div class="buscador">
			<p class="resultados">Canciones:</p>
		</div>
						
		<div class="artistAlbum">
		<br />
			<c:forEach items="${requestScope.albumSongs.items}" var="song">
				<span> <a class="resultados"
					href="TrackPageController?trackId=${song.id}"> <c:out
							value="${song.name}" />
				</a>
				</span>
				<br />
				<br />
			</c:forEach>
		</div>
	</div>

<div class="albumPhoto">
		<img src="${requestScope.urlAlbumImages}"
					alt="${requestScope.albumName}" width="400px" height="400px">
	</div>
	
	<div class="albumData">
		<div class="lyricArriba">
			<p class="lyricArriba">Datos:</p>
		</div>
		<div class="resultados">
			<p class="datosAbajo">
				Artista: <a class="resultados"
					href="ArtistPageController?artistId=${requestScope.idAlbumArtist}">
					<c:out value="${requestScope.artistsNames}" /> <c:out
						value="${artist.name}" />
				</a> <br /> <br /> Fecha de salida:
				<c:out value="${requestScope.albumReleaseDate}" />
			</p>
			
		</div>
	</div>
	
</body>
</html>