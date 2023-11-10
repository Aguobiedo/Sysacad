package data;

import entities.NoDocente;

public class NoDocenteDAO extends MiembroFacultadDAO{
	
	@Override
	public String getTabla() {
		return "no_docente";
	}
	
	@Override
	public NoDocente getTipo() {
		return new NoDocente();
	}
}
