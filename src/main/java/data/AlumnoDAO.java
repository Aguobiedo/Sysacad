package data;

import java.util.LinkedList;

import entities.Alumno;
import java.sql.*;

public class AlumnoDAO extends MiembroFacultadDAO{

	@Override
	public String getTabla() {
		return "alumno";
	}
	
	@Override
	public Alumno getTipo() {
		return new Alumno();
	}

}
