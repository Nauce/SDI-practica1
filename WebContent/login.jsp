<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>ShareMyTrip - Iniciar sesión</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>


<body>

	<form class="form-controll" method="post" action="login">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="page-header">
							<h1>Iniciar sesión</h1>
						</div>
						<form role="form">

							<c:if test="${sessionScope.registrarseAction != null}">
								<div class="alert alert-success">
									<p>
										<strong>Tu cuenta ha sido creada con éxito con el
											nombre de usuario <i>${registrarseAction}</i> <br>
										<br>
										</strong> Inicie sesión para empezar a crear y compartir tus viajes
									</p>
								</div>
							</c:if>

							<c:if test="${error != null}">
								<div class="alert alert-danger">
									<p>Usuario o contraseña incorrectos</p>
								</div>
							</c:if>

							<div class="form-group">
								<label class="control-label" for="nombreUsuario">Usuario</label>
								<input class="form-control" id="nombreUsuario"
									name="nombreUsuario" type="text">
							</div>

							<div class="form-group">
								<label class="control-label" for="contrasenya">Contraseña</label>
								<input class="form-control" id="contrasenya" name="contrasenya"
									type="password">
							</div>

							<button type="submit" class="btn btn-block btn-primary">Iniciar&nbsp;sesión</button>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="text-center well well-sm">
							<p class="text-muted">
								¿Eres nuevo en ShareMyTrip?&nbsp; <a id="registrarse"
									href="registrarse">Regístrate</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>

	</form>

	<h1 class="text-center">
		<a id="listarViajes" href="listarViajes"> Vea nuestros viajes </a>
	</h1>




</body>
</html>