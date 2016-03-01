<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>ShareMyTrip - Registrarse</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<form class="form-controll" method="post" action="registrarse">

		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="page-header">
							<h1>
								Únete a ShareMyTrip <small> <br> <br>La mejor
									forma de compartir tu vehículo y gastos de viaje
								</small>
							</h1>
						</div>

						<c:if test="${errores != null && !errores.isEmpty()}">
							<div class="alert alert-danger">
								<p>${errores}</p>
							</div>
						</c:if>
						<div class="form-group">
							<label class="control-label" for="nombreUsuario">Nombre
								de usuario</label> <input class="form-control" id="nombreUsuario"
								name="nombreUsuario" type="text" value="${nombreUsuario}" required>
						</div>

						<div class="form-group">
							<label class="control-label" for="nombre">Nombre <br>
							</label> <input class="form-control" id="nombre" name="nombre"
								type="text" value="${nombre}" required>
						</div>

						<div class="form-group">
							<label class="control-label" for="apellidos">Apellidos <br>
							</label> <input class="form-control" id="apellidos" name="apellidos"
								type="text" value="${apellidos}" required>
						</div>

						<div class="form-group">
							<label class="control-label" for="email">Email <br>
							</label> <input class="form-control" id="email" name="email" type="email"
								value="${email}" required>
						</div>

						<div class="form-group">
							<label class="control-label" for="contrasenya">Contraseña
								<br>
							</label> <input class="form-control" id="contrasenya" name="contrasenya"
								type="password" value="${contrasenya}" required>
						</div>
						<div class="form-group">
							<label class="control-label" for="confirmaContrasenya">Confirma
								tu contraseña <br>
							</label> <input class="form-control" id="confirmaContrasenya"
								name="confirmaContrasenya" type="password"
								value="${confirmaContrasenya}" required>
						</div>


						<button type="submit" id="enviarBoton" name="enviarBoton" class="btn btn-block btn-primary">Crear&nbsp;cuenta</button>

					</div>
				</div>
			</div>
		</div>

	</form>

</body>
</html>