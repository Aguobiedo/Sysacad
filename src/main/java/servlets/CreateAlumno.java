package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class CreateAlumno
 */
@WebServlet("/altaAlumno")
public class CreateAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAlumno() {
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
		// TODO Auto-generated method stub
		int legajo = Integer.parseInt(request.getParameter("legajo"));
		String name = request.getParameter("name");
		String last_name = request.getParameter("last-name");
		String dni = request.getParameter("dni");
		String adress = request.getParameter("adress");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			Alumno a = new Alumno();
			a.setLegajo(legajo);
			a.setNombre(name);
			a.setApellido(last_name);
			a.setDni(dni);
			a.setDireccion(adress);
			a.setEmail(email);
			a.setUsuario(username);
			Controller ctrl = new Controller();
			if(ctrl.addAlumno(a, password)) {
				LinkedList<MiembroFacultad> alumnos = ctrl.alumnosGetAll();
				request.setAttribute("alumnos", alumnos);
				request.setAttribute("aviso", "ALUMNO CARGADO CON EXITO");
				request.getRequestDispatcher("WEB-INF/principalNoDocente/alumnos/alumnos.jsp").forward(request, response);		
			}			
		}
	}

}
