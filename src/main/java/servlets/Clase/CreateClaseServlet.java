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
		String idclase = request.getParameter("idclase");
		String legajo = request.getParameter("legajo");
		String materia = request.getParameter("materia");
		String nrocomision = request.getParameter("nrocomision");
		String aniocursado = request.getParameter("aniocursado");
		String diaSemanaCursado = request.getParameter("diasemana");
		Time horarioInicio = null;
		try {
			horarioInicio = new Time(new SimpleDateFormat("HH:mm").parse(request.getParameter("horarioinicio")).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Time horarioFin = null;
		try {
			horarioFin = new Time(new SimpleDateFormat("HH:mm").parse(request.getParameter("horariofin")).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			try {
				Clase c = new Clase();
				c.setIdClase(Integer.parseInt(idclase));
				c.setDocente(new Docente(Integer.parseInt(legajo)));
				c.setMateria(new Materia(Integer.parseInt(materia)));
				c.setComision(new Comision(Integer.parseInt(nrocomision),Integer.parseInt(aniocursado)));
				c.setDiaSemanaCursado(diaSemanaCursado);
				c.setHorarioInicio(horarioInicio);
				c.setHorarioFin(horarioFin);
				Controller ctrl = new Controller();
				String aviso = ctrl.addClase(c);
				LinkedList<Clase> clases = ctrl.clasesGetAll();
				LinkedList<Materia> materias= ctrl.materiasGetAll();
				request.setAttribute("clases", clases);
				request.setAttribute("materias", materias);
				request.setAttribute("aviso", aviso);
				request.getRequestDispatcher("WEB-INF/principalNoDocente/clases/clases.jsp").forward(request, response);		
					
			}catch(NumberFormatException n) {
				Controller ctrl = new Controller();
				LinkedList<Clase> clases = ctrl.clasesGetAll();
				LinkedList<Materia> materias= ctrl.materiasGetAll();
				request.setAttribute("aviso", "");
				request.setAttribute("clases", clases);
				request.setAttribute("materias", materias);
				request.getRequestDispatcher("WEB-INF/principalNoDocente/clases/clases.jsp").forward(request, response);
			}			
		}			
	}

}