package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import entities.Clase;
import entities.Comision;
import entities.Docente;
import entities.Materia;

public class ClaseDAO implements IDao<Clase>{

	@Override
	public Clase guardar(Clase c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO clase(idclase,legajodoc,idMateria,numComision,anio_cursado,dia_semana_cursado,horario_inicio,horario_fin) VALUES(?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, c.getIdClase());
			stmt.setInt(2, c.getDocente().getLegajo());
			stmt.setInt(3, c.getMateria().getIdMateria());
			stmt.setInt(4, c.getComision().getNumComision());
			stmt.setInt(5, c.getComision().getAnioCursado());
			stmt.setString(6, c.getDiaSemanaCursado());
			stmt.setTime(7, c.getHorarioInicio());
			stmt.setTime(8, c.getHorarioFin());
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
		return c;
	}

	public void eliminarClase(int id) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM Clase WHERE idclase = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, id);
			stmt.executeUpdate();	
			keyResultSet=stmt.getGeneratedKeys();			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	@Override
	public Clase getOne(int id) {
		Clase c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idclase, legajodoc, idMateria, numComision, anio_cursado, dia_semana_cursado, horario_inicio, horario_fin FROM clase WHERE idclase=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c= new Clase();
				c.setIdClase(rs.getInt("idclase"));
				c.setDocente(new Docente(rs.getInt("legajodoc")));
				c.setMateria(new Materia(rs.getInt("idMateria")));
				c.setComision(new Comision(rs.getInt("numComision"),rs.getInt("anio_cursado")));
				c.setDiaSemanaCursado(rs.getString("dia_semana_cursado"));
				c.setHorarioInicio(rs.getTime("horario_inicio"));
				c.setHorarioFin(rs.getTime("horario_fin"));
			}
		} catch (SQLException e) {
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
		
		return c;
	}

	@Override
	public LinkedList<Clase> getAll() {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		LinkedList<Clase> Clases = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT idclase, legajodoc, idMateria, numComision, anio_cursado, dia_semana_cursado, horario_inicio, horario_fin FROM clase");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Clase c = new Clase();
					c.setIdClase(rs.getInt("idclase"));
					c.setDocente(new Docente(rs.getInt("legajodoc")));
					c.setMateria(new Materia(rs.getInt("idMateria")));
					c.setComision(new Comision(rs.getInt("numComision"),rs.getInt("anio_cursado")));
					c.setDiaSemanaCursado(rs.getString("dia_semana_cursado"));
					c.setHorarioInicio(rs.getTime("horario_inicio"));
					c.setHorarioFin(rs.getTime("horario_fin"));
					Clases.add(c);
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
		return Clases;
	}

	@Override
	public void update(Clase c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE clase SET legajodoc = ?,idMateria = ?, numComision = ?, anio_cursado = ?, "
							+ "dia_semana_cursado = ?, horario_inicio = ?, horario_fin = ? WHERE idclase = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, c.getDocente().getLegajo());
			stmt.setInt(2, c.getMateria().getIdMateria());
			stmt.setInt(3, c.getComision().getNumComision());
			stmt.setInt(4, c.getComision().getAnioCursado());
			stmt.setString(5, c.getDiaSemanaCursado());
			stmt.setTime(6, c.getHorarioInicio());
			stmt.setTime(7, c.getHorarioFin());
			stmt.setInt(8, c.getIdClase());
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

	public LinkedList<Clase> getByLegajoDocente(int legajo) {
		LinkedList<Clase> Clases = new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT c.idclase, c.legajodoc, c.idmateria, c.numComision, c.anio_cursado, c.dia_semana_cursado, c.horario_inicio, c.horario_fin, m.nombre "
					+ "FROM clase c "
					+ "INNER JOIN materia m ON c.idmateria = m.idmateria "
					+ "WHERE legajodoc=?"
					);
			stmt.setInt(1, legajo);
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Clase c = new Clase();
					c.setIdClase(rs.getInt("idclase"));
					c.setDocente(new Docente(rs.getInt("legajodoc")));
					Materia m = new Materia(rs.getInt("idMateria"));
					m.setNombre(rs.getString("m.nombre"));
					c.setMateria(m);
					c.setComision(new Comision(rs.getInt("numComision"),rs.getInt("anio_cursado")));
					c.setDiaSemanaCursado(rs.getString("dia_semana_cursado"));
					c.setHorarioInicio(rs.getTime("horario_inicio"));
					c.setHorarioFin(rs.getTime("horario_fin"));
					Clases.add(c);
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
		return Clases;
	}

}
