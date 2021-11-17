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
	<div class="tituloMensaje">
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV izq.png" alt="resultados" width="75"
			height="100" />
		</a>
		<a class = "tituloMensaje" href="SpotifyLibraryController?trackId=${requestScope.trackId}">
				<c:out value="Continuar" />
		</a>	
		<a title="Vuelta a inicio" href="/mainPage"> <img
			src="images/SounDocsV der.png" alt="resultados" width="75"
			height="100" />
		</a>
	</div>
	

	<div class="Mensaje">
		<p class="Mensaje">
		<c:out value="${requestScope.trackName} ${requestScope.message}"/>
		</p>
	</div>
	
	

	
</body>
</html>





