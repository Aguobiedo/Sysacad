package data;

import entities.Alumno;
import entities.Clase;
import entities.Comision;
import entities.Docente;
import entities.Examen;
import entities.Materia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ExamenDAO implements IDao<Examen> {

    public LinkedList<Examen> getExamenesByAlumno(Alumno alumno) {
        LinkedList<Examen> examenes = new LinkedList<>();

        try {
            // Consulta SQL
            String sql = "SELECT m.nombre, c.anio_cursado, c.numcomision, ex.fecha_hora_inscripcion, d.nombre, d.apellido, ex.nota " +
                    "FROM examen ex " +
                    "INNER JOIN alumno alu ON ex.legajo_alumno = alu.legajo " +
                    "INNER JOIN clase c ON ex.idclase = c.idclase " +
                    "INNER JOIN materia m ON c.idmateria = m.idmateria " +
                    "INNER JOIN docente d ON c.legajodoc = d.legajo " +
                    "WHERE alu.legajo = ?";

            // Crear la conexión y la declaración preparada
            try (PreparedStatement statement = DbConnector.getInstancia().getConn().prepareStatement(sql)) {
                statement.setInt(1, alumno.getLegajo());

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {

                        // Crea instancias clases y configurcion con los datos del resultado
                        Materia materia = new Materia();
                        materia.setNombre(resultSet.getString("nombre"));
                        
                        Comision comision = new Comision();
                        comision.setAnioCursado(resultSet.getInt("anio_cursado"));
                        comision.setNumComision(resultSet.getInt("numcomision"));

                        Clase clase = new Clase();
                        clase.setComision(comision);
                        clase.setMateria(materia);

                        Docente docente = new Docente();
                        docente.setNombre(resultSet.getString("nombre"));
                        docente.setApellido(resultSet.getString("apellido"));

                        Examen examen = new Examen();
                        examen.setFechaHoraInscripcion(resultSet.getTimestamp("fecha_hora_inscripcion"));
                        examen.setNota(resultSet.getFloat("nota"));
                        examen.setClase(clase);
                        examen.setAlumno(alumno);
                        examen.getClase().setDocente(docente);

                        // Agregar el examen a la lista
                        examenes.add(examen);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades (lanzarla nuevamente, convertirla a una excepción específica, etc.)
        }

        return examenes;
    }
    
    public LinkedList<Clase> materiasDisponiblesRendir(Alumno alumno) {
        LinkedList<Clase> listMateriasDisponiblesRendir = new LinkedList<>();

        try {
            // Consulta SQL
            String sql = "SELECT m.nombre, c.anio_cursado, c.numcomision, d.nombre AS nombre_docente, d.apellido AS apellido_docente, c.idclase as idClase " +
                    "FROM alumno alu " +
                    "INNER JOIN inscripcion i ON alu.legajo = i.legajo_alumno " +
                    "INNER JOIN clase c ON i.idclase = c.idclase " +
                    "INNER JOIN materia m ON c.idmateria = m.idmateria " +
                    "INNER JOIN docente d ON c.legajodoc = d.legajo " +
                    "WHERE c.idclase NOT IN (" +
                    "        SELECT idclase " +
                    "        FROM examen " +
                    "        WHERE legajo_alumno = ?" +
                    "    )";

            try (PreparedStatement statement = DbConnector.getInstancia().getConn().prepareStatement(sql)) {
                statement.setInt(1, alumno.getLegajo());

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {

                        // Crear instancias de clases y configurar con los datos del resultado
                        Materia materia = new Materia();
                        materia.setNombre(resultSet.getString("nombre"));

                        Comision comision = new Comision();
                        comision.setAnioCursado(resultSet.getInt("anio_cursado"));
                        comision.setNumComision(resultSet.getInt("numcomision"));

                        Docente docente = new Docente();
                        docente.setNombre(resultSet.getString("nombre_docente"));
                        docente.setApellido(resultSet.getString("apellido_docente"));

                        Clase clase = new Clase();
                        clase.setIdClase(resultSet.getInt("idClase"));
                        clase.setComision(comision);
                        clase.setMateria(materia);
                        clase.setDocente(docente);

                        listMateriasDisponiblesRendir.add(clase);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }

        return listMateriasDisponiblesRendir;
    }
    
    public boolean inscribirExamen(int legajoAlumno, int idClase) {
    	System.out.println(legajoAlumno);
    	System.out.println(idClase);
        String sql = "INSERT INTO examen (legajo_alumno, idclase, fecha_hora_inscripcion) " +
                     "VALUES (?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement statement = DbConnector.getInstancia().getConn().prepareStatement(sql)) {
            statement.setInt(1, legajoAlumno);
            statement.setInt(2, idClase);

            // Ejecutar la consulta de inscripción
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
            return false;
        }
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

