package servlets;

import java.io.IOException;

import entities.MiembroFacultad;
import entities.Alumno;
import entities.Docente;
import entities.NoDocente;

import data.MiembroFacultadDAO;
import logic.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Prueba
 */
@WebServlet("/login")
public class Prueba extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prueba() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Controller ctrl = new Controller();
		MiembroFacultad mf = ctrl.validate(username, password);
		if(mf.getTipo().equals("Alumno")){
			Alumno a = (Alumno) mf;
			request.getSession().setAttribute("alumno", a);
		}
		request.getRequestDispatcher("WEB-INF/principal/Principal.jsp").forward(request, response);
	}
		
		
		/*
		response.getWriter().append("Email: ").append(username)
		.append("- Password: ").append(password);
		request.getRequestDispatcher("WEB-INF/UserManagement.jsp").forward(request, response);
		*/
	
	
	

}
