package uo.sdi.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class ComentariosUsuarioDto {
	
	private User user;
	private Map<User, List<Comentario>> comentarios = new HashMap<User, List<Comentario>>();

	static class Comentario {
		private Long tripId;
		private String departure;
		private String destination;
		private Date departureDate;
		private String comment;
		private double valoracion;
		
		public Comentario(Trip trip, String comment, double valoracion) {
			this.tripId = trip.getId();
			this.departure = trip.getDeparture().getCity();
			this.departureDate = trip.getDepartureDate();
			this.destination = trip.getDestination().getCity();
			this.comment = comment;
			this.valoracion = valoracion;
		}

		public Long getTripId() {
			return tripId;
		}

		public String getDeparture() {
			return departure;
		}

		public String getDestination() {
			return destination;
		}

		public Date getDepartureDate() {
			return departureDate;
		}

		public String getComment() {
			return comment;
		}

		public double getValoracion() {
			return valoracion;
		}
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<User, List<Comentario>> getComentarios() {
		return comentarios;
	}
	
}
