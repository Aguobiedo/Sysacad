<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import= "entities.Clase" %>
<%@page import= "entities.Materia" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Inicio - Tu Sistema Académico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principalNoDocente/materias/materias.css">
<%
	LinkedList<Clase> clases = (LinkedList<Clase>)request.getAttribute("clases");
    LinkedList<Materia> materias = (LinkedList<Materia>)request.getAttribute("materias");
	String aviso = (String)request.getAttribute("aviso");
%>    
</head>
<body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  <!-- Encabezado -->
    <header>
        <div class="logo">
            <h1>Tu Sistema Académico</h1>
        </div>
        <div class="profile-options">
            <a href="#">Ver Mi Perfil</a>
            <a href="#">Configuración</a>
            <a href="../home/Home.html">Cerrar Sesión</a>
        </div>
    </header>
        
    <div class="container">
    <!-- Contenido principal -->
        <div class="right">
            <table class="table table-light table-sm">
                <thead>
                    <tr>
                        <th scope="col">ID Clase</th>
                        <th scope="col">Legajo Doc</th>
                        <th scope="col">ID Materia</th>
                        <th scope="col">Nro comision</th>
                        <th scope="col">Año de cursado</th>
                        <th scope="col">Dia semana</th>
                        <th scope="col">Horario Inicio</th>
                        <th scope="col">Horario Fin</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody class="table-group-divider">
                    <%for (Clase a : clases){ %>                    
                    <tr>
                        <td scope="row"><%=a.getIdClase() %></td>
                        <td><%=a.getDocente().getLegajo() %></td>
                        <td><%=a.getMateria().getIdMateria() %></td>
                        <td><%=a.getComision().getNumComision() %></td>
                        <td><%=a.getComision().getAnioCursado() %></td>
                        <td><%=a.getDiaSemanaCursado() %></td>
                        <td><%=a.getHorarioInicio() %></td>
                        <td><%=a.getHorarioFin() %></td>
                        <td>
                            <!-- Button trigger modal -->
                            <a href="modificarClase?id=<%=a.getIdClase()%>" class="btn btn-primary">
                                <i class="bi bi-pencil-fill"></i>
                            </a>
                            <!-- Button trigger modal -->
                            <a href="bajaClase?id=<%=a.getIdClase()%>" class="btn btn-danger"> 
                                <i class="bi bi-trash-fill"></i>
                            </a>
                        </td> 
                    </tr>
                    <%} %>
                </tbody>           
            </table>
            
        </div>     
        <div class="left">
            <form class="altaAlumno-form" action="altaClase" method="post">
                <h1 style="color:whitesmoke;text-align: left;">Cargar Clase</h1>
                <div class="form-floating mb-3">
                    <input name="idclase" type="number" class="form-control" id="floatingInput" placeholder="">
                    <label for="floatingInput">ID Clase</label>
                </div>
                <div class="form-floating mb-3">
                    <input name="legajo" type="number" class="form-control" id="floatingInput" placeholder="Juan">
                    <label for="floatingInput">Legajo Docente</label>
                </div>
                <select name="materia" class="form-select mb-3" aria-label="Default select example">
                    <option selected value='0'>Seleccione la materia</option>
                    <%for (Materia m : materias){ %>
                    	<option value="<%=m.getIdMateria()%>"><%=m.getNombre()%></option>
                    <%} %>
                </select>
                <div class="form-floating mb-3">
                    <input name="nrocomision" type="number" class="form-control" id="floatingInput" placeholder="Juan">
                    <label for="floatingInput">Nro de Comision</label>
                </div>
                <div class="form-floating mb-3">
                    <input name="aniocursado" type="number" class="form-control" id="floatingInput" placeholder="Juan">
                    <label for="floatingInput">Año de cursado</label>
                </div>
                <select name="diasemana" class="form-select mb-3" aria-label="Default select example">
                    <option selected value="No especificado">Seleccione un dia de la semana</option>              
                    <option value="Lunes">Lunes</option> 
                    <option value="Martes">Martes</option> 
                    <option value="Miercoles">Miercoles</option> 
                    <option value="Jueves">Jueves</option> 
                    <option value="Viernes">Viernes</option> 
                    <option value="Sabado">Sabado</option> 
                </select>
                <div class="form-floating mb-3">
                    <input name="horarioinicio" type="time" class="form-control" id="floatingInput" placeholder="Juan">
                    <label for="floatingInput">Horario de Inicio</label>
                </div>
                <div class="form-floating mb-3">
                    <input name="horariofin" type="time" class="form-control" id="floatingInput" placeholder="Juan">
                    <label for="floatingInput">Horario de Fin</label>
                </div>
                <%if(aviso.equals("")) {%>
                <%}else{%>
                    <dialog data-modal>
                        <div><%=aviso%></div>
                        <button data-close-modal>Aceptar</button>
                    </dialog>
                    
                    <script>
                        const closeButton = document.querySelector("[data-close-modal]")
                        const modal = document.querySelector("[data-modal]")
                        
                        modal.showModal()
                        closeButton.addEventListener("click", () => {
                            modal.close()
                        })
                    </script>
                <%}%>

                <button class="alta-button" type="submit">Cargar</button>
            </form>
        </div>
    </div>

    <!-- Pie de p�gina -->
    <footer>
        <p>&copy; 2023 Tu Sistema Acad�mico</p>
    </footer>
</body>
</html>