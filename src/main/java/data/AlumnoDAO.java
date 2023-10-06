package data;

import java.util.LinkedList;

import entities.Alumno;
import java.sql.*;

public class AlumnoDAO implements IDao<Alumno>{

	@Override
	public Alumno guardar(Alumno c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
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
	

}
