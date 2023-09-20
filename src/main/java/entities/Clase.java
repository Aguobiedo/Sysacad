package entities;

import java.util.LinkedList;
import java.time.LocalTime;

public class Clase {
	private int idClase;
	private LinkedList<String> diasSemanaCursado;
	private LocalTime horarioInicio;
	private LocalTime horarioFin;
	private Docente docente;
	private Materia materia;
	private Comision comision;
	
	public int getIdClase() {
		return idClase;
	}
	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}
	public LinkedList<String> getDiasSemanaCursado() {
		return diasSemanaCursado;
	}
	public void setDiasSemanaCursado(LinkedList<String> diasSemanaCursado) {
		this.diasSemanaCursado = diasSemanaCursado;
	}
	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public LocalTime getHorarioFin() {
		return horarioFin;
	}
	public void setHorarioFin(LocalTime horarioFin) {
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
