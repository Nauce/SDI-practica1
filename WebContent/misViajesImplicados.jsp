<%@ page contentType="text/html" pageEncoding="UTF-8"
	import="uo.sdi.model.SeatStatus"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>ShareMyTrip - Mis viajes implicados</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:useBean id="now" class="java.util.Date"/>

	<div class="container">
	
	<c:if test="${requestScope.comentarAUsuarioAction != null}">
		<div class="alert alert-success">
			 <p>Comentario guardado con éxito</p>
		</div>
	</c:if>
	
	<c:if test="${requestScope.cancelarPlazaAction != null}">
		<div class="alert alert-success">
			 <p>Plaza cancelada correctamente</p>
		</div>
	</c:if>
	
		<div class="page-header">
			<h1>Mis viajes implicados</h1>
		</div>

		<table class="table table-bordered">
			<tr>
				<th>Estado</th>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
				<th>Fecha Salida</th>
				<th>Fecha Límite</th>
				<th>Coste (€)</th>
				<th>Comentar</th>
				<th>Cancelar</th>
			</tr>

			<c:set var="ACCEPTED" value="<%=SeatStatus.ACCEPTED%>" />
			<c:set var="EXCLUDED" value="<%=SeatStatus.EXCLUDED%>" />
			<c:forEach var="entry" items="${viajesImplicadosDto.trips}"
				varStatus="i">
				<tr id="item_${i.index}">
					<td>
					<c:choose>
						<c:when test="${ entry.value == null }">
						
							<c:choose>
								<c:when test="${ entry.key.availablePax > 0 }">
									<span class="label label-warning">Pendiente</span>
								</c:when>
								<c:otherwise>
									<span class="label label-default">Sin plaza</span>
								</c:otherwise>
							</c:choose>
				
						</c:when>
						
						<c:when test="${ entry.value == ACCEPTED}">
							<span class="label label-success">Admitido</span>
						</c:when>
						
						<c:when test="${ entry.value == EXCLUDED}">
							<span class="label label-danger">Excluido</span>
						</c:when>
						
					</c:choose>
					</td>
					<td><a href="listarViajesUsuario?id=${entry.key.id}">${entry.key.id}</a></td>
					<td>${entry.key.departure.city}</td>
					<td>${entry.key.destination.city}</td>
					<td>${entry.key.availablePax}</td>
					<td><fmt:formatDate type="both" dateStyle="short"
								timeStyle="short" value="${entry.key.departureDate}"/></td>
					<td><fmt:formatDate type="both" dateStyle="short"
								timeStyle="short" value="${entry.key.closingDate}"/></td>
					<td>${entry.key.estimatedCost}</td>
					<td>
						<form method="post" action="comentarEnViaje?id=${entry.key.id}">
							<button id="comentar" name="comentar"
								<c:if test="${entry.key.arrivalDate < now || entry.value != ACCEPTED}">disabled="disabled"</c:if>
								class="btn btn-primary">Comentar</button>
						</form>
					</td>
					<td>
						<form method="post" action="cancelarPlaza?id=${entry.key.id}">
							<button id="cancelar" name="cancelar" 
							<c:if test="${entry.key.closingDate < now || entry.value == EXCLUDED}">disabled="disabled"</c:if>
							class="btn btn-danger">Cancelar</button>
						</form>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>


</body>
</html>