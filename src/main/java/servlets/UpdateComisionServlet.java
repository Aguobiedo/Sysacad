package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Carrera;
import entities.Comision;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class UpdateComisionServlet
 */
@WebServlet("/modificarComision")
public class UpdateComisionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateComisionServlet() {
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
			Comision comision= ctrl.comisionGetOne(nro,anio);
			request.setAttribute("comision", comision);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/comisiones/comisionesUpdate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comision a = new Comision();
		a.setNumComision(Integer.parseInt(request.getParameter("numero")));
		a.setAnioCursado(Integer.parseInt(request.getParameter("anio")));
		a.setTurno(request.getParameter("turno"));
		System.out.println(request.getParameter("name"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.updateComision(a);
			String aviso = "COMISION MODIFICADA CON EXITO";
			LinkedList<Comision> comisiones = ctrl.comisionesGetAll();
			request.setAttribute("comisiones", comisiones);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/comisiones/comisiones.jsp").forward(request, response);
		}
	}

}
