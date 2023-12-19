package servlets.docente;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Docente;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class CreateDocente
 */
@WebServlet("/altaDocente")
public class CreateDocente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDocente() {
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
			Docente a = new Docente();
			a.setLegajo(legajo);
			a.setNombre(name);
			a.setApellido(last_name);
			a.setDni(dni);
			a.setDireccion(adress);
			a.setEmail(email);
			a.setUsuario(username);
			Controller ctrl = new Controller();
			String aviso = ctrl.addDocente(a, password);
			LinkedList<MiembroFacultad> docentes = ctrl.docentesGetAll();
			request.setAttribute("docentes", docentes);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/docentes/docentes.jsp").forward(request, response);
		}
	}

}
