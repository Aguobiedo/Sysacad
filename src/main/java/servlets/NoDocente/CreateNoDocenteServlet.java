package servlets.NoDocente;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MiembroFacultad;
import entities.NoDocente;
import logic.Controller;

/**
 * Servlet implementation class CreateNoDocenteServlet
 */
@WebServlet("/altaNoDocente")
public class CreateNoDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNoDocenteServlet() {
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
			NoDocente a = new NoDocente();
			a.setLegajo(legajo);
			a.setNombre(name);
			a.setApellido(last_name);
			a.setDni(dni);
			a.setDireccion(adress);
			a.setEmail(email);
			a.setUsuario(username);
			Controller ctrl = new Controller();
			String aviso = ctrl.addNoDocente(a, password);
			LinkedList<MiembroFacultad> noDocentes = ctrl.noDocentesGetAll();
			request.setAttribute("noDocentes", noDocentes);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/noDocentes/noDocentes.jsp").forward(request, response);
		}
	}

}
