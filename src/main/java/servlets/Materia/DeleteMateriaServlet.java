package servlets.Materia;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Carrera;
import entities.MiembroFacultad;
import entities.Materia;
import logic.Controller;

/**
 * Servlet implementation class DeleteMateriaServlet
 */
@WebServlet("/bajaMateria")
public class DeleteMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMateriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(id);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.deleteMateria(id);
			String aviso = "MATERIA BORRADA CON EXITO";
			LinkedList<Materia> materias = ctrl.materiasGetAll();
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			request.setAttribute("materias", materias);
			request.setAttribute("aviso", aviso);
			request.setAttribute("carreras", carreras);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/materias/materias.jsp").forward(request, response);
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
