package uo.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.acciones.*;

public class Controlador extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, Accion>> mapaDeAcciones; // <rol, <opcion,
																// objeto
																// Accion>>
	private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion; // <rol,
																			// <opcion,
																			// <resultado,
																			// JSP>>>

	public void init() throws ServletException {
		crearMapaAcciones();
		crearMapaDeJSP();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		String opcion, resultado, jspSiguiente;
		Accion accion;
		String rolAntes, rolDespues;

		try {
			opcion = req.getServletPath().replace("/", "");

			rolAntes = obtenerRolDeSesion(req);

			accion = buscarAccionParaOpcion(rolAntes, opcion);

			resultado = accion.execute(req, res);

			rolDespues = obtenerRolDeSesion(req);

			jspSiguiente = buscarJSPSegun(rolDespues, opcion, resultado);

			req.setAttribute("jspSiguiente", jspSiguiente);

		} catch (Exception e) {

			req.getSession().invalidate();

			Log.error("Se ha producido alguna excepción no manejada [%s]", e);

			jspSiguiente = "/login.jsp";
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jspSiguiente);

		dispatcher.forward(req, res);
	}

	private String obtenerRolDeSesion(HttpServletRequest req) {
		HttpSession sesion = req.getSession();
		if (sesion.getAttribute("user") == null)
			return "PUBLICO";
		else
			return "REGISTRADO";
	}

	// Obtiene un objeto accion en funci�n de la opci�n
	// enviada desde el navegador
	private Accion buscarAccionParaOpcion(String rol, String opcion) {

		Accion accion = mapaDeAcciones.get(rol).get(opcion);
		Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]", accion,
				opcion, rol);
		return accion;
	}

	// Obtiene la p�gina JSP a la que habr� que entregar el
	// control el funci�n de la opci�n enviada desde el navegador
	// y el resultado de la ejecuci�n de la acci�n asociada
	private String buscarJSPSegun(String rol, String opcion, String resultado) {

		String jspSiguiente = mapaDeNavegacion.get(rol).get(opcion)
				.get(resultado);
		Log.debug(
				"Elegida página siguiente [%s] para el resultado [%s] tras realizar [%s] con rol [%s]",
				jspSiguiente, resultado, opcion, rol);
		return jspSiguiente;
	}

	private void crearMapaAcciones() {

		mapaDeAcciones = new HashMap<String, Map<String, Accion>>();

		Map<String, Accion> mapaPublico = new HashMap<String, Accion>();
		mapaPublico.put("login", new LoginAction());
		mapaPublico.put("registrarse", new RegistrarseAction());
		mapaPublico.put("listarViajes", new ListarViajesAction());
		mapaPublico.put("listarViajesUsuario",
				new ListarViajesUsuarioRegistradoAction());

		mapaDeAcciones.put("PUBLICO", mapaPublico);

		Map<String, Accion> mapaRegistrado = new HashMap<String, Accion>();
		mapaRegistrado.put("modificarDatos", new ModificarDatosAction());
		mapaRegistrado.put("modificarDatosPersonales", new ModificarDatosPersonalesAction());
		mapaRegistrado.put("registrarViaje", new RegistrarViajeAction());
<<<<<<< HEAD
		mapaRegistrado.put("listarViajes", new ListarViajesAction());
		mapaRegistrado.put("listarViajesUsuario",
				new ListarViajesUsuarioRegistradoAction());
		//REVISAR: necesario para redirigir al usuario registrado a la principal
		//cuando intenta acceder a login de nuevo
		mapaRegistrado.put("login", new LoginAction());
		//REVISAR: necesario para redirigir al usuario registrado a la principal
		//cuando intenta acceder a login de nuevo
		mapaRegistrado.put("registrarse", new RegistrarseAction());
=======

		mapaRegistrado.put("listarViajes", new ListarViajesAction());

		mapaRegistrado.put("listarViajesOfertados",
				new ListarViajesOfertadosAction());

		mapaRegistrado.put("borrarViaje", new BorrarViajeAction());

		mapaRegistrado.put("listarViajesUsuario",
				new ListarViajesUsuarioRegistradoAction());

		mapaRegistrado.put("modificarViaje", new ModificarViajeAction());
>>>>>>> fc1df2ca27262cccc8b21050a22ba66548958912

		mapaDeAcciones.put("REGISTRADO", mapaRegistrado);
	}

	private void crearMapaDeJSP() {

		mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

		// Crear mapas auxiliares vacíos
		Map<String, Map<String, String>> opcionResJSP = new HashMap<String, Map<String, String>>();
		Map<String, String> resJSP = new HashMap<String, String>();

		// Mapa de navegación del público
		resJSP.put("FRACASO", "/login.jsp");
		opcionResJSP.put("login", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/registrarse.jsp");
		opcionResJSP.put("registrarse", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajes.jsp");
		opcionResJSP.put("listarViajes", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajesUsuario.jsp");
		opcionResJSP.put("listarViajesUsuario", resJSP);

		mapaDeNavegacion.put("PUBLICO", opcionResJSP);

		// Crear mapas auxiliares vacíos
		opcionResJSP = new HashMap<String, Map<String, String>>();
		resJSP = new HashMap<String, String>();

		// Mapa de navegación de usuarios registrados
		resJSP.put("EXITO", "/principal.jsp");
		opcionResJSP.put("login", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/login.jsp");
		opcionResJSP.put("registrarse", resJSP);
		
		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/modificarDatosPersonales.jsp");
		opcionResJSP.put("modificarDatosPersonales", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/principal.jsp");
		opcionResJSP.put("modificarDatos", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/misViajesOfertados.jsp");
		opcionResJSP.put("listarViajesOfertados", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/modificarViaje.jsp");
		opcionResJSP.put("modificarViaje", resJSP);

		resJSP.put("EXITO", "/misViajesOfertados.jsp");
		opcionResJSP.put("modificarViaje", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajesUsuario.jsp");
		opcionResJSP.put("listarViajesUsuario", resJSP);
<<<<<<< HEAD
		
		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/registrarViaje.jsp");
		opcionResJSP.put("registrarViaje", resJSP);
		
=======

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/misViajesOfertados.jsp");
		opcionResJSP.put("borrarViaje", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/registrarViaje.jsp");
		opcionResJSP.put("registrarViaje", resJSP);

		resJSP.put("EXITO", "/principal.jsp");
		opcionResJSP.put("registrarViaje", resJSP);

>>>>>>> fc1df2ca27262cccc8b21050a22ba66548958912
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajes.jsp");
		opcionResJSP.put("listarViajes", resJSP);

		mapaDeNavegacion.put("REGISTRADO", opcionResJSP);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		doGet(req, res);
	}

}