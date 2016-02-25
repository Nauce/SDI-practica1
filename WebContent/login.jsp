<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ShareMyTrip - Inicie sesión</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>


<body>

	<form class="form-horizontal" method="post" action="validarse">
		<fieldset>

			<!-- Form Name -->
			<h1 class="text-center">Inicie Sesion</h1>



			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="usuario">Usuario</label>
				<div class="col-md-4">
					<input id="nombreUsuario" name="nombreUsuario" type="text"
						placeholder="" class="form-control input-md">

				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Contraseña</label>
				<div class="col-md-4">
					<input id="contrasenya" name="contrasenya" type="password"
						placeholder="" class="form-control input-md">

				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="enviar">Enviar</label>
				<div class="col-md-4">
					<button id="enviar" name="enviar" class="btn btn-primary">Enviar</button>
				</div>
			</div>

		</fieldset>
	</form>

	<h1 class="text-center">
		<a id="listarViajes" href="listarViajes"> Vea nuestros viajes </a>
	</h1>

	


</body>
</html>