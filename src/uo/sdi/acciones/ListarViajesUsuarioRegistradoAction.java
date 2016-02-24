package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.dao.DAOAssembler;
import uo.sdi.dao.TripDAO;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class ListarViajesUsuarioRegistradoAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Trip> viajes;
		List<TripDAO> tripDao;

		try {
			viajes = PersistenceFactory.newTripDao().findAll();
			tripDao = new ArrayList<TripDAO>();
			for (Trip trip : viajes) {

				tripDao.add(DAOAssembler.generateTripDao(
						trip,
						PersistenceFactory.newUserDao().findById(
								trip.getPromoterId())));

			}

			HttpSession session = request.getSession();
			request.setAttribute("listaViajesUsuario", tripDao);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());
		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
