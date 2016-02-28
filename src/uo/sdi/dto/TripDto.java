package uo.sdi.dto;

import java.util.HashMap;
import java.util.Map;

public class TripDto {

	private Long id;
	private String origen;
	private String destino;
	private int plazasLibres;

	private int plazasMaximas;
	private String promotor;
	private Map<String, InfoViajeDto> infoPasajeros;
	

	public TripDto(long id, String origen, String destino, int plazasLibres,
			String promotor) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.plazasLibres = plazasLibres;
		this.promotor = promotor;
		this.setInfoPasajeros(new HashMap<String, InfoViajeDto>());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPlazasMaximas() {
		return plazasMaximas;
	}

	public void setPlazasMaximas(int plazasMaximas) {
		this.plazasMaximas = plazasMaximas;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getPlazasLibres() {
		return plazasLibres;
	}

	public void setPlazasLibres(int plazasLibres) {
		this.plazasLibres = plazasLibres;
	}

	public String getPromotor() {
		return promotor;
	}

	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, InfoViajeDto> getInfoPasajeros() {
		return infoPasajeros;
	}

	public void setInfoPasajeros(Map<String, InfoViajeDto> infoPasajeros) {
		this.infoPasajeros = infoPasajeros;

	}

	

}
