package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uo.sdi.dto.DTOAssembler;
import uo.sdi.dto.SolicitudesDto;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class PasarAPendientesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String[] queryString = request.getQueryString().split("&");

		if (queryString.length >= 2) {
			try {
				Long idUser = Long.parseLong(queryString[0].split("=")[1]);
				Long idTrip = Long.parseLong(queryString[1].split("=")[1]);

				SeatDao sdao = PersistenceFactory.newSeatDao();

				Seat seat = sdao.findByUserAndTrip(idUser, idTrip);

				if (seat != null) {

					sdao.delete(new Long[] { idUser, idTrip });
					if (seat.getStatus().equals(SeatStatus.ACCEPTED)) {

						updateAvailablePax(idTrip, +1);

					}
					putDtoInRequest(request, idTrip);

				} else {

					Log.debug("No hay ese asiento tomado");

				}

			} catch (NumberFormatException e) {
			}
		}

		return "EXITO";
	}

	private void putDtoInRequest(HttpServletRequest request, Long idTrip) {
		SolicitudesDto solicitudes = DTOAssembler
				.generateSolicitudesDTO(idTrip);

		request.setAttribute("solicitudesDto", solicitudes);
	}

	private void updateAvailablePax(Long idTrip, int num) {
		TripDao tdao = PersistenceFactory.newTripDao();
		Trip trip = tdao.findById(idTrip);
		trip.setAvailablePax(trip.getAvailablePax() + num);
		tdao.update(trip);
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
