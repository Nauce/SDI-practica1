package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class BorrarViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Trip> viajes;

		try {

			Long id = Long.parseLong(request.getQueryString().split("=")[1]);
			TripDao tdao = PersistenceFactory.newTripDao();
			Trip trip = PersistenceFactory.newTripDao().findById(id);
			trip.setStatus(TripStatus.CANCELLED);
			tdao.update(trip);
			Long idUser = ((User) request.getSession().getAttribute("user"))
					.getId();

			viajes = findTripsByUserSession(idUser);
			request.setAttribute("viajesOfertados", viajes);

		} catch (Exception e) {
			Log.error("Error borrando el viaje");
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	private List<Trip> findTripsByUserSession(Long userId) {

		List<Trip> trips = new ArrayList<Trip>();

		for (Trip trip : PersistenceFactory.newTripDao().findAll()) {

			if (trip.getPromoterId().equals(userId) && !trip.getStatus().equals(TripStatus.CANCELLED)) {
				trips.add(trip);
			}

		}
		return trips;

	}
}
