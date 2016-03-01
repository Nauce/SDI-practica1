package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class OrdenarViajesPublicosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String respuesta = request.getParameter("filtrados");

		TripDao tdao = PersistenceFactory.newTripDao();
		Date date = new Date();
		switch (respuesta) {
		case "Fecha Ascendente":

			request.setAttribute("listaViajes", tdao.dateAscending(date));
			break;

		case "Fecha Descendente":

			request.setAttribute("listaViajes", tdao.dateDescending(date));
			break;

		case "Destino Descendente":
			request.setAttribute("listaViajes", tdao.destinoDescending(date));
			break;

		case "Destino Ascendente":
			request.setAttribute("listaViajes", tdao.dateAscending(date));
			break;
		default:
			request.setAttribute("listaViajes", tdao.dateDescending(date));
			return "Fracaso";

		}
		Log.debug("Filtrado correctamente");
		return "EXITO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
