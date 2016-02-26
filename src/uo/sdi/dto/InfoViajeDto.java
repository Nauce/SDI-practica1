package uo.sdi.dto;

public class InfoViajeDto {

	public InfoViajeDto(String usuario, String comentario, String rating) {
		super();
		this.usuario = usuario;
		this.comentario = comentario;
		this.rating = rating;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	private String usuario;

	private String comentario;

	private String rating;

}
