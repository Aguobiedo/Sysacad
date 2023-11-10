package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.LinkedList;

import data.AlumnoDAO;
import data.DocenteDAO;
import data.InscripcionDAO;
import data.MiembroFacultadDAO;
import entities.Alumno;
import entities.Docente;
import entities.Inscripcion;
import entities.MiembroFacultad;

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
		return dDao.add(a, password);
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
