package servlets.Inscripcion;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MiembroFacultad;
import entities.Inscripcion;
import logic.Controller;

/**
 * Servlet implementation class CreateInscripcionServlet
 */
@WebServlet("/altaInscripcion")
public class CreateInscripcionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateInscripcionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String legajo = request.getParameter("legajo");
		String idclase = request.getParameter("idclase");
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		Controller ctrl = new Controller();
		if(mf.esNoDocente()) {
			try {
				String aviso = ctrl.addInscripcion(Integer.parseInt(legajo), Integer.parseInt(idclase));
				LinkedList<Inscripcion> inscripciones= ctrl.inscripcionesGetAll();
				request.setAttribute("inscripciones", inscripciones);
				request.setAttribute("aviso", aviso);
				request.getRequestDispatcher("WEB-INF/principalNoDocente/inscripciones/inscripciones.jsp").forward(request, response);		
						
			}catch(NumberFormatException n) {
				LinkedList<Inscripcion> inscripciones= ctrl.inscripcionesGetAll();
				request.setAttribute("inscripciones", inscripciones);
				request.setAttribute("aviso", "");
				request.getRequestDispatcher("WEB-INF/principalNoDocente/inscripciones/inscripciones.jsp").forward(request, response);
			}
						
		}
	}

}
