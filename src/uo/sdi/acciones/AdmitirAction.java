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
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class AdmitirAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String[] queryString = request.getQueryString().split("&");

		if (queryString.length >= 2) {
			try {
				Long idUser = Long.parseLong(queryString[0].split("=")[1]);
				Long idTrip = Long.parseLong(queryString[1].split("=")[1]);

				SeatDao seatDao = PersistenceFactory.newSeatDao();

				Seat previousSeat = seatDao.findByUserAndTrip(idUser, idTrip);

				if (previousSeat != null) {

					previousSeat.setStatus(SeatStatus.ACCEPTED);
					seatDao.update(previousSeat);
					Log.debug("Admitido con exito");
					updateAvailablePax(idTrip);
					putDtoInRequest(request, idTrip);
					
					Log.debug("Admitido el usuario [%s] en el viaje [%s]", idUser, idTrip);
					
					return "EXITO";

				}

				Seat seat = new Seat();
				seat.setStatus(SeatStatus.ACCEPTED);
				seat.setTripId(idTrip);
				seat.setUserId(idUser);

				PersistenceFactory.newSeatDao().save(seat);
				updateAvailablePax(idTrip);
				putDtoInRequest(request, idTrip);
				
				Log.debug("Admitido el usuario [%s] en el viaje [%s]", idUser, idTrip);
				
				return "EXITO";
			} catch (NumberFormatException e) {
				Log.error("Se ha intentado admitir en un viaje a un usuario desconocido");
				return "FRACASO";
			}
		}

		Log.error("Se ha intentado admitir en un viaje a un usuario desconocido");
		
		return "FRACASO";
	}

	private void updateAvailablePax(Long idTrip) {
		TripDao tdao = PersistenceFactory.newTripDao();
		Trip trip = tdao.findById(idTrip);
		trip.setAvailablePax(trip.getAvailablePax() - 1);
		tdao.update(trip);
	}

	private void putDtoInRequest(HttpServletRequest request, Long idTrip) {
		SolicitudesDto solicitudes = DTOAssembler
				.generateSolicitudesDTO(idTrip);

		request.setAttribute("solicitudesDto", solicitudes);
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
