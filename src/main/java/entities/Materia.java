package entities;


public class Materia {
	private int idMateria;
	private String nombre;
	private int hsSemanales;
	private String resolucion;
	private int anioCursado;
	private String correlativa1;
	private String correlativa2;
	private int idcorrelativa1;
	private int idcorrelativa2;
	private Carrera carrera;
	
	public Materia(int id) {
		this.idMateria = id;
	}
	public Materia() {
		// TODO Auto-generated constructor stub
	}
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

	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	public String getCorrelativa1() {
		return correlativa1;
	}
	public void setCorrelativa1(String correlativa1) {
		this.correlativa1 = correlativa1;
	}
	public String getCorrelativa2() {
		return correlativa2;
	}
	public void setCorrelativa2(String correlativa2) {
		this.correlativa2 = correlativa2;
	}
	public int getIdcorrelativa1() {
		return idcorrelativa1;
	}
	public void setIdcorrelativa1(int idcorrelativa1) {
		this.idcorrelativa1 = idcorrelativa1;
	}
	public int getIdcorrelativa2() {
		return idcorrelativa2;
	}
	public void setIdcorrelativa2(int idcorrelativa2) {
		this.idcorrelativa2 = idcorrelativa2;
	}

}
