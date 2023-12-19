package servlets.carrera;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Carrera;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class UpdateCarrera
 */
@WebServlet("/modificarCarrera")
public class UpdateCarrera extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCarrera() {
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
			Carrera carrera = ctrl.carreraGetOne(id);
			request.setAttribute("carrera", carrera);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/carreras/carrerasUpdate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrera a = new Carrera();
		a.setIdCarrera(Integer.parseInt(request.getParameter("legajo")));
		a.setNombre(request.getParameter("name"));
		System.out.println(request.getParameter("name"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.updateCarrera(a);
			String aviso = "CARRERA MODIFICADA CON EXITO";
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			request.setAttribute("carreras", carreras);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/carreras/carreras.jsp").forward(request, response);
		}
	}

}
