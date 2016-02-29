package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.dto.DTOAssembler;
import uo.sdi.dto.SolicitudesDto;
import alb.util.log.Log;

public class SolicitudesViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String[] queryString = request.getQueryString().split("=");

		if (queryString.length == 2) {
			try {

				Long idTrip = Long.parseLong(queryString[1]);

				SolicitudesDto solicitudes = DTOAssembler.generateSolicitudesDTO(idTrip);

				request.setAttribute("solicitudesDto", solicitudes);
			} catch (NumberFormatException e) {

				Log.debug("No se ha podido incluir en interesados el usuario");
				return "FRACASO";
			}
		}
		
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
