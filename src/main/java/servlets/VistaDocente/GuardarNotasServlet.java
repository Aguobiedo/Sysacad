import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alumno;
import entities.Examen;
import entities.Inscripcion;
import logic.Controller;

@WebServlet("/guardarNotas")
public class GuardarNotasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LinkedList<Examen> examenes = (LinkedList<Examen>) request.getAttribute("examenes");
            Controller ctrl = new Controller();
            String[] legajos = request.getParameterValues("legajos");
            String[] notas = request.getParameterValues("notas");

            if (legajos != null && notas != null) {
                for (int i = 0; i < legajos.length && i < notas.length; i++) {
                    String legajoStr = legajos[i];
                    String notaStr = notas[i];

                    if (legajoStr != null && notaStr != null && !notaStr.isEmpty()) {
                    	int legajo = Integer.parseInt(legajoStr);
                        int nota = Integer.parseInt(notaStr);
                        Examen e = new Examen();
                        Alumno a = new Alumno();
                        a.setLegajo(legajo);
                        e.setAlumno(a);
                        e.setNota(nota);
                        e.setFechaHoraInscripcion(Timestamp.valueOf(LocalDateTime.now()));
                        if (nota>=6) {
                        	e.setEstado("Aprobado");
                        } else {
                        	e.setEstado("Desaprobado");
                        }
                        ctrl.updateExamen(e);
                    }
                }
            }

            // Aquí podrías realizar alguna lógica adicional o redirigir a otra página.
            response.sendRedirect("tuPagina.jsp");
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            // Puedes redirigir a una página de error si es necesario.
            response.sendRedirect("error.jsp");
        }
    }
}

