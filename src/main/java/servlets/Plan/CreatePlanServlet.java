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
import entities.NoDocente;
import entities.Plan;
import logic.Controller;

/**
 * Servlet implementation class CreatePlanServlet
 */
@WebServlet("/altaPlan")
public class CreatePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePlanServlet() {
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
		String id = request.getParameter("idPlan");
		String descripcion = request.getParameter("descripcion");
		String idCarrera = request.getParameter("idCarrera");
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			try {
				Plan p = new Plan();
				p.setIdPlan(Integer.parseInt(id));
				p.setDescripcion(descripcion);
				Controller ctrl = new Controller();
				p.setCarrera(ctrl.carreraGetOne(Integer.parseInt(idCarrera)));
				if(ctrl.addPlan(p)) {
					LinkedList<Plan> planes = ctrl.planesGetAll();
					LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
					request.setAttribute("planes", planes);
					request.setAttribute("carreras", carreras);
					request.setAttribute("aviso", "PLAN CARGADO CON EXITO");
					request.getRequestDispatcher("WEB-INF/principalNoDocente/planes/planes.jsp").forward(request, response);		
				}		
			}catch(NumberFormatException n) {
				Controller ctrl = new Controller();
				LinkedList<Plan> planes = ctrl.planesGetAll();
				LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
				request.setAttribute("planes", planes);
				request.setAttribute("carreras", carreras);
				request.setAttribute("aviso", "");
				request.getRequestDispatcher("WEB-INF/principalNoDocente/planes/planes.jsp").forward(request, response);
			}
						
		}
	}

}
