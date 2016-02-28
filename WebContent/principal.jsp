<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Página principal del usuario</title>
<link rel stylesheet="css" href="css/bootstrap.min.css">
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('[data-toggle="popover"]')
								.popover(
										{
											placement : 'top',
											html : true,
											title : 'User Info <a href="#" class="close" data-dismiss="alert">&times;</a>',
											content : '<div class="media">		<button type="submit" class="btn btn-block btn-primary">Iniciar&nbsp;sesión</button> </br> 		<button type="submit" class="btn btn-block btn-primary">Iniciar&nbsp;sesión</button>  </div>'
										});
						$(document).on("click", ".popover .close", function() {
							$(this).parents(".popover").popover('hide');
						});
					});
</script>
<style type="text/css">
.bs-example {
	margin: 200px 150px 0;
}

.popover-title .close {
	position: relative;
	bottom: 3px;
}
</style>

</head>
<body>

	<br /> Es Vd el usuario número: ${contador}
	<a id="listarViajes" class="text-center" href="listarViajes">
		<h1>Ver viaje User</h1>
	</a>

	<h1 class="text-center">
		<a id="registrarViaje" href="registrarViaje"> Registrar </a>
	</h1>

	<h1 class="text-center">
		<a id="listarViajesOfertados" href="listarViajesOfertados"> Mis
			viajes Ofertados </a>
	</h1>

	<div class="bs-example">
		<button type="button" class="btn btn-primary" data-toggle="popover">Click
			Me</button>
</body>
</html>
