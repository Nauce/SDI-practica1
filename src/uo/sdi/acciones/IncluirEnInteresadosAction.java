package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Application;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class IncluirEnInteresadosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String[] queryString = request.getQueryString().split("=");

		if (queryString.length > 2) {
			try {

				Long idTrip = Long.parseLong(queryString[1]);

				Application application = new Application();
				application.setTripId(idTrip);
				User user = (User) request.getSession().getAttribute("user");
				application.setUserId(user.getId());

				PersistenceFactory.newApplicationDao().save(application);
				Log.debug("Incluido en interesados " + user.getName());
				return "EXITO";
			} catch (NumberFormatException e) {

				Log.debug("No se ha podido incluir en interesados el usuario");
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
