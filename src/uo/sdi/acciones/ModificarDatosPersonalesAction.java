package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String nuevoContrasenya = request.getParameter("contrasenya");
		String ConfirmaContrasenya = request.getParameter("confirmaContrasenya");
		
		if (nuevoContrasenya == null
				|| nuevoContrasenya.isEmpty()
				|| !nuevoContrasenya.equals(ConfirmaContrasenya))
			return "FRACASO";
		
		HttpSession session=request.getSession();
		User usuario=((User)session.getAttribute("user"));
		usuario.setName(nuevoNombre);
		usuario.setSurname(nuevoApellidos);
		usuario.setEmail(nuevoEmail);
		usuario.setPassword(nuevoContrasenya);
		try {
			UserDao dao = PersistenceFactory.newUserDao();
			dao.update(usuario);
			Log.debug("Modificado nombre de [%s] con el valor [%s]", usuario.getLogin(), nuevoNombre);
			Log.debug("Modificado apellidos de [%s] con el valor [%s]", usuario.getLogin(), nuevoApellidos);
			Log.debug("Modificado email de [%s] con el valor [%s]", usuario.getLogin(), nuevoEmail);
			Log.debug("Modificado contraseña de [%s] con el valor [%s]", usuario.getLogin(), nuevoContrasenya);
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido actualizando el usuario [%s]", usuario.getLogin());
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
