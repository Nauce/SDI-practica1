<%@ page contentType="text/html" pageEncoding="UTF-8"
	import="uo.sdi.dto.TripDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>ShareMyTrip - Comentarios del usuario</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h1>Comentarios sobre el usuario ${comentariosUsuario.user.name}</h1>
				</div>


				<c:forEach var="entry" items="${comentariosUsuario.comentarios}"
					varStatus="i">
					<c:forEach var="comment" items="${entry.value}" varStatus="j">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h5>
									<strong>Viaje ID ${comment.tripId} &nbsp;
										${comment.departure} - ${comment.destination} &nbsp;
										${comment.departureDate} </strong>
								</h5>
								
							</div>

							<div class="panel-body">
							<strong>${entry.key.login} escribió :</strong>
							<br>
							<br>
								${comment.comment}
							</div>
							
							<div class="panel-footer">
								<span class="label label-warning">Rating ${comment.valoracion}</span>
							</div>
						</div>

					</c:forEach>
				</c:forEach>

			</div>
		</div>
	</div>


</body>
</html>