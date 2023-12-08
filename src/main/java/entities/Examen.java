package entities;

import java.sql.Timestamp;

public class Examen {
    private Clase clase;
    private Alumno alumno;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraInscripcion;

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Timestamp getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Timestamp getFechaHoraInscripcion() {
        return fechaHoraInscripcion;
    }

    public void setFechaHoraInscripcion(Timestamp fechaHoraFin) {
        this.fechaHoraInscripcion = fechaHoraFin;
    }
}
