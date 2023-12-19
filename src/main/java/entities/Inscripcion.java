package entities;

import java.sql.Timestamp;

public class Inscripcion {
	private Clase clase;
	private Alumno alumno;
	private Timestamp fechahora;
	
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
	public Timestamp getFechahora() {
		return fechahora;
	}
	public void setFechahora(Timestamp fechahora) {
		this.fechahora = fechahora;
	}
}
