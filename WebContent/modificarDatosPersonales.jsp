<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<form class="form-controll" method="post" action="modificarDatosPersonales">

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header">
						<h1>Modificar datos personales</h1>
					</div>
					
					<c:if test="${errores != null}">
								<div class="alert alert-danger">
									<p>${errores}</p>
								</div>
					</c:if>
					
					<c:if test="${EXITO != null}">
								<div class="alert alert-success">
									<p><strong>Tus datos han sido modificados correctamente</p>
								</div>
					</c:if>
					
						<div class="form-group">
							<label class="control-label" for="nombre">Nombre</label>
							<input class="form-control" id="nombre" name="nombre" type="text" required
							value="${sessionScope.user.name}">
						</div>
						<div class="form-group">
							<label class="control-label" for="apellidos">Apellidos</label>
							<input class="form-control" id="apellidos" name="apellidos"
								type="text" required value="${sessionScope.user.surname}">
						</div>

						<div class="form-group">
							<label class="control-label" for="email">Email</label><input
								class="form-control" id="email" name="email" type="email" required
								value="${sessionScope.user.email}">
						</div>
						<div class="form-group">
							<label class="control-label" for="contrasenyaActual">Contraseña actual</label><input
								class="form-control" id="contrasenyaActual" name="contrasenyaActual" type="password" required>
						</div>
						<div class="form-group">
							<label class="control-label" for="contrasenyaNueva">Contraseña nueva</label><input
								class="form-control" id="contrasenyaNueva" name="contrasenyaNueva" type="password" required>
						</div>
						<div class="form-group">
							<label class="control-label">Confirmar contraseña nueva</label><input
								class="form-control" id="confirmaContrasenyaNueva" name="confirmaContrasenyaNueva" type="password" required>
						</div>
						<button type="submit" class="btn btn-block btn-primary">Actualizar</button>
				</div>
			</div>
		</div>
	</div>

</form>

</body>
</html>