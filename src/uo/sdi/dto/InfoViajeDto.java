package uo.sdi.dto;

import java.util.ArrayList;
import java.util.List;

public class InfoViajeDto {

	private String usuario;

	private List<String> comentarios;

	private Double rating;
	
	public InfoViajeDto() {
		super();
		this.setComentarios(new  ArrayList<String>());
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}



}
