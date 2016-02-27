<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Página principal del usuario</title>
<link rel stylesheet="css" href="css/bootstrap.min.css">

</head>
<body>
	
	<br /> Es Vd el usuario número: ${contador}
	<a id="listarViajes" class="text-center"
		href="listarViajes">
		<h1>Ver viaje User</h1>
	</a>

	<h1 class="text-center">
		<a id="registrarViaje" href="registrarViaje">
		Registrar </a>
	</h1>
	
	<h1 class="text-center">
		<a id="listarViajesOfertados" href="listarViajesOfertados">
		Mis viajes Ofertados </a>
	</h1>



</body>
</html>
