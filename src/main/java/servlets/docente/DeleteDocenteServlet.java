package servlets.docente;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class DeleteDocenteServlet
 */
@WebServlet("/bajaDocente")
public class DeleteDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDocenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int legajo = Integer.parseInt(request.getParameter("legajoDelete"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(legajo);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.deleteDocente(legajo);
			String aviso = "DOCENTE BORRADO CON EXITO";
			LinkedList<MiembroFacultad> docentes = ctrl.docentesGetAll();
			request.setAttribute("aviso", aviso);
			request.setAttribute("docentes", docentes);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/docentes/docentes.jsp").forward(request, response);
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
