<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ShareMyTrip - Solicitudes viaje</title>

<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

	<script>
		$('.popover-markup>.trigger').popover({
			html : true,
			title : function() {
				return $(this).parent().find('.head').html();
			},
			content : function() {
				return $(this).parent().find('.content').html();
			}
		});
	</script>

	<div class="container">

		<div class="row">
			<div class="col-md-12">

				<div class="page-header">
					<h1>Solicitudes del viaje ID ${solicitudesDto.idViaje}</h1>
				</div>

				<c:if test="${ !solicitudesDto.admitidos.isEmpty()
								&& !solicitudesDto.pendientes.isEmpty()
								&& !solicitudesDto.excluidos.isEmpty() }"></c:if>


				<c:if test="${ !solicitudesDto.admitidos.isEmpty() }">

					<div class="page-header">
						<h2>Admitidos</h2>
					</div>

					<c:forEach var="entry" items="${solicitudesDto.admitidos}" varStatus="i">

						<div class="popover-markup">
							<a href="#" class="trigger btn btn-default"><strong>${entry.name}
							 ${entry.surname} (${entry.login})</strong></a>
							<div class="head hide">Pasar a</div>
							<div class="content hide">
								<form class="form-group" method="post"
									action="pendiente?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
									<button type="submit" class="btn btn-warning">
										Pendientes</button>
								</form>
								<form class="form-group" method="post"
									action="excluir?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
									<button type="submit" class="btn btn-danger">
										Excluidos</button>
								</form>
							</div>
						</div>

					</c:forEach>

				</c:if>



				<c:if test="${!pendientes.isEmpty()}">

					<c:if test="${solicitudesDto.plazasLibres > 0}">

					<div class="page-header">
							<h2>Pendientes</h2>
						</div>

						<div class="popover-markup">
							<a href="#" class="trigger btn btn-default"><strong>${entry.name}
							 ${entry.surname} (${entry.login})</strong></a>
							<div class="head hide">Pasar a</div>
							<div class="content hide">
								<form class="form-group" method="post"
									action="admitir?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
									<button type="submit" class="btn btn-warning">
										Admitidos</button>
								</form>
								<form class="form-group" method="post"
									action="excluir?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
									<button type="submit" class="btn btn-danger">Excluidos</button>
								</form>
							</div>
						</div>

					</c:if>
					
					<c:if test="${solicitudesDto.plazasLibres == 0}">>

					<div class="page-header">
							<h2>Sin plaza</h2>
						</div>

						<div class="popover-markup">
							<a href="#" class="trigger btn btn-default"><strong>${entry.name}
							 ${entry.surname} (${entry.login})</strong></a>
							<div class="head hide">Pasar a</div>
							<div class="content hide">
								<form class="form-group" method="post"
									action="excluir?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
									<button type="submit" class="btn btn-danger">Excluidos</button>
								</form>
							</div>
						</div>

					</c:if>
					

				</c:if>


				<c:if test="${!excluidos.isEmpty()}">

					<div class="page-header">
						<h2>Excluidos</h2>
					</div>

					<div class="popover-markup">
						<a href="#" class="trigger btn btn-default"><strong>${entry.name}
							 ${entry.surname} (${entry.login})</strong></a>
						<div class="head hide">Pasar a</div>
						<div class="content hide">
							<form class="form-group" method="post"
								action="admitir?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
								<button type="submit" class="btn btn-warning">
									Admitidos</button>
							</form>
							<form class="form-group" method="post"
								action="pendiente?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
								<button type="submit" class="btn btn-danger">
									Pendientes</button>
							</form>
						</div>
					</div>

				</c:if>


			</div>
		</div>

	</div>


</body>
</html>