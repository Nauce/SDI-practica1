package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CerrarSesionAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		if (session == null) {
			return "FRACASO";
		}
		session.invalidate();
		
		request.setAttribute("logout", true);

		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
