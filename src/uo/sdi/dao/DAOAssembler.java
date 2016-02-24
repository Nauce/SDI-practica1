package uo.sdi.dao;

import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.TripDao;

public class DAOAssembler {

	public static TripDAO generateTripDao(Trip trip, User user) {

		TripDAO tdao = new TripDAO(trip.getId(), trip.getDeparture().getCity(),
				trip.getDestination().getCity(), trip.getAvailablePax(),
				user.getName(), 10/* ^puntuacion */, "comentariosPromotor",
				"participantes");
		
		return tdao;

	}

}
