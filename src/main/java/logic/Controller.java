package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

import data.*;
import entities.*;

public class Controller {
	
	
	public MiembroFacultad validate(String username, String password) throws SQLException {
		MiembroFacultadDAO mfDao = new MiembroFacultadDAO();
		return (MiembroFacultad)mfDao.validate(username, calcularSHA256(password));
	}
	
	//METODOS DE ALUMNO
	
	public LinkedList<Inscripcion> getInscripcionesByAlumno(Alumno a){
		InscripcionDAO insDao = new InscripcionDAO();
		return insDao.getByAlumno(a);
	}
	
	public LinkedList<MiembroFacultad> alumnosGetAll(){
		AlumnoDAO aDao = new AlumnoDAO();
		return aDao.getAll();
	}
	
	
	
	public String addAlumno(Alumno a, String password) {
		AlumnoDAO aDao = new AlumnoDAO();
		if(this.isLegajoAvailable(a.getLegajo())) {
			if(this.isUsernameAvailable(aDao, a.getUsuario())) {
				if(aDao.add(a, calcularSHA256(password))) {
					return "ALUMNO INGRESADO CON EXITO";
				}else {
					return "ERROR AL INGRESAR EL ALUMNO";
				}
			}else {
				return "EL NOMBRE DE USUARIO INGRESADO YA SE ENCUENTRA EN USO";
			}
		}else {
			return "EL LEGAJO INGRESADO YA EXISTE";
		}
		
	}
	
	
	public boolean isLegajoAvailable(int legajo) {
		if(Objects.isNull(this.alumnoGetOne(legajo)) &&
				Objects.isNull(this.docenteGetOne(legajo)) &&
				Objects.isNull(this.noDocenteGetOne(legajo))) {
			return true;
		}
		return false;
	}
	
