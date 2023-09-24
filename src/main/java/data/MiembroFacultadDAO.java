package data;

import java.sql.*;

import java.util.LinkedList;

import entities.Alumno;
import entities.MiembroFacultad;


public class MiembroFacultadDAO implements IDao<MiembroFacultad>{

	
	public MiembroFacultad validate(String username, String password){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		MiembroFacultad a = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT legajo,nombre,apellido,dni,direccion,email,usuario, 'Alumno' as TipoMiembro "
					+ "FROM alumno WHERE usuario=? and password=? "
					+ "UNION "
					+ "SELECT legajo,nombre,apellido,dni,direccion,email,usuario, 'NoDocente' as TipoMiembro "
					+ "FROM no_docente WHERE usuario = ? AND password = ? "
					+ "UNION "
					+ "SELECT legajo,nombre,apellido,dni,direccion,email,usuario, 'Docente' as TipoMiembro "
					+ "FROM docente WHERE usuario = ? AND password = ?;"
					);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, username);
		    stmt.setString(4, password);
		    stmt.setString(5, username);
		    stmt.setString(6, password);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a = new Alumno();
				a.setLegajo(rs.getInt("legajo"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
				a.setDni(rs.getString("dni"));
				a.setDireccion(rs.getString("direccion"));
				a.setEmail(rs.getString("email"));
				a.setUsuario(rs.getString("usuario"));
				a.setTipo(rs.getString("TipoMiembro"));				
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
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MiembroFacultad getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<MiembroFacultad> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MiembroFacultad guardar(MiembroFacultad c) {
		// TODO Auto-generated method stub
		return null;
	}

}