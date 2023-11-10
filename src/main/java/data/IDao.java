package data;

import java.util.LinkedList;

import entities.Docente;

public interface IDao<Clase> {
	
	public Clase guardar(Clase c);
	public void eliminar(int id);
	public Clase getOne(int id);
	public LinkedList<Clase> getAll();
	public void update(Clase c);

}
