package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Carrera;
import entities.MiembroFacultad;

public class CarreraDAO implements IDao<Carrera>{

	@Override
	public Carrera guardar(Carrera c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO carrera(idcarrera,nombre) VALUES(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, c.getIdCarrera());
			stmt.setString(2, c.getNombre());
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

	@Override
	public void eliminar(int id) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM carrera WHERE idcarrera = ?",
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
	public Carrera getOne(int id) {
		Carrera a= new Carrera();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idcarrera, nombre FROM carrera WHERE idcarrera=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a.setIdCarrera(rs.getInt("idcarrera"));
				a.setNombre(rs.getString("nombre"));
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
		
		return a;
	}

	@Override
	public LinkedList<Carrera> getAll() {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		LinkedList<Carrera> carreras = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT idcarrera, nombre FROM carrera");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Carrera c = new Carrera();
					c.setIdCarrera(rs.getInt("idcarrera"));
					c.setNombre(rs.getString("nombre"));
					carreras.add(c);
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
		return carreras;
	}

	@Override
	public void update(Carrera c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE carrera SET nombre = ? WHERE idcarrera = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, c.getNombre());
			stmt.setInt(2, c.getIdCarrera());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setIdCarrera(keyResultSet.getInt(1));
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
