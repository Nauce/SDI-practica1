<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Página principal del usuario</title>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


</head>
<body>

	<br /> Es Vd el usuario número: ${contador}
	<h1>
		<a id="listarViajes" class="text-center" href="listarViajes"> Ver
			viaje User </a>
	</h1>

	<h1 class="text-center">
		<a id="registrarViaje" href="registrarViaje"> Registrar </a>
	</h1>

	<h1 class="text-center">
		<a id="listarViajesOfertados" href="listarViajesOfertados"> Mis
			viajes Ofertados </a>
	</h1>

	<br>

	<div class="popover-markup">
		<a href="#" class="trigger btn btn-default">Pepita Jimenez</a>
		<div class="head hide">Pasar a</div>
		<div class="content hide">
			<div class="form-group">
				<button type="submit" class="btn btn-default btn-block">
					Submit</button>
			</div>
			<button type="submit" class="btn btn-default btn-block">
				Submit</button>
		</div>
	</div>

	<br>

	<div class="popover-markup">
		<a href="#" class="trigger btn btn-default">Pepito Perez</a>
		<div class="head hide">Pasar a</div>
		<div class="content hide">
			<div class="form-group">
				<button type="submit" class="btn btn-default btn-block">
					Submit</button>
			</div>
			<button type="submit" class="btn btn-default btn-block">
				Submit</button>
		</div>
	</div>

	<div class="popover-markup">
		<a href="#" class="trigger btn btn-default">${usuario}</a>
		<div class="head hide">Pasar a</div>
		<div class="content hide">
			<form class="form-group" method="post"
				action="comentariosUsuario?id=306">
				<button type="submit" class="btn btn-warning">Pendientes</button>
			</form>
			<form class="form-group" method="post"
				action="comentariosUsuario?id=306">
				<button type="submit" class="btn btn-danger">Excluidos</button>
			</form>
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
			}
		});
	</script>

</body>
</html>
