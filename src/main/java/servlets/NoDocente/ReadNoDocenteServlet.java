package servlets.NoDocente;

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
 * Servlet implementation class ReadNoDocenteServlet
 */
@WebServlet("/readNoDocente")
public class ReadNoDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadNoDocenteServlet() {
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
			LinkedList<MiembroFacultad> noDocentes = ctrl.noDocentesGetAll();
			String aviso = "CARGA DE NO DOCENTE FALLIDA";
			request.setAttribute("noDocentes", noDocentes);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/noDocentes/noDocentes.jsp").forward(request, response);
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
