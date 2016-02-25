<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
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


	<form  method="post" action="registrarViaje">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h1>
							Inserte los datos de su proximo viaje ofertado<br>
						</h1>
					</div>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<h1>Punto de origen</h1>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Calle<br></label><input
								class="form-control" id="calleOrigen" name="calleOrigen" type="text">
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Ciudad<br></label><input
								class="form-control"  id="ciudadOrigen" name="ciudadOrigen"  type="text">
						</div>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Provincia<br></label><input
								class="form-control"  id="privinciaOrigen" name="provinciaOrigen"  type="text">
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">País<br></label><input
								class="form-control"  id="paisOrigen" name="paisOrigen"  type="text">
						</div>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Código
								Postal<br>
							</label><input class="form-control"  id="codigoPostalOrigen" name="codigoPostalOrigen"  type="text">
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Coordenadas
								GPS<br>
							</label><input class="form-control"  id="coordenadasOrigen" name="coordenadasOrigen" 
								type="text" placeholder="Formato 'LAT'&'LON'">
						</div>
					</div>
					<div class="col-md-6">
						<h1>Punto de Destino</h1>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Calle<br></label><input
								class="form-control" id="calleDestino" name="calleDestino" type="text">
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Ciudad<br></label><input
								class="form-control"  id="ciudadDestino" name="ciudadDestino"  type="text">
						</div>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Provincia<br></label><input
								class="form-control"  id="privinciaDestino" name="provinciaDestino"  type="text">
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">País<br></label><input
								class="form-control"  id="paisDestino" name="paisDestino"  type="text">
						</div>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Código
								Postal<br>
							</label><input class="form-control"  id="codigoPostalDestino" name="codigoPostalDestino"  type="text">
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Coordenadas
								GPS<br>
							</label><input class="form-control"  id="coordenadasDestino" name="coordenadasDestino" 
								type="text" placeholder="Formato 'LAT'&'LON' ">
						</div>
					</div>

					
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label" for="exampleInputtext1">Fecha
							de Salida<br>
						</label><input class="form-control" id="fechaSalida" name="fechaSalida"  type="date">
					</div>
					<div class="form-group">
						<label class="control-label" for="exampleInputtext1">Hora
							de Salida<br>
						</label><input class="form-control" id="horaSalida" name="horaSalida" 
							type="text">
					</div>


					<div class="form-group">
						<label class="control-label" for="exampleInputtext1">Fecha
							de llegada estimada<br>
						</label><input class="form-control" id="fechaLlegada" name="fechaLlegada"  type="date">
					</div>
					<div class="form-group">
						<label class="control-label" for="exampleInputtext1">Fecha
							límite de inscripción<br>
						</label><input class="form-control" id="fechaLimiteInscripcion" name="fechaLimiteInscripcion"
							type="date">
					</div>

					<div class="form-group">
						<label class="control-label" for="exampleInputtext1">Coste
							estimado<br>
						</label><input class="form-control" id="costeEstimado" name="costeEstimado" type="text">
					</div>
					<div class="form-group">
						<label class="control-label" for="exampleInputtext1">Plazas
							máximas<br>
						</label><input class="form-control" id="plazasMaximas" name="plazasMaximas"
							type="text">
					</div>

					<div class="form-group">
						<label class="control-label" for="exampleInputtext1">Plazas
							disponibles<br>
						</label><input class="form-control"  id="plazasDisponibles" name="plazasDisponibles" type="text">
					</div>
					<div class="form-group">
						<label class="control-label" for="exampleInputtext1">Comentarios
							oportunos<br>
						</label><input class="form-control"  id="comentarios" name="comentarios"
							type="text">
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<button id="enviar" name="enviar"  class="btn btn-primary">Enviar</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>