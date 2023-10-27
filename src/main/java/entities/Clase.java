package entities;

import java.sql.Time;

public class Clase {
	private int idClase;
	private String diaSemanaCursado;
	private Time horarioInicio;
	private Time horarioFin;
	private Docente docente;
	private Materia materia;
	private Comision comision;
	
	public int getIdClase() {
		return idClase;
	}
	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}
	public String getDiaSemanaCursado() {
		return diaSemanaCursado;
	}
	public void setDiaSemanaCursado(String diasSemanaCursado) {
		this.diaSemanaCursado = diasSemanaCursado;
	}
	public Time getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(Time horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public Time getHorarioFin() {
		return horarioFin;
	}
	public void setHorarioFin(Time horarioFin) {
		this.horarioFin = horarioFin;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public Comision getComision() {
		return comision;
	}
	public void setComision(Comision comision) {
		this.comision = comision;
	}
	

}
