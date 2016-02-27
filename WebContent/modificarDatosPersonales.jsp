<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<form class="form-controll" method="post" action="modificarDatosPersonales">

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header">
						<h1>Modificar datos personales</h1>
					</div>
					
						<div class="form-group">
							<label class="control-label" for="nombre">Nombre</label>
							<input class="form-control" id="nombre" name="nombre" type="text">
						</div>
						<div class="form-group">
							<label class="control-label" for="apellidos">Apellidos</label>
							<input class="form-control" id="apellidos" name="apellidos"
								type="text">
						</div>

						<div class="form-group">
							<label class="control-label" for="email">Email</label><input
								class="form-control" id="email" name="email" type="email">
						</div>
						<div class="form-group">
							<label class="control-label" for="contrasenya">Contraseña nueva</label><input
								class="form-control" id="contrasenya" name="contrasenya" type="password">
						</div>
						<div class="form-group">
							<label class="control-label">Confirmar contraseña nueva</label><input
								class="form-control" id="confirmaContrasenya" name="confirmaContrasenya "type="password">
						</div>
						<button type="submit" class="btn btn-block btn-primary">Actualizar</button>
				</div>
			</div>
		</div>
	</div>

</form>

</body>
</html>