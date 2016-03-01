package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.dto.DTOAssembler;
import uo.sdi.dto.SolicitudesDto;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class ExcluirAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String[] queryString = request.getQueryString().split("&");

		if (queryString.length >= 2) {
			try {
				Long idUser = Long.parseLong(queryString[0].split("=")[1]);
				Long idTrip = Long.parseLong(queryString[1].split("=")[1]);
				
				Seat seatPrevious = PersistenceFactory.newSeatDao().findById(new Long[] {idUser, idTrip});
				
				if (seatPrevious != null) {
					seatPrevious.setStatus(SeatStatus.EXCLUDED);
					PersistenceFactory.newSeatDao().update(seatPrevious);
					updateAvailablePax(idTrip, 1);
					putDtoInRequest(request, idTrip);

					Log.debug("Usuario [%s] excluido del viaje [%s]", idUser, idTrip);
					
					return "EXITO";
				}
				
				Seat seat = new Seat();
				seat.setStatus(SeatStatus.EXCLUDED);
				seat.setTripId(idTrip);
				seat.setUserId(idUser);

				PersistenceFactory.newSeatDao().save(seat);
				putDtoInRequest(request, idTrip);

				Log.debug("Usuario [%s] excluido del viaje [%s]", idUser, idTrip);

				return "EXITO";
			} catch (NumberFormatException e) {

				Log.error("No se ha poder excluir al usuario");

				return "FRACASO";
			}
		}
		Log.error("No se ha podido excluir al usuario");

		return "FRACASO";

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
