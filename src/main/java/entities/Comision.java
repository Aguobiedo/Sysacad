package entities;

public class Comision {
	private int numComision;
	private int anioCursado;
	private String turno;
	
	public Comision(int numComision, int anioCursado) {
		this.numComision = numComision;
		this.anioCursado = anioCursado;
	}
	public Comision() {
		// TODO Auto-generated constructor stub
	}
	public int getNumComision() {
		return numComision;
	}
	public void setNumComision(int numComision) {
		this.numComision = numComision;
	}
	public int getAnioCursado() {
		return anioCursado;
	}
	public void setAnioCursado(int anioCursado) {
		this.anioCursado = anioCursado;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	

}
