package data;

import java.sql.*;
import java.util.LinkedList;

import entities.Alumno;
import entities.Clase;
import entities.Comision;
import entities.Docente;
import entities.Inscripcion;
import entities.Materia;

public class InscripcionDAO implements IDao<Inscripcion>{
	
	
	public LinkedList<Inscripcion> getByAlumno(Alumno a){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Inscripcion> inscripciones = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT mat.nombre,doc.apellido,cla.numcomision, cla.anio_cursado, cla.dia_semana_cursado, cla.horario_inicio, cla.horario_fin FROM inscripcion ins "
					+ "INNER JOIN "
					+ "alumno alu ON ins.legajo_alumno=alu.legajo "
					+ "INNER JOIN "
					+ "clase cla ON ins.idclase = cla.idclase "
					+ "INNER JOIN "
					+ "docente doc ON cla.legajodoc = doc.legajo "
					+ "INNER JOIN "
					+ "materia mat ON cla.idmateria = mat.idmateria "
					+ "WHERE ins.legajo_alumno = ?;");
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
	public Inscripcion guardar(Inscripcion c) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
	

}
