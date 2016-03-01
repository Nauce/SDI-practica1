package uo.sdi.acciones;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ListarViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Trip> viajes;

		try {

			viajes = PersistenceFactory.newTripDao().findTravelsBefore(new Date());
			request.setAttribute("listaViajes", viajes);
			
			

		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		Log.debug("Lista de viajes creada con éxito");
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
