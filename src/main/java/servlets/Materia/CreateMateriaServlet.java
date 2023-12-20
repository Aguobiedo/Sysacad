package servlets.Materia;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Carrera;
import entities.Materia;
import entities.MiembroFacultad;
import entities.Plan;
import logic.Controller;

/**
 * Servlet implementation class CreateMateriaServlet
 */
@WebServlet("/altaMateria")
public class CreateMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMateriaServlet() {
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
		String nombre = request.getParameter("nombre");
		String horas = request.getParameter("horas");
		String resolucion = request.getParameter("resolucion");
		String anio = request.getParameter("anio");
		String idcarrera = request.getParameter("idcarrera");
		String idcorrelativa1 = request.getParameter("correlativa1");
		String idcorrelativa2 = request.getParameter("correlativa2");
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			try {
				Materia m = new Materia();
				m.setNombre(nombre);
				m.setHsSemanales(Integer.parseInt(horas));
				m.setResolucion(resolucion);				
				m.setAnioCursado(Integer.parseInt(anio));
				m.setIdcorrelativa1(Integer.parseInt(idcorrelativa1));
				m.setIdcorrelativa2(Integer.parseInt(idcorrelativa2));
				Controller ctrl = new Controller();
				m.setCarrera(ctrl.carreraGetOne(Integer.parseInt(idcarrera)));			
				if(ctrl.addMateria(m)) {
					LinkedList<Materia> materias = ctrl.materiasGetAll();
					LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
					String aviso = "MATERIA CARGADA CON EXITO";
					request.setAttribute("materias", materias);
					request.setAttribute("carreras", carreras);
					request.setAttribute("aviso", aviso);
					request.getRequestDispatcher("WEB-INF/principalNoDocente/materias/materias.jsp").forward(request, response);		
				}		
			}catch(NumberFormatException n) {
				Controller ctrl = new Controller();
				LinkedList<Materia> materias = ctrl.materiasGetAll();
				LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
				request.setAttribute("materias", materias);
				request.setAttribute("carreras", carreras);
				request.setAttribute("aviso", "");
				request.getRequestDispatcher("WEB-INF/principalNoDocente/materias/materias.jsp").forward(request, response);
			}
						
		}
	}

}
