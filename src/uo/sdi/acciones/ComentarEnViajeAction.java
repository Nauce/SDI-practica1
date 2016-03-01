package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.dto.DTOAssembler;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ComentarEnViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

	
			try {
				String queryString = request.getQueryString();
				
				Long idTrip = Long.parseLong(queryString.split("=")[1]);
				Long idUser = ((User) request.getSession().getAttribute("user")).getId();

				request.setAttribute("dto", DTOAssembler.generateComentarEnViajeDto(idTrip, idUser));
				
				return "EXITO";
			} catch (NumberFormatException e) {

				Log.error("No se han leído los usuarios del viaje");
				return "FRACASO";

			}

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
