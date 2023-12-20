package servlets.Clase;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Clase;
import entities.Materia;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class DeleteComisionServlet
 */
@WebServlet("/bajaClase")
public class DeleteClaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idclase = Integer.parseInt(request.getParameter("id"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(idclase);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.deleteClase(idclase);
			String aviso = "CLASE BORRADA CON EXITO";
			LinkedList<Clase> clases = ctrl.clasesGetAll();
			LinkedList<Materia> materias= ctrl.materiasGetAll();
			request.setAttribute("aviso", aviso);
			request.setAttribute("clases", clases);
			request.setAttribute("materias", materias);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/clases/clases.jsp").forward(request, response);
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
