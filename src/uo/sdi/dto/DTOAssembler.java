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
		TripDto tdao = new TripDto(trip);
		tdao.setPromotor(PersistenceFactory.newUserDao()
				.findById(trip.getPromoterId()).getName());
		List<User> users = findUserBySeat(trip.getId());
		users.add(promotor);
		tdao.setInfoPasajeros(getInfoViaje(trip.getId(), users));

		return tdao;

	}

	private static Map<String, InfoViajeDto> getInfoViaje(Long idViaje,
			List<User> usuarios) {

		Map<String, InfoViajeDto> usuarioComentario = new HashMap<String, InfoViajeDto>();
		List<Rating> ratings = PersistenceFactory.newRatingDao().findAll();

		for (User usuario : usuarios) {
			int contador = 0;
			double media = 0;
			InfoViajeDto infodto = new InfoViajeDto();
			for (Rating rating : ratings) {

				if (rating.getSeatAboutUserId().equals(usuario.getId())) {

					contador++;
					media += rating.getValue();
					infodto.getComentarios().add(rating.getComment());

				}

			}
			media = media / contador;
			infodto.setRating(media);
			infodto.setUsuario(usuario.getName());
			usuarioComentario.put(usuario.getName(), infodto);

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
