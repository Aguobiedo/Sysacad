package servlets.comision;

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
 * Servlet implementation class CreateComisionServlet
 */
@WebServlet("/altaComision")
public class CreateComisionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateComisionServlet() {
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
		String nro = request.getParameter("nroComision");
		String anio = request.getParameter("anio");
		String turno = request.getParameter("turno");
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			try {
				Comision c = new Comision();
				c.setNumComision(Integer.parseInt(nro));
				c.setAnioCursado(Integer.parseInt(anio));
				c.setTurno(turno);
				Controller ctrl = new Controller();
				String aviso = ctrl.addComision(c);
				LinkedList<Comision> comisiones = ctrl.comisionesGetAll();
				request.setAttribute("comisiones", comisiones);
				request.setAttribute("aviso", aviso);
				request.getRequestDispatcher("WEB-INF/principalNoDocente/comisiones/comisiones.jsp").forward(request, response);		
					
			}catch(NumberFormatException n) {
				Controller ctrl = new Controller();
				LinkedList<Comision> comisiones = ctrl.comisionesGetAll();
				request.setAttribute("aviso", "");
				request.setAttribute("comisiones", comisiones);
				request.getRequestDispatcher("WEB-INF/principalNoDocente/comisiones/comisiones.jsp").forward(request, response);
			}			
		}
	}

}
