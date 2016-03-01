<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>ShareMyTrip - Comentar en viaje</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet">
<link href="path/to/css/star-rating.min.css" media="all"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="path/to/js/star-rating.min.js" type="text/javascript"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>

</head>
<body>

	<form class="form-controll" method="post"
		action="comentarEnViaje">
		<div class="container">
			<div class="page-header">
				<h1>Comentar en viaje ID ${ dto.trip.id }</h1>
			</div>

			<div class="panel panel-default">
				<div class="panel-body">
					<h4>
						<span class="label label-info"> Viaje ID ${dto.trip.id}
							&nbsp; ${dto.trip.departure.city} - ${dto.trip.destination.city}
							&nbsp; ${dto.trip.departureDate}</span>
					</h4>
					<br>

					<p>
						<strong>Participante</strong>&nbsp;&nbsp;
						<select class="selectpicker">
							<c:forEach var="entry" items="${dto.participantes}" varStatus="i">
								<option>${entry.name} ${entry.surname} (${entry.login})</option>
							</c:forEach>
						</select>
					</p>

					<br>

					<p>
						<strong>Puntuación</strong> &nbsp;&nbsp;<input id="input-id"
							name="ratingValue" type="number" class="rating" min=0 max=5
							step=1 data-size="lg" value=3>
					</p>

					<br>

					<div class="form-group">
						<label for="comment">Comentar</label>
						<textarea class="form-control" rows="5" id="comment"></textarea>
					</div>
					
					<button type="submit" class="btn btn-primary">Enviar</button>
					
				</div>
			</div>

		</div>
	</form>

	<script>
		$("#input-id").rating({
			'size' : 'lg'
		});
	</script>

</body>
</html>