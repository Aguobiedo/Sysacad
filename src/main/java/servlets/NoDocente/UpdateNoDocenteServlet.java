package servlets.NoDocente;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Docente;
import entities.MiembroFacultad;
import entities.NoDocente;
import logic.Controller;

/**
 * Servlet implementation class UpdateNoDocenteServlet
 */
@WebServlet("/modificarNoDocente")
public class UpdateNoDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNoDocenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int legajo = Integer.parseInt(request.getParameter("legajo"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(legajo);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			NoDocente noDocente = ctrl.noDocenteGetOne(legajo);
			request.setAttribute("noDocente", noDocente);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/noDocentes/noDocentesUpdate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoDocente a = new NoDocente();
		a.setLegajo(Integer.parseInt(request.getParameter("legajo")));
		a.setNombre(request.getParameter("name"));
		System.out.println(request.getParameter("name"));
		a.setApellido(request.getParameter("last-name"));
		a.setDni(request.getParameter("dni"));
		a.setDireccion(request.getParameter("adress"));
		a.setEmail(request.getParameter("email"));
		a.setUsuario(request.getParameter("username"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.updateNoDocente(a);
			String aviso = "NO DOCENTE MODIFICADO CON EXITO";
			LinkedList<MiembroFacultad> noDocentes = ctrl.noDocentesGetAll();
			request.setAttribute("noDocentes", noDocentes);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/noDocentes/noDocentes.jsp").forward(request, response);
		}
	}

}
