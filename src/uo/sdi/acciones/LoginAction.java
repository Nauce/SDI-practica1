package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class LoginAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String resultado = "EXITO";
		String nombreUsuario = request.getParameter("nombreUsuario");
		String contrasenya = request.getParameter("contrasenya");
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			UserDao dao = PersistenceFactory.newUserDao();
			User userByLogin = dao.findByLogin(nombreUsuario);
			if (userByLogin != null
					&& userByLogin.getPassword().equals(contrasenya)) {
				session.setAttribute("user", userByLogin);
				int contador = Integer.parseInt((String) request
						.getServletContext().getAttribute("contador"));
				request.getServletContext().setAttribute("contador",
						String.valueOf(contador + 1));
				Log.info("El usuario [%s] ha iniciado sesión", nombreUsuario);
			} else {
				session.invalidate();
				Log.info("El usuario [%s] no está registrado", nombreUsuario);
				resultado = "FRACASO";
				request.setAttribute("error", "error");
			}
		} 
		else {
			Log.info("El usuario [%s] ha intentado acceder a la página de inicio de sesión."
					+ " Fue redirigido a la p�gina principal",
					((User) session.getAttribute("user")).getLogin());
			resultado = "FRACASO";
		}
		return resultado;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
