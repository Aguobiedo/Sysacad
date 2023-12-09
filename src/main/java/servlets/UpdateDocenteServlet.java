package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.Docente;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class UpdateDocenteServlet
 */
@WebServlet("/modificarDocente")
public class UpdateDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDocenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int legajo = Integer.parseInt(request.getParameter("legajo"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(legajo);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			Docente docente = ctrl.docenteGetOne(legajo);
			request.setAttribute("docente", docente);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/docentes/docentesUpdate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Docente a = new Docente();
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
			ctrl.updateDocente(a);
			String aviso = "DOCENTE MODIFICADO CON EXITO";
			LinkedList<MiembroFacultad> docentes = ctrl.docentesGetAll();
			request.setAttribute("docentes", docentes);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/docentes/docentes.jsp").forward(request, response);
		}
	}

}
