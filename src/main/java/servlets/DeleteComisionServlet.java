package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Comision;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class DeleteComisionServlet
 */
@WebServlet("/bajaComision")
public class DeleteComisionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComisionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nro = Integer.parseInt(request.getParameter("numero"));
		int anio = Integer.parseInt(request.getParameter("anio"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(nro + " " + anio);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.deleteComision(nro,anio);
			String aviso = "COMISION BORRADA CON EXITO";
			LinkedList<Comision> comisiones = ctrl.comisionesGetAll();
			request.setAttribute("aviso", aviso);
			request.setAttribute("comisiones", comisiones);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/comisiones/comisiones.jsp").forward(request, response);
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
