<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>ShareMyTrip - Listado de viajes</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<h1 class="text-center">Estos son nuestros viajes</h1>
	<table class="table table-bordered">
		<tr>
			<th>ID viaje</th>
			<th>Origen</th>
			<th>Destino</th>
			<th>Plazas libres</th>
			<th>Promotor</th>
			<th>Puntuación Promotor</th>
			<th>Comentarios Promotor</th>
			<th>Participantes</th>
		</tr>

		<c:forEach var="entry" items="${listaViajesUsuario}" varStatus="i">
			<tr id="item_${i.index}">
				<td><a href="mostrarViaje?id=${entry.id}">${entry.id}</a></td>
				<td>${entry.origen}</td>
				<td>${entry.destino}</td>
				<td>${entry.plazasLibres}</td>
				<td>${entry.promotor}</td>
				<td>${entry.puntuacionPromotor}</td>
				<td>${entry.comentariosPromotor}</td>
				<td>${entry.participantes}</td>
			</tr>
		</c:forEach>
	</table>

	<a id="login" class="text-right" href="principal"> <h2>Atrás</h2></a>
</body>
</html>