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

			jspSiguiente = "/principal.jsp";
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
		mapaPublico.put("ordenarViajesPublico",
				new OrdenarViajesPublicosAction());

		mapaDeAcciones.put("PUBLICO", mapaPublico);

		Map<String, Accion> mapaRegistrado = new HashMap<String, Accion>();
		mapaRegistrado.put("modificarDatosPersonales",
				new ModificarDatosPersonalesAction());
		mapaRegistrado.put("registrarViaje", new RegistrarViajeAction());
		mapaRegistrado.put("listarViajes", new ListarViajesAction());
		mapaRegistrado.put("listarViajesUsuario",
				new ListarViajesUsuarioRegistradoAction());
		mapaRegistrado.put("listarViajesOfertados",
				new ListarViajesOfertadosAction());
		mapaRegistrado.put("incluirEnInteresados",
				new IncluirEnInteresadosAction());
		mapaRegistrado.put("borrarViaje", new BorrarViajeAction());
		mapaRegistrado.put("cerrarSesion", new CerrarSesionAction());
		mapaRegistrado.put("modificarViaje", new ModificarViajeAction());
		mapaRegistrado
				.put("comentariosUsuario", new ComentariosUsuarioAction());
		mapaRegistrado.put("excluirParticipante", new ExcluirAction());
		mapaRegistrado.put("admitirParticipante", new AdmitirAction());
		mapaRegistrado.put("solicitudesViaje", new SolicitudesViajeAction());
		mapaRegistrado.put("pasarAPendientes", new PasarAPendientesAction());
		mapaRegistrado.put("misViajesImplicados",
				new MisViajesImplicadosAction());
		mapaRegistrado.put("comentarEnViaje", new ComentarEnViajeAction());
		mapaRegistrado.put("misViajesImplicados",
				new MisViajesImplicadosAction());
		mapaRegistrado.put("ordenarViajesPublico",
				new OrdenarViajesPublicosAction());
		mapaRegistrado.put("ordenarViajesPromotor",
				new OrdenarViajesOfertados());
		mapaRegistrado.put("comentarEnViajeAUsuario",
				new ComentarEnViajeAUsuarioAction());
		mapaRegistrado.put("cancelarPlaza", new CancelarPlazaAction());

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
		resJSP.put("EXITO", "/login.jsp");
		opcionResJSP.put("registrarse", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajes.jsp");
		opcionResJSP.put("listarViajes", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajes.jsp");
		opcionResJSP.put("ordenarViajesPublico", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajesUsuario.jsp");
		opcionResJSP.put("listarViajesUsuario", resJSP);

		mapaDeNavegacion.put("PUBLICO", opcionResJSP);

		// Crear mapas auxiliares vacíos
		opcionResJSP = new HashMap<String, Map<String, String>>();
		resJSP = new HashMap<String, String>();

		// Mapa de navegación de usuarios registrados
		resJSP.put("EXITO", "/listarViajes");
		opcionResJSP.put("login", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/login.jsp");
		opcionResJSP.put("registrarse", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/modificarDatosPersonales.jsp");
		opcionResJSP.put("modificarDatosPersonales", resJSP);
		resJSP.put("EXITO", "/modificarDatosPersonales.jsp");
		opcionResJSP.put("modificarDatosPersonales", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/misViajesOfertados.jsp");
		opcionResJSP.put("listarViajesOfertados", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/");
		opcionResJSP.put("cerrarSesion", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/modificarViaje.jsp");
		opcionResJSP.put("modificarViaje", resJSP);

		resJSP.put("EXITO", "/misViajesOfertados.jsp");
		opcionResJSP.put("modificarViaje", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/datosViaje.jsp");
		opcionResJSP.put("incluirEnInteresados", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/datosViaje.jsp");
		opcionResJSP.put("listarViajesUsuario", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/registrarViaje.jsp");
		opcionResJSP.put("registrarViaje", resJSP);

		resJSP.put("EXITO", "/misViajesOfertados.jsp");
		opcionResJSP.put("registrarViaje", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/misViajesOfertados.jsp");
		opcionResJSP.put("borrarViaje", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajes.jsp");
		opcionResJSP.put("listarViajes", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/comentariosUsuario.jsp");
		opcionResJSP.put("comentariosUsuario", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/solicitudesViaje.jsp");
		opcionResJSP.put("pasarAPendientes", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/listaViajes.jsp");
		opcionResJSP.put("ordenarViajesPublico", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/misViajesOfertados.jsp");
		opcionResJSP.put("ordenarViajesPromotor", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/solicitudesViaje.jsp");
		opcionResJSP.put("admitirParticipante", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/solicitudesViaje.jsp");
		opcionResJSP.put("excluirParticipante", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/solicitudesViaje.jsp");
		opcionResJSP.put("solicitudesViaje", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/misViajesImplicados.jsp");
		opcionResJSP.put("misViajesImplicados", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/comentarEnViaje.jsp");
		opcionResJSP.put("comentarEnViaje", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/misViajesImplicados.jsp");
		opcionResJSP.put("comentarEnViajeAUsuario", resJSP);

		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/misViajesImplicados.jsp");
		opcionResJSP.put("cancelarPlaza", resJSP);

		mapaDeNavegacion.put("REGISTRADO", opcionResJSP);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		doGet(req, res);
	}

}