package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.util.DateUtil;
import alb.util.log.Log;

public class OrdenarViajesOfertados implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String respuesta = request.getParameter("filtrados");
		User user = (User) request.getSession().getAttribute("user");
		TripDao tdao = PersistenceFactory.newTripDao();
		switch (respuesta) {
		case "Fecha Ascendente":

			request.setAttribute("viajesOfertados",
					filterTrips(tdao.dateAscendingPromoter(user.getId())));
			break;

		case "Fecha Descendente":

			request.setAttribute("viajesOfertados",
					filterTrips(tdao.dateDescendingPromoter(user.getId())));
			break;

		case "Destino Descendente":
			request.setAttribute("viajesOfertados",
					filterTrips(tdao.destinoDescendingPromoter(user.getId())));
			break;

		case "Destino Ascendente":
			request.setAttribute("viajesOfertados",
					filterTrips(tdao.destinoAscendingPromoter(user.getId())));
			break;
		default:
			request.setAttribute("viajesOfertados",
					filterTrips(tdao.dateDescendingPromoter(user.getId())));
			return "Fracaso";

		}
		Log.debug("Filtrado correctamente");
		return "EXITO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	private List<Trip> filterTrips(List<Trip> tripsFiltered) {

		List<Trip> trips = new ArrayList<Trip>();

		for (Trip trip : tripsFiltered) {

			if (DateUtil.isBefore(trip.getArrivalDate(), new Date())) {
				trip.setStatus(TripStatus.DONE);
			}
			trips.add(trip);

		}
		return trips;

	}

}
