package data;

import entities.Alumno;
import entities.Clase;
import entities.Docente;
import entities.Examen;

import java.sql.*;
import java.util.LinkedList;

public class ExamenDAO implements IDao<Examen> {

	public LinkedList<Examen> getExamenesByAlumno(Alumno alumno) {
	    LinkedList<Examen> examenes = new LinkedList<>();

	    try (PreparedStatement statement = DbConnector.getInstancia().getConn().prepareStatement(
	            "SELECT e.*, c.idclase, c.legajodoc, c.idmateria, c.numcomision, c.anio_cursado, c.dia_semana_cursado, c.horario_inicio, c.horario_fin " +
	                    "FROM examen e " +
	                    "INNER JOIN clase c ON e.idclase = c.idclase " +
	                    "INNER JOIN alumno alu ON e.legajo_alumno = alu.legajo " +
	                    "WHERE e.legajo_alumno = ?")) {

	        statement.setInt(1, alumno.getLegajo());

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Examen examen = new Examen();

	                Clase clase = new Clase();
	                clase.setIdClase(resultSet.getInt("idclase"));

	                // Mostrar los atributos de la clase
	                System.out.println("Legajo Alumno: " + alumno.getLegajo());
	                System.out.println("ID Clase: " + clase.getIdClase());
	                System.out.println("Fecha y Hora Inicio: " + resultSet.getTimestamp("fecha_hora_inicio"));
	                System.out.println("Fecha y Hora Inscripción: " + resultSet.getTimestamp("fecha_hora_inscripcion"));

	                // Resto del código

	                examen.setAlumno(alumno);
	                examen.setClase(clase);
	                examen.setFechaHoraInicio(resultSet.getTimestamp("fecha_hora_inicio"));
	                examen.setFechaHoraInscripcion(resultSet.getTimestamp("fecha_hora_inscripcion"));

	                // Agregar el examen a la lista
	                examenes.add(examen);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // Retornar la lista de exámenes
	    return examenes;
	}





    private Clase getClaseById(int idClase) {
        // Implementa la lógica para obtener la Clase por su ID desde la base de datos
        // Puedes crear otro DAO para la Clase o adaptar el existente
        return null;
    }

    @Override
    public Examen guardar(Examen c) {
        // Implementa la lógica para guardar un examen en la base de datos
        return null;
    }

    @Override
    public void eliminar(int id) {
        // Implementa la lógica para eliminar un examen de la base de datos
    }

    @Override
    public Examen getOne(int id) {
        // Implementa la lógica para obtener un examen por su ID de la base de datos
        return null;
    }

    @Override
    public LinkedList<Examen> getAll() {
        // Implementa la lógica para obtener todos los exámenes de la base de datos
        return null;
    }

    @Override
    public void update(Examen c) {
        // Implementa la lógica para actualizar un examen en la base de datos
    }
}
