package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
