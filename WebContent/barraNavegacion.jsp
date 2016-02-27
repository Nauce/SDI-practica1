<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<c:choose>

		<c:when test="${sessionScope.user == null}">

			<div class="section">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<ul class="nav nav-pills">
								<li class="active"><a href="login">Iniciar sesión</a></li>
								<li class=""><a href="listarViajes">Viajes ofertados</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

		</c:when>

		<c:otherwise>

			<div class="section">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<ul class="nav nav-pills">
								<li class="dropdown active"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown" role="button"
									aria-expanded="false"> ${sessionScope.user.login} <i
										class="fa fa-caret-down"></i></a>
									<ul class="dropdown-menu">
										<li><a href="modificarDatosPersonales">Modificar datos personales</a></li>
										<li class="divider"></li>
										<li><a href="#">Cerrar sesión</a></li>
									</ul></li>
								<li class=""><a href="listarViajes">Viajes ofertados</a></li>
								<li><a href="#">Mis viajes</a></li>
								<li><a href="registrarViaje">Crear viaje</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</c:otherwise>

	</c:choose>

</body>
</html>