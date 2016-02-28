<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE>
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


	<form method="post" action="modificarViaje?id=${viaje.id}">
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
		
		<c:if test="${fechaPrevia != null}">
			<div class="alert alert-danger">
				<p>Las fechas que ha insertado son incorrectas, las fecha de
					llegada no puede ser previa a las fechas de salida y fecha de
					limite de plazo</p>
			</div>
		</c:if>
		
	
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
									class="form-control" id="calleOrigen" name="calleOrigen"
									type="text" required>
							</div>
							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Ciudad<br></label><input
									class="form-control" id="ciudadOrigen" name="ciudadOrigen"
									type="text" required>
							</div>

							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Provincia<br></label><input
									class="form-control" id="privinciaOrigen"
									name="provinciaOrigen" type="text" required>
							</div>
							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">País<br></label><input
									class="form-control" id="paisOrigen" name="paisOrigen"
									type="text" required>
							</div>

							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Código
									Postal<br>
								</label><input class="form-control" id="codigoPostalOrigen"
									name="codigoPostalOrigen" type="text" pattern="[0-9]{5}"
									required>
							</div>
							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Coordenadas
									GPS<br>
								</label><input class="form-control" id="coordenadasOrigen"
									name="coordenadasOrigen" type="text"
									placeholder="Formato 'LAT'&'LON'" pattern="[0-9]+&[0-9]+">
							</div>
						</div>
						<div class="col-md-6">
							<h1>Punto de Destino</h1>

							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Calle<br></label><input
									class="form-control" id="calleDestino" name="calleDestino"
									type="text" required>
							</div>
							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Ciudad<br></label><input
									class="form-control" id="ciudadDestino" name="ciudadDestino"
									type="text" required>
							</div>

							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Provincia<br></label><input
									class="form-control" id="privinciaDestino"
									name="provinciaDestino" type="text" required>
							</div>
							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">País<br></label><input
									class="form-control" id="paisDestino" name="paisDestino"
									type="text" required>
							</div>

							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Código
									Postal<br>
								</label><input class="form-control" id="codigoPostalDestino"
									name="codigoPostalDestino" type="text" pattern="[0-9]{5}"
									required>
							</div>
							<div class="form-group">
								<label class="control-label" for="exampleInputtext1">Coordenadas
									GPS<br>
								</label><input class="form-control" id="coordenadasDestino"
									name="coordenadasDestino" type="text"
									placeholder="Formato 'LAT'&'LON' " pattern="[0-9]+&[0-9]+">
							</div>
						</div>


					</div>
					<p>En caso de que el navegador no soporte la etiqueta de html5
						(input type ="date") y sucedaneos, el formato de entrada para las
						fechas será yyyy-mm-dd</p>
					<div class="row">
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Fecha
								de Salida<br>
							</label><input class="form-control" id="fechaSalida" name="fechaSalida"
								type="date" required>
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Hora
								de Salida<br>
							</label><input class="form-control" id="horaSalida" name="horaSalida"
								placeholder="formato hh:mm" type="text"
								pattern="[0-9]{2}:[0-9]{2}" required>
						</div>


						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Fecha
								de llegada estimada<br>
							</label><input class="form-control" id="fechaLlegada" name="fechaLlegada"
								type="date" required>
						</div>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Hora
								de Llegada estimada<br>
							</label><input class="form-control" id="horaLlegada" name="horaLlegada"
								placeholder="formato hh:mm" type="text"
								pattern="[0-9]{2}:[0-9]{2}" required>
						</div>


						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Fecha
								límite de inscripción<br>
							</label><input class="form-control" id="fechaLimiteInscripcion"
								name="fechaLimiteInscripcion" type="date" required>
						</div>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Coste
								estimado<br>
							</label><input class="form-control" id="costeEstimado"
								name="costeEstimado" type="number" min="0" max="1000" required>
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Plazas
								máximas<br>
							</label><input class="form-control" id="plazasMaximas"
								name="plazasMaximas" type="number" min="1" max="5" required>
						</div>

						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Plazas
								disponibles<br>
							</label><input class="form-control" id="plazasDisponibles"
								name="plazasDisponibles" type="number" min="1" max="5" required>
						</div>
						<div class="form-group">
							<label class="control-label" for="exampleInputtext1">Comentarios
								oportunos<br>
							</label>
							<textarea class="form-control" id="comentarios"
								name="comentarios" type="text" required> </textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<button id="enviar" name="enviar" class="btn btn-primary">Enviar</button>
						</div>
					</div>
				</div>
			</div>
	</form>

	<h2>
		<a id="login" class="text-right" href="listarViajesOfertados">
			Atrás </a>
	</h2>
</body>
</html>