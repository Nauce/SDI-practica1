<%@ page contentType="text/html" pageEncoding="UTF-8"
	import="uo.sdi.dto.TripDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>ShareMyTrip - Listado de viajes</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>




	<div class="container">
		<h1 class="text-center">Estos son nuestros viajes</h1>

		<section class="col-md-4">
			<form action="ordenarViajesPublico" method="post">
				<select class="form-control">
					<option>Fecha descendente</option>
					<option>Fecha ascendente</option>
					<option>Destino descendente</option>
					<option>Destino ascendente</option>
				</select>

			</form>
		</section>
		<table class="table table-bordered">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
			</tr>
			<c:forEach var="entry" items="${listaViajes}" varStatus="i">
				<tr id="item_${i.index}">
					<td><a href="listarViajesUsuario?id=${entry.id}">${entry.id}</a></td>
					<td>${entry.departure.city}</td>
					<td>${entry.destination.city}</td>
					<td>${entry.availablePax}</td>
				</tr>
			</c:forEach>
		</table>

	</div>


</body>
</html>