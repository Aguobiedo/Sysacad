package data;
import entities.Docente;

public class DocenteDAO extends MiembroFacultadDAO{
	
	@Override
	public String getTabla() {
		return "docente";
	}
	
	@Override
	public Docente getTipo() {
		return new Docente();
	}
	
}
