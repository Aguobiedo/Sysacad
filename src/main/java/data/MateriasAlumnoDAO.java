package data;

import entities.Alumno;
import entities.AlumnoPlan;
import entities.Carrera;
import entities.Plan;
import entities.Materia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MateriasAlumnoDAO implements IDao<AlumnoPlan> {

    public LinkedList<AlumnoPlan> materiasByAlumno(Alumno alumno) {
        LinkedList<AlumnoPlan> materias = new LinkedList<>();

        try {
            // Consulta SQL
        	String sql = "SELECT mat.*,p.idplan,c.nombre as nombCarrera, a.legajo as legajoAlu FROM alumnos_planes ap " +
                    "INNER JOIN alumno a ON ap.legajo_alumno = a.legajo " +
                    "INNER JOIN plan p ON ap.id_plan = p.idplan AND ap.id_carrera = p.idcarrera " +
                    "INNER JOIN carrera c ON p.idcarrera = c.idcarrera " +
                    "INNER JOIN materia mat ON c.idcarrera = mat.idcarrera " +
                    "WHERE a.legajo = ?";

            // Crear la conexión y la declaración preparada
            try (PreparedStatement statement = DbConnector.getInstancia().getConn().prepareStatement(sql)) {
                statement.setInt(1, alumno.getLegajo());

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Imprimir atributos por consola
                        System.out.println("legajo_alumno: " + alumno.getLegajo());
                        System.out.println("idcarrera: " + resultSet.getInt("idcarrera"));
                        System.out.println("idplan: " + resultSet.getInt("idplan"));
                        System.out.println("nombre: " + resultSet.getString("nombre"));

                        // Crear objetos a partir de los resultados
                        Alumno alumno1 = new Alumno();
                        alumno1.setLegajo(resultSet.getInt("legajoAlu"));
                        
                        Materia materia = new Materia();
                        materia.setIdMateria(resultSet.getInt("idmateria"));
                        materia.setNombre(resultSet.getString("nombre"));
                        materia.setResolucion(resultSet.getString("resolucion"));                        

                        Carrera carrera = new Carrera();
                        carrera.setIdCarrera(resultSet.getInt("idcarrera"));
                        carrera.setNombre(resultSet.getString("nombCarrera"));

                        Plan plan = new Plan();
                        plan.setIdPlan(resultSet.getInt("idplan"));
                        plan.setCarrera(carrera);

                        AlumnoPlan alumnoPlan = new AlumnoPlan();
                        alumnoPlan.setAlumno(alumno1);
                        alumnoPlan.setMateria(materia);
                        alumnoPlan.setPlan(plan);

                        materias.add(alumnoPlan);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades (lanzarla nuevamente, convertirla a una excepción específica, etc.)
        }

        return materias;
    }

    @Override
    public AlumnoPlan guardar(AlumnoPlan c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void eliminar(int id) {
        // TODO Auto-generated method stub
    }

    @Override
    public AlumnoPlan getOne(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(AlumnoPlan c) {
        // TODO Auto-generated method stub
    }

    @Override
    public LinkedList<AlumnoPlan> getAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
