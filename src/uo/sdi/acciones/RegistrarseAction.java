package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class RegistrarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String resultado = "EXITO";
		String nombreUsuario = request.getParameter("nombreUsuario");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String contrasenya = request.getParameter("contrasenya");
		String confirmaContrasenya = request.getParameter("confirmaContrasenya");
		
		if (contrasenya == null || !contrasenya.equals(confirmaContrasenya)) {
			resultado = "FRACASO";
			//request.setAttribute("error", "error");
			return resultado;
		}
		
		User usuario = new User();
		usuario.setLogin(nombreUsuario);
		usuario.setName(nombre);
		usuario.setSurname(apellidos);
		usuario.setEmail(email);
		usuario.setPassword(contrasenya);
		
		PersistenceFactory.newUserDao().save(usuario);
		
		return resultado;
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
}
