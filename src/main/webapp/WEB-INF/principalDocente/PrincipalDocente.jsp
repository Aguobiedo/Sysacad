<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Clase"%>
<%@page import="logic.Controller"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Inicio - Tu Sistema Academico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principalDocente/PrincipalDocente.css">
<%
	Controller ctrl = new Controller();
	LinkedList<Clase> clases = ctrl.getClaseByLegajoDocente(Integer.parseInt(request.getAttribute("legajo").toString()));
%> 
</head>
<body>
  <!-- Encabezado -->
    <header>
        <div class="logo">
            <h1>Tu Sistema Academico</h1>
        </div>
        <div class="profile-options">
            <a href="#">Ver Mi Perfil</a>
            <a href="#">Configuración</a>
            <a href="../home/Home.html">Cerrar Sesión</a>
        </div>
    </header>
        
    <section class="container">
    <!-- Contenido principal -->
        <div class="right">
            <table class="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">Materia</th>
                        <th scope="col">Num. Comision</th>
                        <th scope="col">Año de Cursado</th>
                        <th scope="col">Día de cursado</th>
                        <th scope="col">Hora Inicio</th>
                        <th scope="col">Hora Fin</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody class="table-group-divider">
                    <%for (Clase c : clases){ %>                    
                    <tr>
                        <td scope="row"><%=c.getMateria().getNombre() %></td>
                        <td><%=c.getComision().getNumComision() %></td>
                        <td><%=c.getComision().getAnioCursado() %></td>
                        <td><%=c.getDiaSemanaCursado() %></td>
                        <td><%=c.getHorarioInicio() %></td>
                        <td><%=c.getHorarioFin() %></td>
                        <td>
                            <!-- Button trigger modal -->
                            <a href="" class="btn btn-primary">
                                <i class="bi bi-pencil-fill"></i>
                            </a>
                            <!-- Button trigger modal -->
                            <a href="" class="btn btn-danger"> 
                                <i class="bi bi-trash-fill"></i>
                            </a>
                        </td> 
                    </tr>
                    <%} %>
                </tbody>           
            </table>
        </div> 
    </section>

    <!-- Pie de página -->
    <footer>
        <p>&copy; 2023 Tu Sistema Academico</p>
    </footer>
</body>
</html>