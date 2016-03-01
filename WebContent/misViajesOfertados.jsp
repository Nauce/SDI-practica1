<%@ page contentType="text/html" pageEncoding="UTF-8"
	import="uo.sdi.dto.ViajeImplicadoDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<%@ page import="uo.sdi.model.TripStatus"%>
<!DOCTYPE>
<html>
<head>
<title>ShareMyTrip - Mis viajes ofertados</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<jsp:useBean id="now" class="java.util.Date" />
	<div class="container">

		<c:if test="${ modificarViajeAction != null}">
			<div class="alert alert-success">
				<p>Viaje modificado con éxito</p>
			</div>
		</c:if>

		<c:if test="${ registrarViajeAction != null}">
			<div class="alert alert-success">
				<p>Viaje registrado con éxito</p>
			</div>
		</c:if>

		<div class="page-header">
			<h1>Mis viajes ofertados</h1>
		</div>

		<section class="col-md-4">
			<strong>Ordenar</strong>
			<form action="ordenarViajesPromotor" method="post">
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
		<c:set var="DONE" value="<%=TripStatus.DONE%>" />

		<table class="table table-bordered">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
				<th>Fecha Salida</th>
				<th>Fecha Límite</th>
				<th>Coste (€)</th>
				<th>Solicitudes</th>
				<th>Modificar</th>
				<th>Borrar</th>
				<th>Comentar</th>
			</tr>
			<c:forEach var="entry" items="${viajesOfertados}" varStatus="i">
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


					<td>
						<form method="post" action="solicitudesViaje?id=${entry.id}">
							<button id="solicitudes" name="solicitudes"
								<c:if test="${entry.closingDate < now}">disabled="disabled"</c:if>
								class="btn btn-success">Solicitudes</button>
						</form>
					</td>
					<td>
						<form method="post" action="modificarViaje?id=${entry.id}">
							<button id="borrar" name="borrar"
								<c:if test="${entry.closingDate < now}">disabled="disabled"</c:if>
								class="btn btn-warning">Modificar</button>
						</form>
					</td>
					<td>
						<form method="post" action="borrarViaje?id=${entry.id}">
							<button id="borrar" name="borrar"
								<c:if test="${entry.closingDate < now}">disabled="disabled"</c:if>
								class="btn btn-danger">Borrar</button>
						</form>
					</td>
					<td>
						<form method="post" action="comentarEnViaje?id=${entry.id}">
							<button id="comentar" name="comentar"
								<c:if test="${entry.arrivalDate < now}">disabled="disabled"</c:if>
								class="btn btn-primary">Comentar</button>
						</form>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>