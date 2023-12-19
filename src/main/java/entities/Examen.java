package entities;

import java.sql.Timestamp;

public class Examen {
    private Clase clase;
    private Alumno alumno;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraInscripcion;
    private String estado;
    private float nota;

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

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
