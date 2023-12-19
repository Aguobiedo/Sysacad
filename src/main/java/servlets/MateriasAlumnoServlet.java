package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.AlumnoPlan;
import logic.Controller;

/**
 * Servlet implementation class MateriasAlumnoServlet
 */
@WebServlet("/MateriasAlumnoServlet")
public class MateriasAlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MateriasAlumnoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controller ctrl = new Controller();
        Alumno a = (Alumno) request.getSession().getAttribute("alumno");
        if (a != null) {
            LinkedList<	AlumnoPlan> alumnoPlanes = ctrl.materiasAlumno(a);
            
            request.setAttribute("alumnoPlanes", alumnoPlanes);
            request.getRequestDispatcher("WEB-INF/principal/MateriasAlumno.jsp").forward(request, response);
        } else {
            response.getWriter().append("Alumno no autorizado").append(request.getContextPath());
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
