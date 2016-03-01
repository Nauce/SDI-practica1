package uo.sdi.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.sdi.dto.ComentariosUsuarioDto.Comentario;
import uo.sdi.model.Application;
import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;

public class DTOAssembler {

	public static SolicitudesDto generateSolicitudesDTO(Long idTrip) {

		SolicitudesDto scdto = new SolicitudesDto(PersistenceFactory
				.newTripDao().findById(idTrip));

		Trip trip = PersistenceFactory.newTripDao().findById(idTrip);

		List<User> admitidos = getAdmitidos(idTrip, trip.getPromoterId());
		List<User> excluidos = getExcluidos(idTrip, trip.getPromoterId());
		List<User> pendientes = getPendientes(idTrip, trip.getPromoterId());

		scdto.setAdmitidos(admitidos);
		scdto.setExcluidos(excluidos);
		scdto.setPendientes(pendientes);

		return scdto;

	}

	public static ViajeImplicadoDto getViajesImplicadosDto(Long userId) {

		ViajeImplicadoDto vidto = new ViajeImplicadoDto();
		Seat seat;

		for (Application aplication : PersistenceFactory.newApplicationDao()
				.findByUserId(userId)) {

			seat = PersistenceFactory.newSeatDao().findByUserAndTrip(userId,
					aplication.getTripId());

			if (seat == null)
				vidto.getTrips().put(
						PersistenceFactory.newTripDao().findById(
								aplication.getTripId()), null);
			else
				vidto.getTrips().put(
						PersistenceFactory.newTripDao().findById(
								aplication.getTripId()), seat.getStatus());

		}

		return vidto;

	}

	private static List<User> getPendientes(Long tripId, Long long1) {

		List<User> pendientes = new ArrayList<User>();
		List<Application> applications = PersistenceFactory.newApplicationDao()
				.appNotInSeat(tripId);

		for (Application application : applications) {

			if (!application.getUserId().equals(long1))
				pendientes.add(PersistenceFactory.newUserDao().findById(
						application.getUserId()));

		}

		return pendientes;

	}

	private static List<User> getAdmitidos(Long idTrip, Long long1) {

		List<User> admitidos = new ArrayList<User>();
		List<Seat> seats = PersistenceFactory.newSeatDao().findAll();
		for (Seat seat : seats) {
			if (seat.getTripId().equals(idTrip)
					&& !seat.getTripId().equals(
							PersistenceFactory.newTripDao().findById(idTrip))
					&& seat.getStatus().equals(SeatStatus.ACCEPTED)
					&& !seat.getUserId().equals(long1)) {
				admitidos.add(PersistenceFactory.newUserDao().findById(
						seat.getUserId()));
			}
		}
		return admitidos;
	}

	private static List<User> getExcluidos(Long idTrip, Long long1) {

		List<User> admitidos = new ArrayList<User>();
		List<Seat> seats = PersistenceFactory.newSeatDao().findAll();
		for (Seat seat : seats) {
			if (seat.getTripId().equals(idTrip)
					&& !seat.getTripId().equals(
							PersistenceFactory.newTripDao().findById(idTrip))
					&& seat.getStatus().equals(SeatStatus.EXCLUDED)
					&& !seat.getUserId().equals(long1)) {
				admitidos.add(PersistenceFactory.newUserDao().findById(
						seat.getUserId()));
			}
		}
		return admitidos;
	}

	public static TripDto generateTripDto(Trip trip, User user) {

		User promotor = PersistenceFactory.newUserDao().findById(
				trip.getPromoterId());
		TripDto tdao = new TripDto(trip, user.getId());
		tdao.setPromotor(PersistenceFactory.newUserDao()
				.findById(trip.getPromoterId()).getName());
		Map<User, SeatStatus> usersAndStatus = findUsersAndStatusSeatBySeat(
				trip.getId(), promotor.getId());
		tdao.setInfoPasajeros(getInfoViaje(trip.getId(), usersAndStatus,
				promotor.getId()));
		tdao.setIdPromotor(promotor.getId());
		tdao.setInfoPromotor(getRatingsPromotor(promotor));

		return tdao;

	}

	public static ComentariosUsuarioDto generateComentariosUsuarioDto(User user) {
		List<Rating> ratings = PersistenceFactory.newRatingDao().findByUserId(
				user.getId());

		ComentariosUsuarioDto dto = new ComentariosUsuarioDto();
		dto.setUser(user);

		UserDao userDao = PersistenceFactory.newUserDao();
		TripDao tripDao = PersistenceFactory.newTripDao();

		User userComenta;
		Trip trip;

		for (Rating rating : ratings) {
			userComenta = userDao.findById(rating.getSeatFromUserId());
			trip = tripDao.findById(rating.getSeatAboutTripId());

			if (!dto.getComentarios().containsKey(userComenta))
				dto.getComentarios().put(userComenta,
						new ArrayList<Comentario>());

			dto.getComentarios()
					.get(userComenta)
					.add(new Comentario(trip, rating.getComment(), rating
							.getValue()));
		}

		return dto;
	}

	public static ComentarEnViajeDto generateComentarEnViajeDto(Long idTrip,
			Long idUser) {
		ComentarEnViajeDto dto = new ComentarEnViajeDto(PersistenceFactory
				.newTripDao().findById(idTrip));

		UserDao userDao = PersistenceFactory.newUserDao();
		RatingDao ratingDao = PersistenceFactory.newRatingDao();

		List<Seat> seats = PersistenceFactory.newSeatDao().findByTripId(idTrip);

		for (Seat seat : seats)
			if (seat.getStatus().equals(SeatStatus.ACCEPTED)
					&& ratingDao.findByAboutFrom(seat.getUserId(), idTrip,
							idUser, idTrip) == null)
				dto.getParticipantes().add(userDao.findById(seat.getUserId()));

		return dto;

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
		infodto.setIdUsuario(promotor.getId());
		infodto.setUsuario(promotor.getName() + " " + promotor.getSurname()
				+ " (" + promotor.getLogin() + ")");

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
			infodto.setIdUsuario(usuario.getId());
			infodto.setUsuario((usuario.getName() + " " + usuario.getSurname()
					+ " (" + usuario.getLogin() + ")"));
			infodto.setSeatStatus(usuarios.get(usuario));
			usuarioComentario.put(usuario.getName(), infodto);

		}
		return usuarioComentario;

	}

	private static Map<User, SeatStatus> findUsersAndStatusSeatBySeat(Long id,
			Long promoterID) {

		List<Seat> seats = PersistenceFactory.newSeatDao().findAll();
		Map<User, SeatStatus> map = new HashMap<User, SeatStatus>();

		for (Seat seat : seats) {

			if (seat.getTripId().equals(id)
					&& !seat.getUserId().equals(promoterID)
					&& seat.getStatus().equals((SeatStatus.ACCEPTED))) {
				map.put(PersistenceFactory.newUserDao().findById(
						seat.getUserId()), seat.getStatus());
			}
		}

		List<Application> applications = PersistenceFactory.newApplicationDao()
				.findAll();

		for (Application application : applications) {

			if (application.getTripId().equals(id)
					&& !application.getUserId().equals(promoterID)) {
				map.put(PersistenceFactory.newUserDao().findById(
						application.getUserId()), null);

			}

		}

		return map;

	}

}
