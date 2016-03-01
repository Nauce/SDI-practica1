package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.dto.DTOAssembler;
import uo.sdi.model.User;
import alb.util.log.Log;

public class MisViajesImplicadosAction implements Accion {
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			request.setAttribute("viajesImplicadosDto",
					DTOAssembler.getViajesImplicadosDto(((User)request.getSession().getAttribute("user")).getId()));
		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo la lista de viajes implicados");
			return "FRACASO";
		}
		Log.debug("Lista de viajes implicados obtenida correctamente");
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
