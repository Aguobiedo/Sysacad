package servlets;

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
 * Servlet implementation class ReadMateriaServlet
 */
@WebServlet("/readMateria")
public class ReadMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadMateriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			LinkedList<Materia> materias = ctrl.materiasGetAll();
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			String aviso = "CARGA DE MATERIA FALLIDA";
			request.setAttribute("materias", materias);
			request.setAttribute("carreras", carreras);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/materias/materias.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
