package uo.sdi.dto;

import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.TripDao;

public class DATOAssembler {

	public static TripDto generateTripDto(Trip trip, User user) {

		TripDto tdao = new TripDto(trip.getId(), trip.getDeparture().getCity(),
				trip.getDestination().getCity(), trip.getAvailablePax(),
				user.getName(), 10/* ^puntuacion */, "comentariosPromotor",
				"participantes");
		
		return tdao;

	}

}
