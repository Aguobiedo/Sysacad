package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.LinkedList;

import data.AlumnoDAO;
import data.InscripcionDAO;
import data.MiembroFacultadDAO;
import entities.Alumno;
import entities.Inscripcion;
import entities.MiembroFacultad;

public class Controller {
	public MiembroFacultad validate(String username, String password) throws SQLException {
		MiembroFacultadDAO mfDao = new MiembroFacultadDAO();
		return (MiembroFacultad)mfDao.validate(username, calcularSHA256(password));
	}
	
	public LinkedList<Inscripcion> getInscripcionesByAlumno(Alumno a){
		InscripcionDAO insDao = new InscripcionDAO();
		return insDao.getByAlumno(a);
	}
	
	public LinkedList<Alumno> alumnosGetAll(){
		AlumnoDAO aDao = new AlumnoDAO();
		return aDao.getAll();
	}
	
	public boolean addAlumno(Alumno a, String password) {
		AlumnoDAO aDao = new AlumnoDAO();
		return aDao.addAlumno(a, calcularSHA256(password));
	}
	
	public void deleteAlumno(int legajo) {
		AlumnoDAO aDao = new AlumnoDAO();
		aDao.eliminar(legajo);
	}
	
	public Alumno alumnoGetOne(int legajo) {
		AlumnoDAO aDao = new AlumnoDAO();
		return aDao.getOne(legajo);
	}
	
	public void updateAlumno(Alumno alumno) {
		AlumnoDAO aDao = new AlumnoDAO();
		aDao.update(alumno);
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
