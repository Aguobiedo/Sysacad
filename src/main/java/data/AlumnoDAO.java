package data;

import java.util.LinkedList;

import entities.Alumno;
import java.sql.*;

public class AlumnoDAO implements IDao<Alumno>{

	
	public boolean addAlumno(Alumno a, String password) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO alumno(legajo,nombre,apellido,dni, direccion,email,usuario, password) VALUES(?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, a.getLegajo());
			stmt.setString(2, a.getNombre());
			stmt.setString(3, a.getApellido());
			stmt.setString(4, a.getDni());
			stmt.setString(5, a.getDireccion());
			stmt.setString(6, a.getEmail());
			stmt.setString(7, a.getUsuario());
			stmt.setString(8, password);
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();

			
		}  catch (SQLException e) {
            e.printStackTrace();
            return false;
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return true;
	}

	@Override
	public void eliminar(int legajo) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM alumno WHERE legajo = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, legajo);
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
	public Alumno getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Alumno> getAll() {
		// TODO Auto-generated method stub
		PreparedStatement stmt=null;
		ResultSet rs = null;
		LinkedList<Alumno> alumnos = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT legajo,nombre,apellido,dni,direccion,email,usuario FROM alumno");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Alumno a = new Alumno();
					a.setLegajo(rs.getInt("legajo"));
					a.setNombre(rs.getString("nombre"));
					a.setApellido(rs.getString("apellido"));
					a.setDni(rs.getString("dni"));
					a.setDireccion(rs.getString("direccion"));
					a.setEmail(rs.getString("email"));
					a.setUsuario(rs.getString("usuario"));
					alumnos.add(a);
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
		return alumnos;
	}

	@Override
	public Alumno guardar(Alumno c) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
