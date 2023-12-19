package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import entities.Comision;

public class ComisionDAO implements IDao<Comision>{

	@Override
	public Comision guardar(Comision c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO comision(num_comision,anio_cursado,turno) VALUES(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, c.getNumComision());
			stmt.setInt(2, c.getAnioCursado());
			stmt.setString(3, c.getTurno());
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

	public void eliminarComision(int nro, int anio) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"DELETE FROM comision WHERE num_comision = ? AND anio_cursado=?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, nro);
			stmt.setInt(2, anio);
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

	public Comision getOne(int nro, int anio) {
		Comision a= null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT num_comision, anio_cursado, turno FROM comision WHERE num_comision=? AND anio_cursado=?"
					);
			stmt.setInt(1, nro);
			stmt.setInt(2, anio);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a = new Comision();
				a.setNumComision(rs.getInt("num_comision"));
				a.setAnioCursado(rs.getInt("anio_cursado"));
				a.setTurno(rs.getString("turno"));
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
	public LinkedList<Comision> getAll() {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		LinkedList<Comision> comisiones = new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT num_comision, anio_cursado, turno FROM comision");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Comision c = new Comision();
					c.setNumComision(rs.getInt("num_comision"));
					c.setAnioCursado(rs.getInt("anio_cursado"));
					c.setTurno(rs.getString("turno"));
					comisiones.add(c);
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
		return comisiones;
	}

	@Override
	public void update(Comision c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE comision SET turno = ? WHERE num_comision = ? AND anio_cursado=?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, c.getTurno());
			stmt.setInt(2, c.getNumComision());
			stmt.setInt(3, c.getAnioCursado());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setNumComision(keyResultSet.getInt(1));
                c.setAnioCursado(keyResultSet.getInt(2));
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

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comision getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
