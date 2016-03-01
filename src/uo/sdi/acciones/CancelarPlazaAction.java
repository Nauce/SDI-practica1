package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.dto.DTOAssembler;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class CancelarPlazaAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

			try {
				String queryString = request.getQueryString();
				
				Long idTrip = Long.parseLong(queryString.split("=")[1]);
				Long idUser = ((User)request.getSession().getAttribute("user")).getId();
				
				Seat seatPrevious = PersistenceFactory.newSeatDao().findById(new Long[] {idUser, idTrip});
				
				if (seatPrevious != null) {
					seatPrevious.setStatus(SeatStatus.EXCLUDED);
					PersistenceFactory.newSeatDao().update(seatPrevious);
					updateAvailablePax(idTrip, 1);

					Log.debug("El usuario [%s] ha cancelado su plaza en el viaje [%s]",
							idUser, idTrip);

					request.setAttribute("cancelarPlazaAction", "");
					request.setAttribute("viajesImplicadosDto",
							DTOAssembler.getViajesImplicadosDto(((User)request.getSession().getAttribute("user")).getId()));
					
					return "EXITO";
				}
				
				Seat seat = new Seat();
				seat.setStatus(SeatStatus.EXCLUDED);
				seat.setTripId(idTrip);
				seat.setUserId(idUser);

				PersistenceFactory.newSeatDao().save(seat);

				Log.debug("El usuario [%s] ha cancelado su plaza en el viaje [%s]",
						idUser, idTrip);

				request.setAttribute("cancelarPlazaAction", "");
				request.setAttribute("viajesImplicadosDto",
						DTOAssembler.getViajesImplicadosDto(((User)request.getSession().getAttribute("user")).getId()));
				
				return "EXITO";
			} catch (NumberFormatException e) {

				Log.error("El usuario no ha podido cancelar su plaza en el viaje");
				return "FRACASO";
			}

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
