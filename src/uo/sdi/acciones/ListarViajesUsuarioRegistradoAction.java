package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.dto.DTOAssembler;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ListarViajesUsuarioRegistradoAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String queryString = request.getQueryString();

			Long id = Long.parseLong(queryString.split("=")[1]);
			
			User user = (User) request.getSession().getAttribute("user");

			Trip trip = PersistenceFactory.newTripDao().findById(id);
			request.setAttribute("viaje",
					DTOAssembler.generateTripDto(trip, user));

		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
			return "ERROR";
		}
		Log.debug("Viaje por usuario listados con éxito");
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
