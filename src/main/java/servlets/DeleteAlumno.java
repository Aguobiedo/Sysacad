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
 * Servlet implementation class DeleteAlumno
 */
@WebServlet("/bajaAlumno")
public class DeleteAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAlumno() {
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
		int legajo = Integer.parseInt(request.getParameter("legajoDelete"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(legajo);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.deleteAlumno(legajo);
			LinkedList<Alumno> alumnos = ctrl.alumnosGetAll();
			request.setAttribute("alumnos", alumnos);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/alumnos/alumnos.jsp").forward(request, response);
		}
	}

}
