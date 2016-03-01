package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.dto.DTOAssembler;
import uo.sdi.model.Rating;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ComentarEnViajeAUsuarioAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String queryString = request.getQueryString();
		
		Long idTrip = Long.parseLong(queryString.split("=")[1]);

			try {
				Long idUserTo = Long.parseLong(request.getParameter("participante").split(" ")[0]);
				
				Rating rating = new Rating();
				rating.setSeatAboutTripId(idTrip);
				rating.setSeatFromTripId(idTrip);
				rating.setSeatFromUserId(((User)request.getSession().getAttribute("user")).getId());
				rating.setSeatAboutUserId(idUserTo);
				rating.setComment((String) request.getParameter("comment"));
				rating.setValue(Integer.valueOf((String) request.getParameter("ratingValue")));
				System.out.println(rating);
				
				PersistenceFactory.newRatingDao().save(rating);
				
				Log.info("El usuario [%s] ha comentado sobre el usuario [%s] en el viaje [%s]",
						((User)request.getSession().getAttribute("user")).getId(),
						idUserTo,
						idTrip);
				
				request.setAttribute("comentarAUsuarioAction", "");
				request.setAttribute("viajesImplicadosDto",
						DTOAssembler.getViajesImplicadosDto(((User)request.getSession().getAttribute("user")).getId()));
				
				return "EXITO";
			} catch (NumberFormatException e) {

				Log.error("No se han leído los usuarios del viaje");
				return "FRACASO";

			}

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
