package uo.sdi.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class DTOAssembler {

	public static TripDto generateTripDto(Trip trip, User user) {

		User promotor = PersistenceFactory.newUserDao().findById(
				trip.getPromoterId());
		TripDto tdao = new TripDto(trip.getId(), trip.getDeparture().getCity(),
				trip.getDestination().getCity(), trip.getAvailablePax(),
				promotor.getName());
		tdao.setPlazasMaximas(trip.getMaxPax());

		List<User> users = findUserBySeat(trip.getId());
		users.add(promotor);
		tdao.setInfoPasajeros(getInfoViaje(trip.getId(), users));

		return tdao;

	}

	private static Map<String, Double> getInfoViaje(Long idViaje,
			List<User> usuarios) {

		Map<String, Double> usuarioComentario = new HashMap<String, Double>();
		List<Rating> ratings = PersistenceFactory.newRatingDao().findAll();

		for (User usuario : usuarios) {
			int contador = 0;
			double media = 0;

			for (Rating rating : ratings) {

				if (rating.getSeatAboutUserId().equals(usuario.getId())) {

					contador++;
					media += rating.getValue();

				}

			}
			media = media / contador;

			usuarioComentario.put(usuario.getName(), media);

		}
		return usuarioComentario;

	}

	private static List<User> findUserBySeat(Long id) {

		List<Seat> seats = PersistenceFactory.newSeatDao().findAll();
		List<User> userstoReturn = new ArrayList<User>();

		for (Seat seat : seats) {

			if (seat.getTripId().equals(id)) {
				userstoReturn.add(PersistenceFactory.newUserDao().findById(
						seat.getUserId()));
			}
		}

		return userstoReturn;

	}

}
