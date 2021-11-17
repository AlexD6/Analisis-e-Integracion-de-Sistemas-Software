 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>

<head>
	<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
	<link rel="stylesheet" type="text/css" href="css/styleSheet.css" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>Resultados :')</title>
</head>

<body>
	<div class = "resultadosImagen">
		<a title="Vuelta a inicio" href="/mainPage">
		<img src="images/resultadosBusqueda.png" alt="resultados" width="1035"
			height="75" /> </a>
	</div>
	<div class="tracks">
		<div class="buscador">
			<p class="resultados">
				Canciones:
				<c:out value="${param.spotifySearchQuery}" />
			</p>
		</div>
		<div class="artistAlbum">
				<br />
		
			<c:forEach items="${requestScope.spotifyTracks}" var="track">
				<span> <a class="resultados"
					href="TrackPageController?trackId=${track.id}"> <c:out
							value="${track.name}" /><br /> <c:forEach
							items="${track.artists}" var="artist" varStatus="loop">
							<span> <c:out
									value="${artist.name}${loop.last ? '' : ','}" />
							</span>
						</c:forEach>
				</a><br />
				</span>
				<br />
			</c:forEach>
		</div>
	</div>

	<div class="artists">
		<div class="lyricArriba">
			<p class="lyricArriba">
				Artistas:
				<c:out value="${param.spotifySearchQuery}" />
			</p>
		</div>

		<div class="artistAlbum">
				<br />
		
			<c:forEach items="${requestScope.spotifyArtists}" var="artist">
				<span> <a class="resultados"
					href="ArtistPageController?artistId=${artist.id}"> <c:out
							value="${artist.name}" />
				</a> <br />
				</span>
				<br />
			</c:forEach>
		</div>
	</div>


	<div class="albums">
		<div class="buscador">
			<p class="resultados">
				Albumes:
				<c:out value="${param.spotifySearchQuery}" />
			</p>
		</div>
		<div class="artistAlbum">
				<br />
		
			<c:forEach items="${requestScope.spotifyAlbums}" var="album">
				<span> <a class="resultados"
					href="AlbumPageController?albumId=${album.id}"> <c:out
							value="${album.name}" /><br /> <c:forEach
							items="${album.artists}" var="artist" varStatus="loop">
							<span> <c:out
									value="${artist.name}${loop.last ? '' : ','}" />
							</span>
						</c:forEach>
				</a><br />
				</span>
				<br />
			</c:forEach>
		</div>
	</div>
</body>
</html>