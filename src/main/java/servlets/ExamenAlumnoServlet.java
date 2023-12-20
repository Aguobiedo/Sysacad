package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.Examen;
import logic.Controller;

@WebServlet("/ExamenAlumno")
public class ExamenAlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controller ctrl = new Controller();
        Alumno alumno = (Alumno) request.getSession().getAttribute("alumno");

        if (alumno != null) {
            LinkedList<Examen> examenes = ctrl.getExamenAlumno(alumno);
            request.setAttribute("examenes", examenes);
        }

        // Redirigir a la página JSP
        request.getRequestDispatcher("WEB-INF/principal/ExamenesAlumno.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