	public boolean isUsernameAvailable(MiembroFacultadDAO mfDao, String username) {
		if(mfDao.isUsernameAvailable(username)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public void deleteAlumno(int legajo) {
		AlumnoDAO aDao = new AlumnoDAO();
		aDao.eliminar(legajo);
	}
	
	
	
	public Alumno alumnoGetOne(int legajo) {
		AlumnoDAO aDao = new AlumnoDAO();
		return (Alumno) aDao.getOne(legajo);
	}
	
	
	public void updateAlumno(Alumno alumno) {
		AlumnoDAO aDao = new AlumnoDAO();
		aDao.update(alumno);
	}
	
     
    public LinkedList<Examen> getExamenAlumno(Alumno a){
		ExamenDAO examenDao = new ExamenDAO();
		return examenDao.getExamenesByAlumno(a);
	}
    
    public LinkedList<Clase> matDispRendir(Alumno a){
		ExamenDAO examenDao = new ExamenDAO();
		return examenDao.materiasDisponiblesRendir(a);
	}
    
    public boolean inscribirExamen(int legajoAlumno, int idClase) {
        ExamenDAO examenDAO = new ExamenDAO();
        return examenDAO.inscribirExamen(legajoAlumno, idClase);
    }
    
    public LinkedList<AlumnoPlan> materiasAlumno(Alumno a){
		MateriasAlumnoDAO materiasAlumnoDAO = new MateriasAlumnoDAO();
		return materiasAlumnoDAO.materiasByAlumno(a);
	}
	//FIN METODOS DE ALUMNO
  
	
	//METODOS DOCENTE
	public LinkedList<MiembroFacultad> docentesGetAll(){
		DocenteDAO dDao = new DocenteDAO();
		return dDao.getAll();
	}
	
	public String addDocente(Docente a, String password) {
		DocenteDAO dDao = new DocenteDAO();
		if(this.isLegajoAvailable(a.getLegajo())) {
			if(this.isUsernameAvailable(dDao, a.getUsuario())) {
				if(dDao.add(a, calcularSHA256(password))) {
					return "DOCENTE INGRESADO CON EXITO";
				}else {
					return "ERROR AL INGRESAR EL DOCENTE";
				}
			}else {
				return "EL NOMBRE DE USUARIO INGRESADO YA SE ENCUENTRA EN USO";
			}
		}else {
			return "EL LEGAJO INGRESADO YA EXISTE";
		}
	}
	
	public void deleteDocente(int legajo) {
		DocenteDAO dDao = new DocenteDAO();
		dDao.eliminar(legajo);
	}
	
	public Docente docenteGetOne(int legajo) {
		DocenteDAO dDao = new DocenteDAO();
		return (Docente) dDao.getOne(legajo);
	}
	
	
	public void updateDocente(Docente docente) {
		DocenteDAO dDao = new DocenteDAO();
		dDao.update(docente);
	}
	
	//FIN METODOS DOCENTE
	
	//METODOS NO DOCENTE
	public LinkedList<MiembroFacultad> noDocentesGetAll(){
		NoDocenteDAO nDao = new NoDocenteDAO();
		return nDao.getAll();
	}
	
	public String addNoDocente(NoDocente a, String password) {
		NoDocenteDAO ndDao = new NoDocenteDAO();
		if(this.isLegajoAvailable(a.getLegajo())) {
			if(this.isUsernameAvailable(ndDao, a.getUsuario())) {
				if(ndDao.add(a, calcularSHA256(password))) {
					return "NO DOCENTE INGRESADO CON EXITO";
				}else {
					return "ERROR AL INGRESAR EL NO DOCENTE";
				}
			}else {
				return "EL NOMBRE DE USUARIO INGRESADO YA SE ENCUENTRA EN USO";
			}
		}else {
			return "EL LEGAJO INGRESADO YA EXISTE";
		}
	}
	
	public void deleteNoDocente(int legajo) {
		NoDocenteDAO dDao = new NoDocenteDAO();
		dDao.eliminar(legajo);
	}
	
	public NoDocente noDocenteGetOne(int legajo) {
		NoDocenteDAO nDao = new NoDocenteDAO();
		return (NoDocente) nDao.getOne(legajo);
	}
	
	
	public void updateNoDocente(NoDocente noDocente) {
		NoDocenteDAO nDao = new NoDocenteDAO();
		nDao.update(noDocente);
	}
	
	//FIN METODOS NO DOCENTE
	
	//METODOS CARRERAS
	
	public LinkedList<Carrera> carrerasGetAll(){
		CarreraDAO cDao = new CarreraDAO();
		return cDao.getAll();
	}
	
	public String addCarrera(Carrera c) {
		// TODO Auto-generated method stub
		CarreraDAO cDao = new CarreraDAO();
		if(Objects.isNull(this.carreraGetOne(c.getIdCarrera()))) {
			if(cDao.guardar(c) != null) {
				return "CARRERA CARGADA CON EXITO";
			}else {
				return "ERROR AL CARGAR LA CARRERA";
			}
		}
		return "EL ID DE LA CARRERA CARGADA YA EXISTE";
	}
	
	public void deleteCarrera(int id) {
		CarreraDAO cDao = new CarreraDAO();
		cDao.eliminar(id);
	}
	
	public Carrera carreraGetOne(int id) {
		CarreraDAO cDao = new CarreraDAO();
		return (Carrera) cDao.getOne(id);
	}
	
	public void updateCarrera(Carrera carrera) {
		CarreraDAO cDao = new CarreraDAO();
		cDao.update(carrera);
	}
	
	//FIN METODOS CARRERAS
	
	//METODOS PLANES
	
	public LinkedList<Plan> planesGetAll(){
		PlanDAO pDao = new PlanDAO();
		return pDao.getAll();
	}
	
	public String addPlan(Plan p) {
		PlanDAO pDao = new PlanDAO();
		if(Objects.isNull(this.planGetOne(p.getIdPlan()))) {
			if(pDao.guardar(p) != null) {
				return "PLAN CARGADO CON EXITO";
			}else {
				return "ERROR AL CARGAR EL PLAN";
			}
		}
		return "EL ID DEL PLAN INGRESADO YA EXISTE";
	}
	
	public void deletePlan(int id) {
		PlanDAO pDao = new PlanDAO();
		pDao.eliminar(id);
	}
	
	public Plan planGetOne(int id) {
		PlanDAO pDao = new PlanDAO();
		return (Plan) pDao.getOne(id);
	}
	
	public void updatePlan(Plan plan) {
		PlanDAO pDao = new PlanDAO();
		pDao.update(plan);
	}
	
	//FIN METODOS PLANES
	
	//METODOS COMISIONES
	
	public LinkedList<Comision> comisionesGetAll(){
		ComisionDAO cDao = new ComisionDAO();
		return cDao.getAll();
	}
	
	public String addComision(Comision c) {
		ComisionDAO cDao = new ComisionDAO();
		if(Objects.isNull(this.comisionGetOne(c.getNumComision(), c.getAnioCursado()))) {
			if(cDao.guardar(c) != null) {
				return "COMISION CARGADA CON EXITO";
			}else {
				return "ERROR AL CARGAR LA COMISION";
			}
		}
		return "LA COMISION INGRESADA YA EXISTE";
	}
	
	public void deleteComision(int nro, int anio) {
		ComisionDAO cDao = new ComisionDAO();
		cDao.eliminarComision(nro,anio);
	}
	
	public Comision comisionGetOne(int nro, int anio) {
		ComisionDAO cDao = new ComisionDAO();
		return (Comision) cDao.getOne(nro,anio);
	}
	
	public void updateComision(Comision comision) {
		ComisionDAO cDao = new ComisionDAO();
		cDao.update(comision);
	}
	
	//FIN METODOS COMISION
	
	//METODOS MATERIA
	
	public LinkedList<Materia> materiasGetAll(){
		MateriaDAO mDao = new MateriaDAO();
		return mDao.getAll();
	}
	
	public boolean addMateria(Materia m) {
		MateriaDAO mDao = new MateriaDAO();
		if(mDao.guardar(m) != null) {
			return true;
		}else {
			return false;
		}
	}
	public void deleteMateria(int id) {
		MateriaDAO mDao = new MateriaDAO();
		mDao.eliminar(id);
	}
	
	public Materia materiaGetOne(int id) {
		MateriaDAO mDao = new MateriaDAO();
		return (Materia) mDao.getOne(id);
	}
	
	public void updateMateria(Materia materia) {
		MateriaDAO mDao = new MateriaDAO();
		mDao.update(materia);
	}
	// FIN METODOS MATERIA
	
	//METODOS CLASE
	public LinkedList<Clase> clasesGetAll() {
		ClaseDAO cDao = new ClaseDAO();
		return cDao.getAll();
	}
	
	public LinkedList<Clase> getClaseByLegajoDocente(int legajo) {
		ClaseDAO cDao = new ClaseDAO();
		return cDao.getByLegajoDocente(legajo);
	}
	
	public boolean addClase(Clase c) {
		ClaseDAO cDao = new ClaseDAO();
		if(cDao.guardar(c) != null) {
			return true;
		}else {
			return false;
		}
	}
	public void deleteClase(int id) {
		ClaseDAO cDao = new ClaseDAO();
		cDao.eliminarClase(id);
	}
	
	public Clase claseGetOne(int id) {
		ClaseDAO cDao = new ClaseDAO();
		return (Clase) cDao.getOne(id);
	}
	
	public void updateClase(Clase clase) {
		ClaseDAO cDao = new ClaseDAO();
		cDao.update(clase);
	}
	//FIN METODOS CLASE
	
	
	// METODOS INSCRIPCIONES
	
	public LinkedList<Inscripcion> inscripcionesGetAll() {
		InscripcionDAO iDao = new InscripcionDAO();
		return iDao.getAll();
	}
	
	public String addInscripcion(int legajo, int idclase) {
		InscripcionDAO iDao = new InscripcionDAO();
		Inscripcion i = new Inscripcion();
		i.setAlumno(this.alumnoGetOne(legajo));
		if(Objects.isNull(i.getAlumno())) {
			return "EL ALUMNO INGRESADO NO EXISTE";
		}
		i.setClase(this.claseGetOne(idclase));
		if(i.getClase().getIdClase() == 0) {
			return "LA CLASE INGRESADA NO EXISTE";
		}
		System.out.println("ID DE LA CLASE ASIGNADA:" + i.getClase().getIdClase());
		i.setFechahora(Timestamp.valueOf(LocalDateTime.now()));
		if(Objects.nonNull(iDao.guardar(i))) {
			return "INSCRIPCION CARGADA CON EXITO";
		}else {
			return "ERROR AL CARGAR LA INSCRIPCION";
		}
	}
	
	public void deleteInscripcion(int legajo, int idclase) {
		InscripcionDAO iDao = new InscripcionDAO();
		iDao.eliminar(legajo,idclase);
	}
	
	public static String calcularSHA256(String texto) {
        try {
            // Crear una instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Obtener los bytes del texto
            byte[] bytes = texto.getBytes();

            // Calcular el hash
            byte[] hashBytes = md.digest(bytes);

            // Convertir el hash a una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejo de excepciones en caso de que no se encuentre el algoritmo
            e.printStackTrace();
            return null; // O puedes lanzar una excepción personalizada aquí
        }
    }


	

}
