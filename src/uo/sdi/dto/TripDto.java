package uo.sdi.dto;

import java.util.HashMap;
import java.util.Map;

import uo.sdi.model.Trip;

public class TripDto {

	private Trip trip;
	private Map<String, InfoViajeDto> infoPasajeros;
	private String promotor;
	private InfoViajeDto infoPromotor;

	public TripDto(Trip trip) {
		super();
		this.setTrip(trip);
		this.setInfoPasajeros(new HashMap<String, InfoViajeDto>());
	

		
	}

	

	public Map<String, InfoViajeDto> getInfoPasajeros() {
		return infoPasajeros;
	}

	public void setInfoPasajeros(Map<String, InfoViajeDto> infoPasajeros) {
		this.infoPasajeros = infoPasajeros;

	}



	public Trip getTrip() {
		return trip;
	}



	public void setTrip(Trip trip) {
		this.trip = trip;
	}




	public String getPromotor() {
		return promotor;
}
	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}



	public InfoViajeDto getInfoPromotor() {
		return infoPromotor;
	}



	public void setInfoPromotor(InfoViajeDto infoPromotor) {
		this.infoPromotor = infoPromotor;
	}



}
