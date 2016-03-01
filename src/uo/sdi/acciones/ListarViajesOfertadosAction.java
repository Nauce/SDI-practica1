package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.dto.DTOAssembler;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.util.DateUtil;
import alb.util.log.Log;

public class ListarViajesOfertadosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Trip> viajes;

		try {

			Long id = ((User) request.getSession().getAttribute("user"))
					.getId();

			viajes = findTripsByUserSession(id);
			request.setAttribute("viajesOfertados", viajes);
			request.setAttribute("viajesImplicadoDTO",
					DTOAssembler.getViajesImplicadosDto(id));

			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());

		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
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

			if (trip.getPromoterId().equals(userId)
					&& !trip.getStatus().equals(TripStatus.CANCELLED)) {
				if (DateUtil.isBefore(trip.getArrivalDate(), new Date())) {
					trip.setStatus(TripStatus.DONE);
				}
				trips.add(trip);
			}

		}
		return trips;

	}
}
