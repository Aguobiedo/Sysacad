package servlets.Plan;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Carrera;
import entities.MiembroFacultad;
import entities.Plan;
import logic.Controller;

/**
 * Servlet implementation class DeletePlanServlet
 */
@WebServlet("/bajaPlan")
public class DeletePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePlanServlet() {
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
			ctrl.deletePlan(id);
			String aviso = "PLAN BORRADO CON EXITO";
			LinkedList<Plan> planes = ctrl.planesGetAll();
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			request.setAttribute("planes", planes);
			request.setAttribute("aviso", aviso);
			request.setAttribute("carreras", carreras);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/planes/planes.jsp").forward(request, response);
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
