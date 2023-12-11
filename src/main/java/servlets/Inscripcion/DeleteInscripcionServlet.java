package servlets.Inscripcion;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Inscripcion;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class DeleteInscripcionServlet
 */
@WebServlet("/bajaInscripcion")
public class DeleteInscripcionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInscripcionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int legajo = Integer.parseInt(request.getParameter("legajo"));
		int idclase = Integer.parseInt(request.getParameter("idclase"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.deleteInscripcion(legajo, idclase);
			String aviso = "INSCRIPCION BORRADA CON EXITO";
			LinkedList<Inscripcion> inscripciones= ctrl.inscripcionesGetAll();
			request.setAttribute("inscripciones", inscripciones);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/inscripciones/inscripciones.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
