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
 * Servlet implementation class UpdateComisionServlet
 */
@WebServlet("/modificarClase")
public class UpdateClaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClaseServlet() {
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
			Clase clase= ctrl.claseGetOne(idclase);
			request.setAttribute("clase", clase);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/clases/clasesUpdate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Clase a = new Clase();
		a.setIdClase(Integer.parseInt(request.getParameter("idclase")));
		a.setDocente(new Docente(Integer.parseInt(request.getParameter("legajo"))));
		a.setMateria(new Materia(Integer.parseInt(request.getParameter("materia"))));
		a.setComision(new Comision(Integer.parseInt(request.getParameter("nrocomision")),Integer.parseInt(request.getParameter("aniocursado"))));
		a.setDiaSemanaCursado(request.getParameter("diasemana"));
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
		a.setHorarioInicio(horarioInicio);
		a.setHorarioFin(horarioFin);
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			ctrl.updateClase(a);
			String aviso = "CLASE MODIFICADA CON EXITO";
			LinkedList<Clase> clases = ctrl.clasesGetAll();
			LinkedList<Materia> materias= ctrl.materiasGetAll();
			request.setAttribute("clases", clases);
			request.setAttribute("materias", materias);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/clases/clases.jsp").forward(request, response);
		}
	}

}
