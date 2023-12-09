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
 * Servlet implementation class CreateCarreraServlet
 */
@WebServlet("/altaCarrera")
public class CreateCarreraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCarreraServlet() {
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
		String id = request.getParameter("idCarrera");
		String name = request.getParameter("name");
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			try {
				Carrera c = new Carrera();
				c.setIdCarrera(Integer.parseInt(id));
				c.setNombre(name);
				Controller ctrl = new Controller();
				if(ctrl.addCarrera(c)) {
					LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
					request.setAttribute("carreras", carreras);
					request.setAttribute("aviso", "CARRERA CARGADA CON EXITO");
					request.getRequestDispatcher("WEB-INF/principalNoDocente/carreras/carreras.jsp").forward(request, response);		
				}	
			}catch(NumberFormatException n) {
				Controller ctrl = new Controller();
				LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
				request.setAttribute("aviso", "");
				request.setAttribute("carreras", carreras);
				request.getRequestDispatcher("WEB-INF/principalNoDocente/carreras/carreras.jsp").forward(request, response);
			}			
		}
	}

}
