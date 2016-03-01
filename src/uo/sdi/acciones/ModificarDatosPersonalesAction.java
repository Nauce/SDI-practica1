package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class ModificarDatosPersonalesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String nuevoNombre = request.getParameter("nombre");
		String nuevoApellidos = request.getParameter("apellidos");
		String nuevoEmail = request.getParameter("email");
		String contrasenyaActual = request.getParameter("contrasenyaActual");
		String nuevoContrasenya = request.getParameter("contrasenyaNueva");
		String nuevoConfirmaContrasenya = request.getParameter("confirmaContrasenyaNueva");
		String errores = "<strong>Tus datos personales no han sido modificados<br><br></strong>";
		
		User user = (User) request.getSession().getAttribute("user");
		
		if (contrasenyaActual == null || !user.getPassword().equals(contrasenyaActual)) {
			Log.info("El usuario [%s] ha intentando modificar sus datos personales introduciendo mal su contrase�a",
					user.getLogin());
			errores += "Contraseña actual errónea";
			request.setAttribute("errores", errores);
			return "FRACASO";
		}
			
		else if (nuevoContrasenya == null
				|| nuevoContrasenya.isEmpty()
				|| !nuevoContrasenya.equals(nuevoConfirmaContrasenya)) {
			Log.info("El usuario [%s] ha intentando modificar sus datos personales"
					+ " no coincidiendo las contrase�as nuevas", user.getLogin());
			errores += "Las contraseñas nuevas no coinciden";
			request.setAttribute("errores", errores);
			return "FRACASO";
		}
		
		user.setName(nuevoNombre);
		user.setSurname(nuevoApellidos);
		user.setEmail(nuevoEmail);
		user.setPassword(nuevoContrasenya);
		try {
			UserDao dao = PersistenceFactory.newUserDao();
			dao.update(user);
			Log.debug("Modificado nombre de [%s] con el valor [%s]", user.getLogin(), nuevoNombre);
			Log.debug("Modificado apellidos de [%s] con el valor [%s]", user.getLogin(), nuevoApellidos);
			Log.debug("Modificado email de [%s] con el valor [%s]", user.getLogin(), nuevoEmail);
			Log.debug("Modificado contraseña de [%s] con el valor [%s]", user.getLogin(), nuevoContrasenya);
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido actualizando el usuario [%s]", user.getLogin());
		}
		
		request.setAttribute("EXITO", "EXITO");
		
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
