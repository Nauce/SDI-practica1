<%@ page contentType="text/html" pageEncoding="UTF-8"
	import="uo.sdi.dto.TripDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>ShareMyTrip - Viajes activos</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>




	<div class="container">
		<div class="page-header">
			<h1>Viajes activos</h1>
		</div>

		<section class="col-md-4">
			<strong>Ordenar </strong>
			<form action="ordenarViajesPublico" method="post">
				<select name="filtrados" onchange="this.form.submit()"
					class="form-control">
					<option>Selecciona una opcion</option>
					<option>Fecha Descendente</option>
					<option>Fecha Ascendente</option>
					<option>Destino Descendente</option>
					<option>Destino Ascendente</option>
				</select>

			</form>
		</section>
		<table class="table table-bordered">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
				<th>Fecha Salida</th>
				<th>Fecha límite</th>
				<th>Coste (€)</th>
			</tr>
			<c:forEach var="entry" items="${listaViajes}" varStatus="i">
				<tr id="item_${i.index}">
					<td><a href="listarViajesUsuario?id=${entry.id}">${entry.id}</a></td>
					<td>${entry.departure.city}</td>
					<td>${entry.destination.city}</td>
					<td>${entry.availablePax}</td>
					<td><fmt:formatDate type="both" dateStyle="short"
							timeStyle="short" value="${entry.departureDate}" /></td>
					<td><fmt:formatDate type="both" dateStyle="short"
							timeStyle="short" value="${entry.closingDate}" /></td>
					<td>${entry.estimatedCost}</td>
				</tr>
			</c:forEach>
		</table>

	</div>


</body>
</html>