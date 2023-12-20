package servlets.VistaDocente;

import java.io.IOException;
import java.util.LinkedList;

import logic.Controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Clase;
import entities.Docente;
import entities.Examen;

/**
 * Servlet implementation class Inscripciones
 */
@WebServlet("/cargarNotas")
public class CargarNotasDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarNotasDocenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Controller ctrl = new Controller();
		int idclase = Integer.parseInt(request.getParameter("idClase"));
		Clase c = ctrl.claseGetOne(idclase);
		if(c!=null) {
			LinkedList<Examen> examenes = ctrl.getExamenesEnCursoByClase(c);
			request.setAttribute("examenes", examenes);
			request.getRequestDispatcher("WEB-INF/principalDocente/ListadoCargaNotas.jsp").forward(request, response);
		}else {
			response.getWriter().append("NO SE ENCONTRO LA CLASE").append(request.getContextPath());
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
