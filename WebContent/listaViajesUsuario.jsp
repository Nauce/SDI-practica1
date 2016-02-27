<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css">
</head>
<body>


	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<h2>Datos del viaje</h2>

					<ul>
						<li>
							<h3>ID del viaje : ${viaje.id}</h3>
						</li>

						<li>
							<h3>Ciudad de Origen : ${viaje.origen}</h3>
						</li>
						<li>
							<h3>Ciudad de Destino : ${viaje.destino}</h3>
						</li>

						<li>
							<h3>Plazas libres : ${viaje.plazasLibres}</h3>
						</li>
						<li>
							<h3>Plazas Maximas : ${viaje.plazasMaximas}</h3>
						</li>
						<li>
							<h3>Promotor: ${viaje.promotor}</h3>
						</li>


					</ul>
				</div>
				<div class="col-md-8">

					<ul>
						<c:forEach var="entry" items="${viaje.infoPasajeros}" varStatus="i">
							<h3><li>${entry.value.usuario} (Rating : ${entry.value.rating })</li></h3>
							<h4>Comentarios acerca del usuario</h4>
							<ul>
							
								<c:forEach var="comentario" items="${entry.value.comentarios}">

									<li>${comentario }</li>

								</c:forEach>
							</ul>

						</c:forEach>
					</ul>


				</div>
			</div>
		</div>
	</div>
</body>
</html>


</body>
</html>