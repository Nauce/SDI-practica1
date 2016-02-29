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

	<div class="container">

		<div class="row">
			<div class="col-md-12">

				<div class="page-header">
					<h1>Solicitudes del viaje ID ${solicitudesDto.idViaje}</h1>
				</div>

				<h3><span class="label label-info">
					Viaje ID ${solicitudesDto.idViaje} &nbsp;
					${solicitudesDto.departureCity} - ${solicitudesDto.destinationCity}
					&nbsp;&nbsp;${solicitudesDto.fechaSalida}
				</span></h3>
				<br>
				
				<h4><span class="label label-default">
					${solicitudesDto.plazasLibres} plazas libres
				</span></h4>
				
				<br>

				<div class="panel panel-default">
					<div class="panel-body">
						<h2>Admitidos</h2>
						<c:if test="${!solicitudesDto.admitidos.isEmpty()}">
							<br>
						</c:if>

						<c:forEach var="entry" items="${solicitudesDto.admitidos}"
							varStatus="i">

							<div class="popover-markup">
								<a href="#" class="trigger btn btn-default"> <span
									class="label label-success">Admitido</span>&nbsp; <font
									color="#228DAC"><strong>${entry.name}
											${entry.surname} (${entry.login})</strong></font></a>
								<div class="head hide">
									<strong>Pasar a</strong>
								</div>
								<div class="content hide">
									<form class="form-group" method="post"
										action="pasarAPendientes?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
										<button type="submit" class="btn btn-warning">
											<c:choose>
												<c:when test="${solicitudesDto.plazasLibres > 0}">Pendientes</c:when>
												<c:otherwise>&nbsp;Sin plaza&nbsp;&nbsp;</c:otherwise>
											</c:choose>
										</button>
									</form>
									<form class="form-group" method="post"
										action="excluirParticipante?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
										<button type="submit" class="btn btn-danger">
											&nbsp;Excluidos&nbsp;&nbsp;</button>
									</form>
								</div>
							</div>
							<br>

						</c:forEach>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-body">


						<c:if test="${solicitudesDto.plazasLibres > 0}">
							<h2>Pendientes</h2>
							<c:if test="${!solicitudesDto.pendientes.isEmpty()}">
								<br>
							</c:if>

							<c:forEach var="entry" items="${solicitudesDto.pendientes}"
								varStatus="i">

								<div class="popover-markup">
									<a href="#" class="trigger btn btn-default"> <span
										class="label label-warning">Pendiente</span>&nbsp; <font
										color="#228DAC"><strong>${entry.name}
												${entry.surname} (${entry.login})</strong></font></a>
									<div class="head hide">
										<strong>Pasar a</strong>
									</div>
									<div class="content hide">
										<form class="form-group" method="post"
											action="admitirParticipante?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
											<button type="submit" class="btn btn-success">
												Admitidos</button>
										</form>
										<form class="form-group" method="post"
											action="excluirParticipante?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
											<button type="submit" class="btn btn-danger">Excluidos</button>
										</form>
									</div>
								</div>
								<br>
							</c:forEach>

						</c:if>

						<c:if test="${solicitudesDto.plazasLibres == 0}">
							<h2>Sin plaza</h2>
							<c:if test="${!solicitudesDto.pendientes.isEmpty()}">
								<br>
							</c:if>

							<c:forEach var="entry" items="${solicitudesDto.pendientes}"
								varStatus="i">
								<div class="popover-markup">
									<a href="#" class="trigger btn btn-default"> <span
										class="label label-default">Sin plaza</span>&nbsp; <font
										color="#228DAC"><strong>${entry.name}
												${entry.surname} (${entry.login})</strong></font></a> <br>
									<div class="head hide">
										<strong>Pasar a</strong>
									</div>
									<div class="content hide">
										<form class="form-group" method="post"
											action="excluirParticipante?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
											<button type="submit" class="btn btn-danger">Excluidos</button>
										</form>
									</div>
								</div>
								<br>
							</c:forEach>
						</c:if>

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-body">
						<h2>Excluidos</h2>
						<c:if test="${!solicitudesDto.excluidos.isEmpty()}">
							<br>
						</c:if>

						<c:forEach var="entry" items="${solicitudesDto.excluidos}"
							varStatus="i">
							<div class="popover-markup">
								<a href="#" class="trigger btn btn-default"> <span
									class="label label-danger">Excluido</span>&nbsp; <font
									color="#228DAC"><strong>${entry.name}
											${entry.surname} (${entry.login})</strong></font></a> <br>
								<div class="head hide">
									<strong>Pasar a</strong>
								</div>
								<div class="content hide">
									<c:if test="${solicitudesDto.plazasLibres > 0}">
										<form class="form-group" method="post"
											action="admitirParticipante?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
											<button type="submit" class="btn btn-success">
												&nbsp;Admitidos&nbsp;&nbsp;</button>
										</form>
									</c:if>
									<form class="form-group" method="post"
										action="pasarAPendientes?idUser=${entry.id}&idViaje=${solicitudesDto.idViaje}">
										<button type="submit" class="btn btn-warning">
											<c:choose>
												<c:when test="${solicitudesDto.plazasLibres > 0}">Pendientes</c:when>
												<c:otherwise>&nbsp;Sin plaza&nbsp;&nbsp;</c:otherwise>
											</c:choose>
										</button>
									</form>
								</div>
							</div>
							<br>
						</c:forEach>

					</div>
				</div>


			</div>
		</div>

	</div>

	<script>
		$('.popover-markup>.trigger').popover({
			html : true,
			title : function() {
				return $(this).parent().find('.head').html();
			},
			content : function() {
				return $(this).parent().find('.content').html();
			},
			placement : 'right',
			trigger : 'focus'
		});
	</script>
</body>
</html>