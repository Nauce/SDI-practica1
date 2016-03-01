package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.dto.DTOAssembler;
import uo.sdi.model.Application;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class IncluirEnInteresadosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String[] queryString = request.getQueryString().split("=");

		if (queryString.length == 2) {
			try {

				Long idTrip = Long.parseLong(queryString[1]);

				Application application = new Application();
				application.setTripId(idTrip);
				User user = (User) request.getSession().getAttribute("user");
				application.setUserId(user.getId());
				PersistenceFactory.newApplicationDao().save(application);

				Trip trip = PersistenceFactory.newTripDao().findById(idTrip);
				request.setAttribute("viaje",
						DTOAssembler.generateTripDto(trip, user));

				request.setAttribute("incluirEnInteresadosAction", "j");
				Log.debug("Incluido en interesados el usuario [%s]",
						user.getName());

				return "EXITO";
			} catch (NumberFormatException e) {

				Log.debug("No se ha podido incluir en interesados al usuario");
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
