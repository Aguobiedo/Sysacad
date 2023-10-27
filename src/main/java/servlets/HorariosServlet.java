package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.Inscripcion;
import logic.Controller;

/**
 * Servlet implementation class HorariosServlet
 */
@WebServlet("/horarios")
public class HorariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HorariosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Controller ctrl = new Controller();
		Alumno a = (Alumno)request.getSession().getAttribute("alumno");
		if(a!=null) {
			LinkedList<Inscripcion> inscripciones = ctrl.getInscripcionesByAlumno(a);
			request.setAttribute("inscripciones", inscripciones);
			request.getRequestDispatcher("WEB-INF/principal/horarios.jsp").forward(request, response);
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
