package servlets.Clase;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Clase;
import entities.Comision;
import entities.Docente;
import entities.Materia;
import entities.MiembroFacultad;
import logic.Controller;

/**
 * Servlet implementation class CreateAlumno
 */
@WebServlet("/altaClase")
public class CreateClaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClaseServlet() {
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
		// TODO Auto-generated method stub
		int idClase = Integer.parseInt(request.getParameter("idClase"));
		int legajoDoc = Integer.parseInt(request.getParameter("legajoDoc"));
		int idMateria = Integer.parseInt(request.getParameter("idMateria"));
		int numComision = Integer.parseInt(request.getParameter("numComision"));
		int anioCursado = Integer.parseInt(request.getParameter("anioCursado"));
		String diaSemanaCursado = request.getParameter("diaSemanaCursado");
		Time horarioInicio = null;
		try {
			horarioInicio = new Time(new SimpleDateFormat("HH:mm:ss").parse(request.getParameter("horarioInicio")).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Time horarioFin = null;
		try {
			horarioFin = new Time(new SimpleDateFormat("HH:mm:ss").parse(request.getParameter("horarioFin")).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			try {
				Clase c = new Clase();
				c.setIdClase(idClase);
				c.setDocente(new Docente(legajoDoc));
				c.setMateria(new Materia(idMateria));
				c.setComision(new Comision(numComision,anioCursado));
				c.setDiaSemanaCursado(diaSemanaCursado);
				c.setHorarioInicio(horarioInicio);
				c.setHorarioFin(horarioFin);
				Controller ctrl = new Controller();
				if(ctrl.addClase(c)) {
					LinkedList<Clase> clases = ctrl.clasesGetAll();
					request.setAttribute("clases", clases);
					request.setAttribute("aviso", "CLASE CARGADA CON EXITO");
					request.getRequestDispatcher("WEB-INF/principalNoDocente/clases/clases.jsp").forward(request, response);		
				}	
			}catch(NumberFormatException n) {
				Controller ctrl = new Controller();
				LinkedList<Clase> clases = ctrl.clasesGetAll();
				request.setAttribute("aviso", "");
				request.setAttribute("clases", clases);
				request.getRequestDispatcher("WEB-INF/principalNoDocente/clases/clases.jsp").forward(request, response);
			}			
		}			
	}

}