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
            String sql = "SELECT m.nombre, c.anio_cursado, c.numcomision, ex.fecha_hora_inscripcion, d.nombre, d.apellido, ex.nota, ex.estado " +
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
                        examen.setEstado(resultSet.getString("estado"));

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
            String sql = "SELECT DISTINCT m.nombre, c.anio_cursado, c.numcomision, d.nombre AS nombre_docente, d.apellido AS apellido_docente, c.idclase as idClase " +
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
        String sql = "INSERT INTO examen (legajo_alumno, idclase, fecha_hora_inscripcion,estado) " +
                     "VALUES (?, ?, CURRENT_TIMESTAMP, 'En curso')";

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
    public void update(Examen e) {
    	PreparedStatement stmt=null;
    	try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE examenes e " +
					"SET e.nota = ?, e.estado = ? " +
					"WHERE e.legajo_alumno = ? AND e.idclase = ? AND e.fecha_hora_inscripcion = ?",PreparedStatement.NO_GENERATED_KEYS);
			stmt.setFloat(1, e.getNota());
			stmt.setString(2, e.getEstado());
			stmt.setInt(3, e.getAlumno().getLegajo());
			stmt.setInt(4, e.getClase().getIdClase());
			stmt.setTimestamp(5, e.getFechaHoraInscripcion());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} finally {
			try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
		}
    }
    
    public Examen getLastByLegajoAlumnoIdClase(int legajo_alumno, int idClase) {
    	Examen e = new Examen();
    	ResultSet rs=null;
    	PreparedStatement stmt=null;
	    try {
	    	stmt=DbConnector.getInstancia().getConn().prepareStatement(
	    		"WITH ExamenesOrdenados AS ( " +
	    				"SELECT legajo_alumno, idclase, fecha_hora_inscripcion, nota, " +
	    				"ROW_NUMBER() OVER (PARTITION BY legajo_alumno, idclase ORDER BY fecha_hora_inscripcion DESC) AS rownum FROM examen) " +
	    				"SELECT legajo_alumno, idclase, fecha_hora_inscripcion, nota " +
	    				"FROM ExamenesOrdenados " +
	    				"WHERE rownum = 1 and legajo_alumno = ? and idclase = ?");
	    	stmt.setInt(1, legajo_alumno);
	    	stmt.setInt(2, idClase);
	        rs=stmt.executeQuery();
	        e.setAlumno(new Alumno(legajo_alumno));
	        e.setClase(new Clase(idClase));
			if(rs!=null && rs.next()) {
				e.setNota(rs.getInt("nota"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return e;
	        
    }

	public LinkedList<Examen> getExamenesEnCursoByClase(Clase c) {
		LinkedList<Examen> examenes = new LinkedList<>();

        try {
            // Consulta SQL
            String sql = "SELECT alu.legajo, alu.nombre, alu.apellido, alu.email, ex.fecha_hora_inscripcion, ex.estado " +
                    "FROM examen ex " +
                    "INNER JOIN alumno alu ON ex.legajo_alumno = alu.legajo " +
                    "WHERE ex.idclase = ? AND ex.estado='En curso'";

            // Crear la conexión y la declaración preparada
            try (PreparedStatement statement = DbConnector.getInstancia().getConn().prepareStatement(sql)) {
                statement.setInt(1, c.getIdClase());

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {

                        // Crea instancias clases y configurcion con los datos del resultado
                        
                        Alumno a = new Alumno();
                        a.setLegajo(resultSet.getInt("legajo"));
                        a.setNombre(resultSet.getString("nombre"));
                        a.setApellido(resultSet.getString("apellido"));
                        a.setEmail(resultSet.getString("email"));
                        Examen examen = new Examen();
                        examen.setFechaHoraInscripcion(resultSet.getTimestamp("fecha_hora_inscripcion"));
                        examen.setNota(resultSet.getFloat("nota"));
                        examen.setClase(c);
                        examen.setAlumno(a);
                        examen.setEstado(resultSet.getString("estado"));
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
    
}

