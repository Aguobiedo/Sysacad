package servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.Examen;
import entities.Clase;
import logic.Controller;

@WebServlet("/ExamenesAlumnoServlet")
public class ExamenAlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ExamenAlumnoServlet.class.getName());

    public ExamenAlumnoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controller ctrl = new Controller();
        Alumno a = (Alumno) request.getSession().getAttribute("alumno");
        if (a != null) {
            LinkedList<Examen> examenes = ctrl.getExamenAlumno(a);
            LinkedList<Clase> clases = ctrl.matDispRendir(a);

            
            request.setAttribute("examenes", examenes);
            request.setAttribute("clases", clases);
            request.getRequestDispatcher("WEB-INF/principal/ExamenesAlumno.jsp").forward(request, response);
        } else {
            response.getWriter().append("Alumno no autorizado").append(request.getContextPath());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idClaseParam = request.getParameter("idClase");

        if (idClaseParam != null && !idClaseParam.isEmpty()) {
            Alumno alumno = (Alumno) request.getSession().getAttribute("alumno");

            if (alumno != null) {
                try {
                    int idClase = Integer.parseInt(idClaseParam);
                    Controller ctrl = new Controller();

                    if (ctrl.inscribirExamen(alumno.getLegajo(), idClase)) {
                        // Inscripción exitosa
                        String exitoMsg = "Inscripción realizada con éxito.";
                        request.setAttribute("exito", exitoMsg);
                    } else {
                        request.setAttribute("error", "No se pudo completar la inscripción.");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Error en la selección de materia.");
                }
            } else {
                request.setAttribute("error", "Usuario no autorizado.");
            }
        }

        doGet(request, response);
    }
}
