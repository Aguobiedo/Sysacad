package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.LinkedList;
import data.*;
import entities.*;

public class Controller {
	
	
	
	public MiembroFacultad validate(String username, String password) throws SQLException {
		MiembroFacultadDAO mfDao = new MiembroFacultadDAO();
		return (MiembroFacultad)mfDao.validate(username, calcularSHA256(password));
	}
	
	//METODOS DE ALUMNO
	
	public LinkedList<Inscripcion> getInscripcionesByAlumno(Alumno a){
		InscripcionDAO insDao = new InscripcionDAO();
		return insDao.getByAlumno(a);
	}
	
	public LinkedList<MiembroFacultad> alumnosGetAll(){
		AlumnoDAO aDao = new AlumnoDAO();
		return aDao.getAll();
	}
	
	
	
	public boolean addAlumno(Alumno a, String password) {
		AlumnoDAO aDao = new AlumnoDAO();
		return aDao.add(a, calcularSHA256(password));
	}
	
	
	
	public void deleteAlumno(int legajo) {
		AlumnoDAO aDao = new AlumnoDAO();
		aDao.eliminar(legajo);
	}
	
	
	
	public Alumno alumnoGetOne(int legajo) {
		AlumnoDAO aDao = new AlumnoDAO();
		return (Alumno) aDao.getOne(legajo);
	}
	
	
	public void updateAlumno(Alumno alumno) {
		AlumnoDAO aDao = new AlumnoDAO();
		aDao.update(alumno);
	}
	//FIN METODOS DE ALUMNO
	
	
	//METODOS DOCENTE
	public LinkedList<MiembroFacultad> docentesGetAll(){
		DocenteDAO dDao = new DocenteDAO();
		return dDao.getAll();
	}
	
	public boolean addDocente(Docente a, String password) {
		// TODO Auto-generated method stub
		DocenteDAO dDao = new DocenteDAO();
		return dDao.add(a, calcularSHA256(password));
	}
	
	public void deleteDocente(int legajo) {
		DocenteDAO dDao = new DocenteDAO();
		dDao.eliminar(legajo);
	}
	
	public Docente docenteGetOne(int legajo) {
		DocenteDAO dDao = new DocenteDAO();
		return (Docente) dDao.getOne(legajo);
	}
	
	
	public void updateDocente(Docente docente) {
		DocenteDAO dDao = new DocenteDAO();
		dDao.update(docente);
	}
	
	//FIN METODOS DOCENTE
	
	//METODOS NO DOCENTE
	public LinkedList<MiembroFacultad> noDocentesGetAll(){
		NoDocenteDAO nDao = new NoDocenteDAO();
		return nDao.getAll();
	}
	
	public boolean addNoDocente(NoDocente a, String password) {
		// TODO Auto-generated method stub
		NoDocenteDAO nDao = new NoDocenteDAO();
		return nDao.add(a, calcularSHA256(password));
	}
	
	public void deleteNoDocente(int legajo) {
		NoDocenteDAO dDao = new NoDocenteDAO();
		dDao.eliminar(legajo);
	}
	
	public NoDocente noDocenteGetOne(int legajo) {
		NoDocenteDAO nDao = new NoDocenteDAO();
		return (NoDocente) nDao.getOne(legajo);
	}
	
	
	public void updateNoDocente(NoDocente noDocente) {
		NoDocenteDAO nDao = new NoDocenteDAO();
		nDao.update(noDocente);
	}
	
	//FIN METODOS NO DOCENTE
	
	//METODOS CARRERAS
	
	public LinkedList<Carrera> carrerasGetAll(){
		CarreraDAO cDao = new CarreraDAO();
		return cDao.getAll();
	}
	
	public boolean addCarrera(Carrera c) {
		// TODO Auto-generated method stub
		CarreraDAO cDao = new CarreraDAO();
		if(cDao.guardar(c) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deleteCarrera(int id) {
		CarreraDAO cDao = new CarreraDAO();
		cDao.eliminar(id);
	}
	
	public Carrera carreraGetOne(int id) {
		CarreraDAO cDao = new CarreraDAO();
		return (Carrera) cDao.getOne(id);
	}
	
	public void updateCarrera(Carrera carrera) {
		CarreraDAO cDao = new CarreraDAO();
		cDao.update(carrera);
	}
	
	//FIN METODOS CARRERAS
	
	//METODOS PLANES
	
	public LinkedList<Plan> planesGetAll(){
		PlanDAO pDao = new PlanDAO();
		return pDao.getAll();
	}
	
	public boolean addPlan(Plan p) {
		PlanDAO pDao = new PlanDAO();
		if(pDao.guardar(p) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deletePlan(int id) {
		PlanDAO pDao = new PlanDAO();
		pDao.eliminar(id);
	}
	
	public Plan planGetOne(int id) {
		PlanDAO pDao = new PlanDAO();
		return (Plan) pDao.getOne(id);
	}
	
	public void updatePlan(Plan plan) {
		PlanDAO pDao = new PlanDAO();
		pDao.update(plan);
	}
	
	//FIN METODOS PLANES
	
	//METODOS COMISIONES
	
	public LinkedList<Comision> comisionesGetAll(){
		ComisionDAO cDao = new ComisionDAO();
		return cDao.getAll();
	}
	
	public boolean addComision(Comision c) {
		ComisionDAO cDao = new ComisionDAO();
		if(cDao.guardar(c) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deleteComision(int nro, int anio) {
		ComisionDAO cDao = new ComisionDAO();
		cDao.eliminarComision(nro,anio);
	}
	
	public Comision comisionGetOne(int nro, int anio) {
		ComisionDAO cDao = new ComisionDAO();
		return (Comision) cDao.getOne(nro,anio);
	}
	
	public void updateComision(Comision comision) {
		ComisionDAO cDao = new ComisionDAO();
		cDao.update(comision);
	}
	
	//FIN METODOS COMISIONES
	
	//METODOS CLASES
	public LinkedList<Clase> clasesGetAll() {
		ClaseDAO cDao = new ClaseDAO();
		return cDao.getAll();
	}
	
	public LinkedList<Clase> getClaseByLegajoDocente(int legajo) {
		// TODO Auto-generated method stub
		ClaseDAO cDao = new ClaseDAO();
		return cDao.getByLegajoDocente(legajo);
	}
	
	public boolean addClase(Clase c) {
		ClaseDAO cDao = new ClaseDAO();
		if(cDao.guardar(c) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deleteClase(int id) {
		ClaseDAO cDao = new ClaseDAO();
		cDao.eliminarClase(id);
	}
	
	public Clase claseGetOne(int id) {
		ClaseDAO cDao = new ClaseDAO();
		return (Clase) cDao.getOne(id);
	}
	
	public void updateClase(Clase clase) {
		ClaseDAO cDao = new ClaseDAO();
		cDao.update(clase);
	}
	
	//FIN METODOS CLASES
	
	public static String calcularSHA256(String texto) {
        try {
            // Crear una instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Obtener los bytes del texto
            byte[] bytes = texto.getBytes();

            // Calcular el hash
            byte[] hashBytes = md.digest(bytes);

            // Convertir el hash a una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejo de excepciones en caso de que no se encuentre el algoritmo
            e.printStackTrace();
            return null; // O puedes lanzar una excepción personalizada aquí
        }
    }

	

}
