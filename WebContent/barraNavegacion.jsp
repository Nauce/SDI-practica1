<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<c:choose>

		<c:when test="${sessionScope.user == null}">
			<div class="container">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="navbar-header">
							<a class="navbar-brand" href="principal.jsp">ShareMyTrip</a>
						</div>
						<ul class="nav navbar-nav">
							<li><a href="listarViajes">Viajes activos</a></li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<li><a href="login.jsp"><span
									class="glyphicon glyphicon-user"></span> Iniciar sesión</a></li>
						</ul>
					</div>
				</nav>
			</div>

		</c:when>

		<c:otherwise>

			<div class="container">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="navbar-header">
							<a class="navbar-brand" href="principal.jsp">ShareMyTrip</a>
						</div>
						<ul class="nav navbar-nav">
							<li><a href="listarViajes">Viajes activos</a></li>
							<li><a href="listarViajesOfertados">Mis viajes ofertados</a></li>
							<li><a href="misViajesImplicados">Mis viajes implicados</a></li>
							<li><a href="registrarViaje">Crear viaje</a></li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
							<a href="#" class="dropdown-toggle"
								data-toggle="dropdown">
									${sessionScope.user.login}<span class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a href="modificarDatosPersonales.jsp">Modificar
											datos personales</a></li>
									<li class="divider"></li>
									<li><a href="cerrarSesion">Cerrar sesión</a></li>
								</ul></li>
						</ul>
					</div>
				</nav>
			</div>

		</c:otherwise>

	</c:choose>

</body>
</html>