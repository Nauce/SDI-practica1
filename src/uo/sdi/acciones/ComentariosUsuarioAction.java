package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uo.sdi.dto.DTOAssembler;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ComentariosUsuarioAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String queryString = request.getQueryString();
			
			Long id = Long.parseLong(queryString.split("=")[1]);
			User user = PersistenceFactory.newUserDao().findById(id);
			request.setAttribute("comentariosUsuario",
					DTOAssembler.generateComentariosUsuarioDto(user));

		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo la lista de comentarios del usuario");
			return "ERROR";
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
