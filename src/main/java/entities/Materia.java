package entities;

import java.util.LinkedList;

public class Materia {
	private int idMateria;
	private String nombre;
	private int hsSemanales;
	private String resolucion;
	private int anioCursado;
	private LinkedList<Materia> correlativas;
	private Carrera carrera;
	
	public int getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHsSemanales() {
		return hsSemanales;
	}
	public void setHsSemanales(int hsSemanales) {
		this.hsSemanales = hsSemanales;
	}
	public String getResolucion() {
		return resolucion;
	}
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}
	public int getAnioCursado() {
		return anioCursado;
	}
	public void setAnioCursado(int anioCursado) {
		this.anioCursado = anioCursado;
	}
	public LinkedList<Materia> getCorrelativas() {
		return correlativas;
	}
	public void setCorrelativas(LinkedList<Materia> correlativas) {
		this.correlativas = correlativas;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

}
