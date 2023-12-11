package data;

import java.sql.*;

import java.util.LinkedList;

import entities.Alumno;
import entities.Clase;
import entities.Comision;
import entities.Docente;
import entities.Inscripcion;
import entities.Materia;
import logic.Controller;

public class InscripcionDAO implements IDao<Inscripcion>{
	
	
	public LinkedList<Inscripcion> getByAlumno(Alumno a){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Inscripcion> inscripciones = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT mat.nombre, doc.apellido, cla.numcomision, cla.anio_cursado, cla.dia_semana_cursado, " +
							"cla.horario_inicio, cla.horario_fin " +
							"FROM inscripcion ins " +
							"INNER JOIN alumno alu ON ins.legajo_alumno=alu.legajo " +
							"INNER JOIN clase cla ON ins.idclase = cla.idclase " +
							"INNER JOIN docente doc ON cla.legajodoc = doc.legajo " +
							"INNER JOIN materia mat ON cla.idmateria = mat.idmateria " +
							"WHERE ins.legajo_alumno = ? " +
							"ORDER BY cla.horario_inicio DESC;");
			stmt.setInt(1, a.getLegajo());
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Inscripcion i = new Inscripcion();
					Clase c = new Clase();
					Comision com = new Comision();
					Docente doc = new Docente();
					Materia mat = new Materia();
					com.setNumComision(rs.getInt("numcomision"));
					com.setAnioCursado(rs.getInt("anio_cursado"));
					doc.setApellido(rs.getString("apellido"));
					mat.setNombre(rs.getString("nombre"));;
					c.setComision(com);
					c.setDocente(doc);
					c.setMateria(mat);
					c.setDiaSemanaCursado(rs.getString("dia_semana_cursado"));
					c.setHorarioInicio(rs.getTime("horario_inicio"));
					c.setHorarioFin(rs.getTime("horario_fin"));
					i.setAlumno(a);
					i.setClase(c);
					inscripciones.add(i);
				}
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return inscripciones;
	}

	@Override
	public Inscripcion guardar(Inscripcion i) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO inscripcion(legajo_alumno,idclase,fecha_hora) VALUES(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, i.getAlumno().getLegajo());
			stmt.setInt(2, i.getClase().getIdClase());
			stmt.setTimestamp(3, i.getFechahora());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();

			
		}  catch (SQLException e) {
            e.printStackTrace();
            return null;
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return i;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Inscripcion getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Inscripcion> getAll() {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		LinkedList<Inscripcion> inscripciones = new LinkedList<>();
		Controller ctrl = new Controller();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT legajo_alumno, idclase, fecha_hora FROM inscripcion");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Inscripcion i = new Inscripcion();
					i.setAlumno(ctrl.alumnoGetOne(rs.getInt("legajo_alumno")));
					i.setClase(ctrl.claseGetOne(rs.getInt("idclase")));
					System.out.println(rs.getTime("fecha_hora"));
					i.setFechahora(rs.getTimestamp("fecha_hora"));
					inscripciones.add(i);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return inscripciones;
	}

	@Override
	public void update(Inscripcion c) {
		// TODO Auto-generated method stub
		
	}
	

}
