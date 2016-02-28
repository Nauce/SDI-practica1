<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h1>IdViaje = ${viaje.trip.id}</h1>
				<h4>
					<span class="label label-warning">${viaje.trip.availablePax}/${viaje.trip.maxPax}
						plazas libres</span>
				</h4>

				<br>

				<h3>${viaje.trip.departure.city}-
					${viaje.trip.destination.city}</h3>
				<h4>
					<span class="label label-info">Fecha límite:
						${viaje.trip.closingDate}</span>
				</h4>

				<br>

				<h4>
					Salida&nbsp;&nbsp;&nbsp;&nbsp;: &nbsp; ${viaje.trip.departure.city}
					&nbsp; ${viaje.trip.departureDate}
					<!--28/02/2016 &nbsp; 15:00  -->
				</h4>
				<h4>Llegada&nbsp;: &nbsp; ${viaje.trip.destination.city} &nbsp;
					${viaje.trip.arrivalDate}</h4>

				<br>

				<h4>Coste total : ${viaje.trip.estimatedCost } €</h4>

				<br>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Comentarios del Promotor sobre el viaje</h4>
					</div>
					<div class="panel-body">
						<h5>${viaje.trip.comments}</h5>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<h1>Participantes confirmados</h1>

				<br>

				<div class="panel panel-default">
					<div class="panel-body">
						<span class="label label-default">Promotor</span> <strong>&nbsp;
							<a href=#>${viaje.infoPromotor.usuario} (Rating :
								${viaje.infoPromotor.rating})</a>
							<h6>Comentarios acerca del usuario</h6>
							<ul>

								<c:forEach var="comentario"
									items="${viaje.infoPromotor.comentarios}">

									<li>${comentario }</li>

								</c:forEach>
							</ul>

						</strong>
					</div>
				</div>



				<c:forEach var="entry" items="${viaje.infoPasajeros}" varStatus="i">
					<div class="panel panel-default">
						<div class="panel-body">
							<strong><a href="#"> ${entry.value.usuario}(Rating:
									${entry.value.rating }) </a></strong>
							<h6>Comentarios acerca del usuario</h6>
							<ul>

								<c:forEach var="comentario" items="${entry.value.comentarios}">

									<li>${comentario }</li>

								</c:forEach>
							</ul>

						</div>
					</div>
				</c:forEach>







			</div>
		</div>

		<div class="row">
			<div class="container">
				<h3>Información adicional</h3>
				<div class="col-md-6">
					<h3>Lugar de Origen: ${viaje.trip.departure.city}</h3>
					<br>

					<h4>Calle : ${viaje.trip.departure.address}</h4>
					<h4>Ciudad : ${viaje.trip.departure.city}</h4>
					<h4>Provincia : ${viaje.trip.departure.state}</h4>
					<h4>País : ${viaje.trip.departure.country}</h4>
					<h4>Código postal : ${viaje.trip.departure.zipCode}</h4>
					<h4>Coordenadas GPS :
						${viaje.trip.departure.waypoint.lat}:${viaje.trip.departure.waypoint.lon}</h4>

					<br>


				</div>
				<div class="col-md-6">
					<h3>Lugar de destino: ${viaje.trip.arrival.city}</h3>
					<br>

					<h4>Calle : ${viaje.trip.destination.address}</h4>
					<h4>Ciudad : ${viaje.trip.destination.city}</h4>
					<h4>Provincia : ${viaje.trip.destination.state}</h4>
					<h4>País : ${viaje.trip.destination.country}</h4>
					<h4>Código postal : ${viaje.trip.destination.zipCode}</h4>
					<h4>Coordenadas GPS :
						${viaje.trip.destination.waypoint.lat}:${viaje.trip.destination.waypoint.lon}</h4>

					<br>
				</div>
			</div>

		</div>
	</div>
</body>
</html>