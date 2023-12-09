package servlets;

import java.io.IOException;

import java.sql.SQLException;
import java.util.LinkedList;

import entities.MiembroFacultad;
import entities.Alumno;
import entities.Clase;
import entities.Docente;
import entities.NoDocente;

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
public class LoginServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		try {
			MiembroFacultad mf = ctrl.validate(username, password);
			if(mf.getTipo().equals("Alumno")){
				Alumno a = (Alumno) mf;
				request.getSession().setAttribute("alumno", a);
				request.getRequestDispatcher("WEB-INF/principal/Principal.jsp").forward(request, response);
			}else if(mf.getTipo().equals("Docente")){
				Docente d = (Docente) mf;
				request.getSession().setAttribute("docente", d);
				request.setAttribute("legajo", d.getLegajo());
				request.getRequestDispatcher("WEB-INF/principalDocente/PrincipalDocente.jsp").forward(request, response);
			}else {
				NoDocente nd = (NoDocente)mf;
				request.getSession().setAttribute("noDocente", nd);
				request.getRequestDispatcher("WEB-INF/principalNoDocente/PrincipalNoDocente.jsp").forward(request, response);
			}
			
		}catch (SQLException e) {
			// PAGINA DONDE LO ENVIA EN CASO DE QUE SALGA UNA EXCEPCION SQL
			e.printStackTrace();
			request.getRequestDispatcher("views/pages/excepciones/sqlexception/sqlexception.jsp").forward(request, response);
		}catch (NullPointerException n){
			// PAGINA DONDE LO ENVIA EN CASO DE QUE SALGA UNA EXCEPCION NULL POINTER (mf recupera dato null)
			n.printStackTrace();
			request.getRequestDispatcher("views/pages/excepciones/sqlexception/sqlexception.jsp").forward(request, response);
		}
		
	}
		
		
		/*
		response.getWriter().append("Email: ").append(username)
		.append("- Password: ").append(password);
		request.getRequestDispatcher("WEB-INF/UserManagement.jsp").forward(request, response);
		*/
	
	
	

}
