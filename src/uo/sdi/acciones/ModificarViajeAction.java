package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.util.DateUtil;

public class ModificarViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String resultado = "FRACASO";

		if (request.getParameter("calleOrigen") != null) {
			resultado = "EXITO";
			String calleOrigen = request.getParameter("calleOrigen");
			String ciudadOrigen = request.getParameter("ciudadOrigen");
			String provinciaOrigen = request.getParameter("provinciaOrigen");
			String paisOrigen = request.getParameter("paisOrigen");
			String codigoPostalOrigen = request
					.getParameter("codigoPostalOrigen");
			String coordenadasOrigen = request
					.getParameter("coordenadasOrigen");
			String calleDestino = request.getParameter("calleDestino");
			String ciudadDestino = request.getParameter("ciudadDestino");
			String provinciaDestino = request.getParameter("provinciaDestino");
			String paisDestino = request.getParameter("paisDestino");
			String codigoDestino = request.getParameter("codigoPostalDestino");
			String coordenadasDestino = request
					.getParameter("coordenadasDestino");
			String fechaSalida = request.getParameter("fechaSalida");
			String fechaLlegada = request.getParameter("fechaLlegada");
			String fechaLimiteInscripcion = request
					.getParameter("fechaLimiteInscripcion");
			String costeEstimado = request.getParameter("costeEstimado");
			String plazasMaximas = request.getParameter("plazasMaximas");
			String plazasDisponibles = request
					.getParameter("plazasDisponibles");
			String comentarios = request.getParameter("comentarios");

			String horaSalida = request.getParameter("horaSalida");
			String horaLlegada = request.getParameter("horaLlegada");

			String[] horaYMinuto = horaSalida.split(":");

			fechaSalida = fechaSalida + "-" + horaYMinuto[0] + "-"
					+ horaYMinuto[1];
			
			String[] horaYMinutoLLegada = horaLlegada.split(":");

			fechaLlegada = fechaLlegada + "-" + horaYMinutoLLegada[0] + "-"
					+ horaYMinutoLLegada[1];

			String[] coorOriString = coordenadasOrigen.split("&");
			double latOr = Double.parseDouble(coorOriString[0]);
			double lonOr = Double.parseDouble(coorOriString[1]);

			String[] coorDestString = coordenadasDestino.split("&");
			double latDest = Double.parseDouble(coorDestString[0]);
			double lonDest = Double.parseDouble(coorDestString[1]);

			if (coorDestString.length != 2 || coorOriString.length != 2) {

				latOr = 0;
				lonOr = 0;
				latDest = 0;
				lonDest = 0;

			}


			try {

				Trip nuevoViaje = PersistenceFactory.newTripDao().findById(
						(Long) Long.parseLong(request.getQueryString().split(
								"=")[1]));

				nuevoViaje.setArrivalDate(DateUtil.fromString(fechaLlegada));
				nuevoViaje.setClosingDate(DateUtil
						.fromString(fechaLimiteInscripcion));
				nuevoViaje.setDepartureDate(DateUtil.fromString(fechaSalida));
				nuevoViaje.setAvailablePax(Integer.parseInt(plazasDisponibles));
				nuevoViaje.setComments(comentarios);
				nuevoViaje.setMaxPax(Integer.parseInt(plazasMaximas));
				nuevoViaje.setEstimatedCost(Double.parseDouble(costeEstimado));
				nuevoViaje.setStatus(TripStatus.OPEN);
				nuevoViaje.setDeparture(new AddressPoint(calleOrigen,
						ciudadOrigen, provinciaOrigen, paisOrigen,
						codigoPostalOrigen, new Waypoint(latOr, lonOr)));
				nuevoViaje.setDestination(new AddressPoint(calleDestino,
						ciudadDestino, provinciaDestino, paisDestino,
						codigoDestino, new Waypoint(latDest, lonDest)));

				request.setAttribute("viaje", nuevoViaje);
				PersistenceFactory.newTripDao().update(nuevoViaje);

				Long id = ((User) request.getSession().getAttribute("user"))
						.getId();

				List<Trip> viajes = findTripsByUserSession(id);
				request.setAttribute("viajesOfertados", viajes);

				return "EXITO";

			} catch (NumberFormatException e) {

				return "FRACASO";
			}

		}

		request.setAttribute(
				"viaje",
				PersistenceFactory.newTripDao().findById(
						(Long) Long.parseLong(request.getQueryString().split(
								"=")[1])));
		return resultado;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	private List<Trip> findTripsByUserSession(Long userId) {

		List<Trip> trips = new ArrayList<Trip>();

		for (Trip trip : PersistenceFactory.newTripDao().findAll()) {

			if (trip.getPromoterId().equals(userId)
					&& !trip.getStatus().equals(TripStatus.CANCELLED)) {
				trips.add(trip);
			}

		}
		return trips;

	}

}
