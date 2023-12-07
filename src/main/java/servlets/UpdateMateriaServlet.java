package servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

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
 * Servlet implementation class UpdateMateriaServlet
 */
@WebServlet("/modificarMateria")
public class UpdateMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMateriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		System.out.println(id);
		if(mf.esNoDocente()) {
			Controller ctrl = new Controller();
			Materia materia = ctrl.materiaGetOne(id);
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			LinkedList<Materia> materias = ctrl.materiasGetAll();
			request.setAttribute("carreras", carreras);
			request.setAttribute("materia", materia);
			request.setAttribute("materias", materias);
			if(materia.getIdcorrelativa1() != 0) {
				for(Materia a : materias) {
					if (a.getIdMateria() == materia.getIdcorrelativa1()) {
						request.setAttribute("correlativa1", a);
					}
				}
			}else {
				Materia a = new Materia();
				a.setNombre("");
				a.setIdMateria(0);
				request.setAttribute("correlativa1", a);
			}
			
			if(materia.getIdcorrelativa2() != 0) {
				for(Materia b : materias) {
					if (b.getIdMateria() == materia.getIdcorrelativa2()) {
						request.setAttribute("correlativa2", b);
					}
				}
			}else {
				Materia b = new Materia();
				b.setNombre("");
				b.setIdMateria(0);
				request.setAttribute("correlativa2", b);
			}
			
			request.getRequestDispatcher("WEB-INF/principalNoDocente/materias/materiasUpdate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Materia m = new Materia();
		m.setIdMateria(Integer.parseInt(request.getParameter("id")));
		m.setNombre(request.getParameter("nombre"));
		m.setHsSemanales(Integer.parseInt(request.getParameter("horas")));
		m.setResolucion(request.getParameter("resolucion"));
		m.setAnioCursado(Integer.parseInt(request.getParameter("anio")));
		m.setIdcorrelativa1(Integer.parseInt(request.getParameter("idcorrelativa1")));
		m.setIdcorrelativa2(Integer.parseInt(request.getParameter("idcorrelativa2")));
		Controller ctrl = new Controller();
		m.setCarrera(ctrl.carreraGetOne(Integer.parseInt(request.getParameter("idcarrera"))));
		System.out.println(request.getParameter("descripcion"));
		MiembroFacultad mf = (MiembroFacultad) request.getSession().getAttribute("noDocente");
		if(mf.esNoDocente()) {
			ctrl.updateMateria(m);
			String aviso = "MATERIA MODIFICADA CON EXITO";
			LinkedList<Materia> materias = ctrl.materiasGetAll();
			LinkedList<Carrera> carreras = ctrl.carrerasGetAll();
			request.setAttribute("materias", materias);
			request.setAttribute("carreras", carreras);
			request.setAttribute("aviso", aviso);
			request.getRequestDispatcher("WEB-INF/principalNoDocente/materias/materias.jsp").forward(request, response);
		}
	}

}
