package servlets.NoDocente;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class FiltrarNoDocenteServlet
 */
@WebServlet("/filtrarNoDocente")
public class FiltrarNoDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltrarNoDocenteServlet() {
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
		String apellido = request.getParameter("apellido");
		Controller ctrl = new Controller();
		LinkedList<MiembroFacultad> noDocentes = ctrl.filtrarNoDocentesPorApellido(apellido);
		request.setAttribute("noDocentes", noDocentes);
		request.setAttribute("aviso", "");
		request.getRequestDispatcher("WEB-INF/principalNoDocente/noDocentes/noDocentes.jsp").forward(request, response);
	}

}