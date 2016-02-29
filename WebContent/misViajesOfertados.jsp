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
		<h1 class="text-center">Estos son sus viajes ofertados</h1>
		<p>Puede borrar uno si lo necesita, asi como modificarlo</p>
		<table class="table table-bordered">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
				<th>Fecha Salida</th>
				<th>Fecha Límite</th>
				<th>Coste</th>
				<th>Solicitudes</th>
				<th>Modificar</th>
				<th>Borrar</th>
			</tr>
			<c:forEach var="entry" items="${viajesOfertados}" varStatus="i">
				<tr id="item_${i.index}">
					<td><a href="listarViajesUsuario?id=${entry.id}">${entry.id}</a></td>
					<td>${entry.departure.city}</td>
					<td>${entry.destination.city}</td>
					<td>${entry.availablePax}</td>
					<td>${entry.departureDate}</td>
					<td>${entry.closingDate}</td>
					<td>${entry.estimatedCost}</td>
					<td>
						<form method="post" action="solicitudesViaje?id=${entry.id}">
							<button id="solicitudes" name="solicitudes"
								class="btn btn-success">Solicitudes</button>
						</form>
					</td>
					<td>
						<form method="post" action="modificarViaje?id=${entry.id}">
							<button id="borrar" name="borrar" class="btn btn-warning">Modificar</button>
						</form>
					</td>
					<td>
						<form method="post" action="borrarViaje?id=${entry.id}">
							<button id="borrar" name="borrar" class="btn btn-danger">Borrar</button>
						</form>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>