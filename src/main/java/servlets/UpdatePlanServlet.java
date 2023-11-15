package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Plan;
import entities.Carrera;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class UpdatePlanServlet
 */
@WebServlet("/modificarPlan")
public class UpdatePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePlanServlet() {
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
			Plan plan = ctrl.planGetOne(id);
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			request.setAttribute("carreras", carreras);
			request.setAttribute("plan", plan);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/planes/planesUpdate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Plan p = new Plan();
		p.setIdPlan(Integer.parseInt(request.getParameter("id")));
		p.setDescripcion(request.getParameter("descripcion"));
		Controller ctrl = new Controller();
		p.setCarrera(ctrl.carreraGetOne(Integer.parseInt(request.getParameter("idCarrera"))));
		System.out.println(request.getParameter("descripcion"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			ctrl.updatePlan(p);
			String aviso = "PLAN MODIFICADO CON EXITO";
			LinkedList<Plan> planes = ctrl.planesGetAll();
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			request.setAttribute("planes", planes);
			request.setAttribute("carreras", carreras);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/planes/planes.jsp").forward(request, response);
		}
	}

}
