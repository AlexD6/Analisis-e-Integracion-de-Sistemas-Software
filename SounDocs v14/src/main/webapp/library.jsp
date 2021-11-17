 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>

<head>
	<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
	<link rel="stylesheet" type="text/css" href="css/styleSheet.css" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>Mi biblioteca</title>
</head>

<body>

<div class="tituloCancion">
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV izq.png" alt="resultados" width="75"
			height="100" />
		</a>
		<p class="tituloCancion">
			<c:out value="Mi biblioteca" />
		</p>
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV der.png" alt="resultados" width="75"
			height="100" />
		</a>
	</div>
	<div class="superlibrary" >
	<div class="library">
		<div class="buscador">
			<p class="resultados">
			Canciones favoritas <br/>
			(50 visualizables de un total de <c:out value="${requestScope.total}"/>):
			</p>
		</div>
		<div class="artistAlbum">
				<br />
			<c:forEach items="${requestScope.usersSavedTracks.items}" var="item">
				<span> <a class="resultados" href="TrackPageController?trackId=${item.track.id}"> 
					<c:out value="${item.track.name}   " /></a> </span>
					<a title="Elimina la canción de la playlist" href="SpotifyRemoveController?trackId=${item.track.id}"> <img
				src="images/CorazonDobleVacio.png" alt="portada" width="13" height="13" /></a><br /> 
					<c:forEach items="${item.track.artists}" var="artist" varStatus="loop">
						<span> <a class="resultados" href="ArtistPageController?artistId=${artist.id}"> <c:out
							value="${artist.name}${loop.last ? '' : ','}" />
						</a> </span>
					</c:forEach>
				<br />
				<br />
			</c:forEach>
		</div>
	
	</div>
	</div>
</body>
</html>