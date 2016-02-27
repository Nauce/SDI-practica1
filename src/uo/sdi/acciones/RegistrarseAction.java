package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.PersistenceFactory;

public class RegistrarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado = "EXITO";
		
		if (request.getSession().getAttribute("user") != null) {
			Log.info("El usuario [%s] ha intentado acceder a la página de registrarse como usuario."
					+ " Fue redirigido a la página principal",
					((User) request.getSession().getAttribute("user")).getLogin());
			return resultado;
		}
		
		StringBuilder sb = new StringBuilder();
		String nombreUsuario = request.getParameter("nombreUsuario");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String contrasenya = request.getParameter("contrasenya");
		String confirmaContrasenya = request.getParameter("confirmaContrasenya");
		
		User userByLogin = PersistenceFactory.newUserDao().findByLogin(nombreUsuario);
		
		if (nombreUsuario != null && userByLogin != null) {
			sb.append("El usuario con nombre de usuario ")
				.append(userByLogin.getLogin()).append(" ya se encuentra registrado en la base de datos<br><br>");
			resultado = "FRACASO";
		}
		
		if (nombreUsuario == null || nombre == null || apellidos == null || email == null || contrasenya == null
				|| nombreUsuario.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || contrasenya.isEmpty()) {
			sb.append("Ninguno de los campos puede ser vacío<br><br>");
			resultado = "FRACASO";
		}
	
		else if (confirmaContrasenya != null && !contrasenya.isEmpty() && !contrasenya.equals(confirmaContrasenya)) {
			sb.append("Las contraseñas introducidas no coinciden<br><br>");
			resultado = "FRACASO";
		}
		
		if (resultado.equals("FRACASO")) {
			sb.insert(0, "Errores en el formulario:<br><br>");
			sb.replace(sb.length() - 8, sb.length(), "");
			request.setAttribute("errores", sb.toString());
			request.setAttribute("nombreUsuario", nombreUsuario);
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellidos", apellidos);
			request.setAttribute("email", email);
			return resultado;
		}
	
		User usuario = new User();
		usuario.setLogin(nombreUsuario);
		usuario.setName(nombre);
		usuario.setSurname(apellidos);
		usuario.setEmail(email);
		usuario.setPassword(contrasenya);
		usuario.setStatus(UserStatus.ACTIVE);
		
		PersistenceFactory.newUserDao().save(usuario);
		
		request.getSession().setAttribute("user", usuario);
		request.getSession().setAttribute("registrarseAction", usuario.getLogin());
		Log.info("El usuario [%s] ha sido dado de alta en el sistema", usuario.getLogin());
		
		return resultado;
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
}
