package uo.sdi.dao;

public class TripDAO {

	private Long id;
	private String origen;
	private String destino;
	private int plazasLibres;
	private String promotor;
	private int puntuacionPromotor;
	private String comentariosPromotor;
	private String participantes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TripDAO(long id, String origen, String destino, int plazasLibres,
			String promotor, int puntuacionPromotor,
			String comentariosPromotor, String participantes) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.plazasLibres = plazasLibres;
		this.promotor = promotor;
		this.puntuacionPromotor = puntuacionPromotor;
		this.comentariosPromotor = comentariosPromotor;
		this.participantes = participantes;
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

	public int getPuntuacionPromotor() {
		return puntuacionPromotor;
	}

	public void setPuntuacionPromotor(int puntuacionPromotor) {
		this.puntuacionPromotor = puntuacionPromotor;
	}

	public String getComentariosPromotor() {
		return comentariosPromotor;
	}

	public void setComentariosPromotor(String comentariosPromotor) {
		this.comentariosPromotor = comentariosPromotor;
	}

	public String getParticipantes() {
		return participantes;
	}

	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}

}
