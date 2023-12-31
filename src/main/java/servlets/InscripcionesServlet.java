package servlets;

import java.io.IOException;
import java.util.LinkedList;

import logic.Controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.Inscripcion;

/**
 * Servlet implementation class Inscripciones
 */
@WebServlet("/inscripciones")
public class InscripcionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscripcionesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Controller ctrl = new Controller();
		Alumno a = (Alumno)request.getSession().getAttribute("alumno");
		if(a!=null) {
			LinkedList<Inscripcion> inscripciones = ctrl.getInscripcionesByAlumno(a);
			request.setAttribute("inscripciones", inscripciones);
			request.getRequestDispatcher("WEB-INF/principal/Inscripciones.jsp").forward(request, response);
		}else {
			response.getWriter().append("NO SE ENCONTRO AL ALUMNO").append(request.getContextPath());
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
