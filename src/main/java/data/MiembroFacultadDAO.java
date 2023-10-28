package data;

import java.sql.*;

import java.util.LinkedList;

import entities.Alumno;
import entities.Docente;
import entities.MiembroFacultad;
import entities.NoDocente;


public class MiembroFacultadDAO implements IDao<MiembroFacultad>{

	
	public MiembroFacultad validate(String username, String password) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		MiembroFacultad a = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT a.legajo,a.nombre,a.apellido,a.dni,a.direccion,a.email,a.usuario, 'Alumno' as TipoMiembro "
					+ "FROM alumno a WHERE a.usuario=? and a.password=? "
					+ "UNION "
					+ "SELECT nd.legajo,nd.nombre,nd.apellido,nd.dni,nd.direccion,nd.email,nd.usuario, 'NoDocente' as TipoMiembro "
					+ "FROM no_docente nd WHERE nd.usuario = ? AND nd.password = ? "
					+ "UNION "
					+ "SELECT d.legajo,d.nombre,d.apellido,d.dni,d.direccion,d.email,d.usuario, 'Docente' as TipoMiembro "
					+ "FROM docente d WHERE d.usuario = ? AND d.password = ?;"
					);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, username);
		    stmt.setString(4, password);
		    stmt.setString(5, username);
		    stmt.setString(6, password);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				if(rs.getString("TipoMiembro").equals("Alumno")) {
					a = new Alumno();
					System.out.println("ENCONTRO EL ALUMNO");
					a.setLegajo(rs.getInt("legajo"));
					a.setNombre(rs.getString("nombre"));
					a.setApellido(rs.getString("apellido"));
					a.setDni(rs.getString("dni"));
					a.setDireccion(rs.getString("direccion"));
					a.setEmail(rs.getString("email"));
					a.setUsuario(rs.getString("usuario"));
					a.setTipo(rs.getString("TipoMiembro"));
				}else if(rs.getString("TipoMiembro").equals("Docente")) {
					a = new Docente();
					a.setLegajo(rs.getInt("legajo"));
					a.setNombre(rs.getString("nombre"));
					a.setApellido(rs.getString("apellido"));
					a.setDni(rs.getString("dni"));
					a.setDireccion(rs.getString("direccion"));
					a.setEmail(rs.getString("email"));
					a.setUsuario(rs.getString("usuario"));
					a.setTipo(rs.getString("TipoMiembro"));
				}else if(rs.getString("TipoMiembro").equals("NoDocente")) {
					a = new NoDocente();
					System.out.println("ENCONTRO EL NO DOCENTE");
					a.setLegajo(rs.getInt("legajo"));
					a.setNombre(rs.getString("nombre"));
					a.setApellido(rs.getString("apellido"));
					a.setDni(rs.getString("dni"));
					a.setDireccion(rs.getString("direccion"));
					a.setEmail(rs.getString("email"));
					a.setUsuario(rs.getString("usuario"));
					a.setTipo(rs.getString("TipoMiembro"));
				}
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

	@Override
	public void update(MiembroFacultad c) {
		// TODO Auto-generated method stub
		
	}

}