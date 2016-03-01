package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

public class CerrarSesionAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		if (session == null) {
			Log.error("No se ha podido finalizar la sesión");

			return "FRACASO";
		}
		session.invalidate();
		
		request.setAttribute("logout", true);

		Log.debug("Sesión finalizada con éxito");
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
