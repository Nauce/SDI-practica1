package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.persistence.PersistenceFactory;

public class AdmitirAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String[] queryString = request.getQueryString().split("&");

		if (queryString.length > 2) {
			try {
				Long idUser = Long.parseLong(queryString[0].split("=")[1]);
				Long idTrip = Long.parseLong(queryString[1].split("=")[1]);

				Seat seat = new Seat();
				seat.setStatus(SeatStatus.ACCEPTED);
				seat.setTripId(idTrip);
				seat.setUserId(idUser);

				PersistenceFactory.newSeatDao().save(seat);
				return "EXITO";
			} catch (NumberFormatException e) {

				return "FRACASO";
			}
		}
		return "FRACASO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
