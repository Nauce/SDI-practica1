package uo.sdi.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
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
		Map<User, SeatStatus> usersAndStatus = findUsersAndStatusSeatBySeat(trip.getId());
		// users.add(promotor);
		tdao.setInfoPasajeros(getInfoViaje(trip.getId(), usersAndStatus,
				promotor.getId()));
		tdao.setInfoPromotor(getRatingsPromotor(promotor));

		return tdao;

	}

	private static InfoViajeDto getRatingsPromotor(User promotor) {

		InfoViajeDto infodto = new InfoViajeDto();
		List<Rating> ratings = PersistenceFactory.newRatingDao().findAll();
		int contador = 0;
		double media = 0;
		for (Rating rating : ratings) {

			if (rating.getSeatAboutUserId().equals(promotor.getId())) {

				contador++;
				media += rating.getValue();
				infodto.getComentarios().add(rating.getComment());

			}

		}
		media = media / contador;
		infodto.setRating(media);
		infodto.setUsuario(promotor.getName());

		return infodto;

	}

	private static Map<String, InfoViajeDto> getInfoViaje(Long idViaje,
			Map<User, SeatStatus> usuarios, Long idPromotor) {

		Map<String, InfoViajeDto> usuarioComentario = new HashMap<String, InfoViajeDto>();
		List<Rating> ratings = PersistenceFactory.newRatingDao().findAll();

		for (User usuario : usuarios.keySet()) {
			int contador = 0;
			double media = 0;
			InfoViajeDto infodto = new InfoViajeDto();
			for (Rating rating : ratings) {

				if (rating.getSeatAboutUserId().equals(usuario.getId())
						&& !rating.getSeatAboutUserId().equals(idPromotor)) {

					contador++;
					media += rating.getValue();
					infodto.getComentarios().add(rating.getComment());

				}

			}
			media = media / contador;
			infodto.setRating(media);
			infodto.setUsuario(usuario.getName());
			infodto.setSeatStatus(usuarios.get(usuario));
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
	
	private static Map<User, SeatStatus> findUsersAndStatusSeatBySeat(Long id) {

		List<Seat> seats = PersistenceFactory.newSeatDao().findAll();
		Map<User, SeatStatus> map = new HashMap<User, SeatStatus>();

		for (Seat seat : seats) {

			if (seat.getTripId().equals(id)) {
				map.put(PersistenceFactory.newUserDao().findById(
						seat.getUserId()),
						seat.getStatus());
			}
		}

		return map;

	}

}
