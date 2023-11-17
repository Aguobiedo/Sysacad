package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MiembroFacultad;
import entities.Carrera;
import logic.Controller;

/**
 * Servlet implementation class DeleteCarreraServlet
 */
@WebServlet("/bajaCarrera")
public class DeleteCarreraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCarreraServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(id);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.deleteCarrera(id);
			String aviso = "CARRERA BORRADA CON EXITO";
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			request.setAttribute("aviso", aviso);
			request.setAttribute("carreras", carreras);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/carreras/carreras.jsp").forward(request, response);
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
