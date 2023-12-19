package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Materia;
import entities.Carrera;
import logic.Controller;

public class MateriaDAO implements IDao<Materia>{

	@Override
	public Materia guardar(Materia m) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO materia(nombre,hs_semanales,resolucion,anio_cursado,idcarrera,correlativa_1,correlativa_2) VALUES(?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, m.getNombre());
			stmt.setInt(2, m.getHsSemanales());
			stmt.setString(3, m.getResolucion());
			stmt.setInt(4, m.getAnioCursado());
			stmt.setInt(5, m.getCarrera().getIdCarrera());
			if(m.getIdcorrelativa1() != 0) {
				stmt.setInt(6, m.getIdcorrelativa1());
			}else {
				stmt.setNull(6,java.sql.Types.NULL);
			}
			if(m.getIdcorrelativa2() != 0) {
				stmt.setInt(7, m.getIdcorrelativa2());
			}else {
				stmt.setNull(7, java.sql.Types.NULL);
			}
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
		return m;
	}

	@Override
	public void eliminar(int id) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM materia WHERE idmateria = ?",
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
	public Materia getOne(int id) {
		Materia m= new Materia();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Controller ctrl = new Controller();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT m.idmateria, m.nombre, m.hs_semanales, m.resolucion, m.anio_cursado, m.idcarrera, n.idmateria \"correlativa_1\", a.idmateria \"correlativa_2\" FROM materia m LEFT JOIN materia n ON m.correlativa_1 = n.idmateria LEFT JOIN materia a ON m.correlativa_2 = a.idmateria WHERE m.idmateria=?;"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				m.setIdMateria(rs.getInt("idmateria"));
				m.setNombre(rs.getString("nombre"));
				m.setHsSemanales(rs.getInt("hs_semanales"));
				m.setResolucion(rs.getString("resolucion"));
				m.setAnioCursado(rs.getInt("anio_cursado"));
				m.setCarrera(ctrl.carreraGetOne(rs.getInt("idcarrera")));
				m.setIdcorrelativa1(rs.getInt("correlativa_1"));
				m.setIdcorrelativa2(rs.getInt("correlativa_2"));
				
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
		
		return m;
	}

	@Override
	public LinkedList<Materia> getAll() {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		LinkedList<Materia> materias = new LinkedList<>();
		Controller ctrl= new Controller();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT m.idmateria, m.nombre, m.hs_semanales, m.resolucion, m.anio_cursado, m.idcarrera, n.nombre \"correlativa_1\", a.nombre \"correlativa_2\" FROM materia m LEFT JOIN materia n ON m.correlativa_1 = n.idmateria LEFT JOIN materia a ON m.correlativa_2 = a.idmateria;");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Materia m = new Materia();
					m.setIdMateria(rs.getInt("idmateria"));
					m.setNombre(rs.getString("nombre"));
					m.setHsSemanales(rs.getInt("hs_semanales"));
					m.setResolucion(rs.getString("resolucion"));
					m.setAnioCursado(rs.getInt("anio_cursado"));
					m.setCarrera(ctrl.carreraGetOne(rs.getInt("idcarrera")));
					m.setCorrelativa1(rs.getString("correlativa_1"));
					m.setCorrelativa2(rs.getString("correlativa_2"));
					materias.add(m);
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
		return materias;
	}

	@Override
	public void update(Materia m) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE materia SET nombre = ?,hs_semanales = ?,resolucion = ?,anio_cursado = ?,idcarrera = ?,correlativa_1 = ?,correlativa_2 = ? WHERE idmateria = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1,m.getNombre());
			stmt.setInt(2, m.getHsSemanales());
			stmt.setString(3, m.getResolucion());
			stmt.setInt(4, m.getAnioCursado());
			stmt.setInt(5, m.getCarrera().getIdCarrera());
			if(m.getIdcorrelativa1() != 0) {
				stmt.setInt(6, m.getIdcorrelativa1());
			}else {
				stmt.setNull(6,java.sql.Types.NULL);
			}
			if(m.getIdcorrelativa2() != 0) {
				stmt.setInt(7, m.getIdcorrelativa2());
			}else {
				stmt.setNull(7, java.sql.Types.NULL);
			}
			stmt.setInt(8, m.getIdMateria());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                m.setIdMateria(keyResultSet.getInt(1));
            }

			
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

}
