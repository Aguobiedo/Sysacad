package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Carrera;
import entities.Plan;
import logic.Controller;

public class PlanDAO implements IDao<Plan>{

	@Override
	public Plan guardar(Plan c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO plan(idplan,descripcion,idcarrera) VALUES(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, c.getIdPlan());
			stmt.setString(2, c.getDescripcion());
			stmt.setInt(3, c.getCarrera().getIdCarrera());
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
							"DELETE FROM plan WHERE idplan = ?",
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
	public Plan getOne(int id) {
		Plan a= new Plan();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Controller ctrl = new Controller();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idplan,descripcion,idcarrera FROM plan WHERE idplan=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a.setIdPlan(rs.getInt("idplan"));
				a.setDescripcion(rs.getString("descripcion"));
				a.setCarrera(ctrl.carreraGetOne(rs.getInt("idcarrera")));
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
	public LinkedList<Plan> getAll() {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		LinkedList<Plan> planes = new LinkedList<>();
		Controller ctrl= new Controller();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT idplan, descripcion, idcarrera FROM plan");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Plan p = new Plan();
					p.setIdPlan(rs.getInt("idplan"));
					p.setDescripcion(rs.getString("descripcion"));
					p.setCarrera(ctrl.carreraGetOne(rs.getInt("idcarrera")));
					planes.add(p);
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
		return planes;
	}

	@Override
	public void update(Plan p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE plan SET descripcion = ?,idcarrera = ? WHERE idplan = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1,p.getDescripcion());
			stmt.setInt(2, p.getCarrera().getIdCarrera());
			stmt.setInt(3, p.getIdPlan());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdPlan(keyResultSet.getInt(1));
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
