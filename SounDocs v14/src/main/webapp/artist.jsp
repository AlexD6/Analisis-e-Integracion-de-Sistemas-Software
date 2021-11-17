<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
	<link rel="stylesheet" type="text/css" href="css/styleSheet.css" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title><c:out value="${requestScope.artistName}" /></title>
</head>

<body>
	<div class="tituloCancion">
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV izq.png" alt="resultados" width="75"
			height="100" />
		</a>
		<p class="tituloCancion">
			<c:out value="${requestScope.artistName}" />
		</p>
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV der.png" alt="resultados" width="75"
			height="100" />
		</a>
	</div>

	<div class="tracks">
		<div class="buscador">
			<p class="resultados">Álbumes:</p>
		</div>
		
		<div class="artistAlbum">
		<br />
			<c:forEach items="${requestScope.artistAlbums.items}" var="album">
				<span> <a class="resultados"
					href="AlbumPageController?albumId=${album.id}"> <c:out
							value="${album.name}" /><br /> <c:out
							value="(${album.releaseDate})" />
				</a>
				</span>
				<br />
				<br />
			</c:forEach>
		</div>
	</div>

	<div class="fotoArtista">
		<img src="${requestScope.urlArtistImages}"
			alt="${requestScope.artistName}" width="400px" height="400px">
	</div>

	<div class="artistSongs">
		<div class="lyricArriba">
			<p class="lyricArriba">Canciones:</p>
		</div>
		<br />
		<div class="resultados">
			<c:forEach items="${requestScope.artistSongs.tracks}" var="track">
				<span> <a class="resultados"
					href="TrackPageController?trackId=${track.id}"> <c:out
							value="${track.name}" />
				</a>
				</span>
				<br />
				<br />
			</c:forEach>
		</div>

	</div>
	
	<div class="similarArtists">
		<div class="cajaAS">
			<p class="tituloAS">Artistas similares: </p> 
		</div>
		<br />
		<div class="resultados">
 			<c:choose>
    			<c:when test = "${empty requestScope.idas0}">
					<a class="resultados"
 						href="/error.jsp"> ${requestScope.as0}
					</a>    			
				</c:when>    
    			<c:otherwise>
        			<a class="resultados"
 						href="ArtistPageController?artistId=${requestScope.idas0}"> ${requestScope.as0}
					</a> 
    			</c:otherwise>
			</c:choose>
 					<br /><br />
			
 			<c:choose>
    			<c:when test = "${empty requestScope.idas1}">
					<a class="resultados"
 						href="/error.jsp"> ${requestScope.as1}
					</a>    			
				</c:when>    
    			<c:otherwise>
        			<a class="resultados"
 						href="ArtistPageController?artistId=${requestScope.idas1}"> ${requestScope.as1}
					</a> 
    			</c:otherwise>
			</c:choose>

					<br /><br />
 			<c:choose>
    			<c:when test = "${empty requestScope.idas2}">
					<a class="resultados"
 						href="/error.jsp"> ${requestScope.as2}
					</a>    			
				</c:when>    
    			<c:otherwise>
        			<a class="resultados"
 						href="ArtistPageController?artistId=${requestScope.idas2}"> ${requestScope.as2}
					</a> 
    			</c:otherwise>
			</c:choose>
					<br /><br />
				
 			<c:choose>
    			<c:when test = "${empty requestScope.idas3}">
					<a class="resultados"
 						href="/error.jsp"> ${requestScope.as3}
					</a>    			
				</c:when>    
    			<c:otherwise>
        			<a class="resultados"
 						href="ArtistPageController?artistId=${requestScope.idas3}"> ${requestScope.as3}
					</a> 
    			</c:otherwise>
			</c:choose>

					<br /><br />
 			<c:choose>
    			<c:when test = "${empty requestScope.idas4}">
					<a class="resultados"
 						href="/error.jsp"> ${requestScope.as4}
					</a>    			
				</c:when>    
    			<c:otherwise>
        			<a class="resultados"
 						href="ArtistPageController?artistId=${requestScope.idas4}"> ${requestScope.as4}
					</a> 
    			</c:otherwise>
			</c:choose>
 
					<br />
			 
		</div>
<br />
	</div>


</body>
</html>