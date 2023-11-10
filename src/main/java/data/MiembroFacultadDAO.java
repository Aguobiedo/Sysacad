package data;

import java.sql.*;

import java.util.LinkedList;

import entities.Alumno;
import entities.Docente;
import entities.MiembroFacultad;
import entities.NoDocente;


public class MiembroFacultadDAO implements IDao<MiembroFacultad>{
	
	
	public final MiembroFacultad validate(String username, String password) throws SQLException{
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
	public void eliminar(int legajo) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM " +this.getTabla() + " WHERE legajo = ?",
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
	public MiembroFacultad getOne(int legajo) {
		// TODO Auto-generated method stub
		MiembroFacultad a=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT legajo,nombre,apellido,dni,direccion,email,usuario FROM " + this.getTabla() + " WHERE legajo=?"
					);
			stmt.setInt(1, legajo);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a=getTipo();
				a.setLegajo(rs.getInt("legajo"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
				a.setDni(rs.getString("dni"));
				a.setDireccion(rs.getString("direccion"));
				a.setEmail(rs.getString("email"));
				a.setUsuario(rs.getString("usuario"));
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
	public LinkedList<MiembroFacultad> getAll() {
		// TODO Auto-generated method stub
		PreparedStatement stmt=null;
		ResultSet rs = null;
		LinkedList<MiembroFacultad> miembros = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT legajo,nombre,apellido,dni,direccion,email,usuario FROM " + this.getTabla());
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					MiembroFacultad m = getTipo();
					m.setLegajo(rs.getInt("legajo"));
					m.setNombre(rs.getString("nombre"));
					m.setApellido(rs.getString("apellido"));
					m.setDni(rs.getString("dni"));
					m.setDireccion(rs.getString("direccion"));
					m.setEmail(rs.getString("email"));
					m.setUsuario(rs.getString("usuario"));
					miembros.add(m);
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
		return miembros;
	}

	public MiembroFacultad getTipo() {
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
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE " + this.getTabla() + " SET nombre = ?, apellido = ?, dni = ?, direccion = ?, email = ?, usuario = ? WHERE legajo = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getApellido());
			stmt.setString(3, c.getDni());
			stmt.setString(4, c.getDireccion());
			stmt.setString(5, c.getEmail());
			stmt.setString(6, c.getUsuario());
			stmt.setInt(7, c.getLegajo());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setLegajo(keyResultSet.getInt(1));
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

	public String getTabla() {
		return "";
	}
	
	public boolean add(MiembroFacultad a, String password) {
		// TODO Auto-generated method stub
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO " + this.getTabla() + "(legajo,nombre,apellido,dni, direccion,email,usuario, password) VALUES(?,?,?,?,?,?,?,?)",
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


}