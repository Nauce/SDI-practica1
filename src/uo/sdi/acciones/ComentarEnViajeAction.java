package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.dto.DTOAssembler;
import alb.util.log.Log;

public class ComentarEnViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String[] queryString = request.getQueryString().split("&");

		if (queryString.length >= 2) {
			try {
				Long idTrip = Long.parseLong(queryString[0].split("=")[1]);
				Long idUser = Long.parseLong(queryString[1].split("=")[1]);

				request.setAttribute("dto", DTOAssembler.generateComentarEnViajeDto(idTrip, idUser));
				
				return "EXITO";
			} catch (NumberFormatException e) {

				Log.error("No se han leído los usuarios del viaje");
				return "FRACASO";

			}
		}
		return "FRACASO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
