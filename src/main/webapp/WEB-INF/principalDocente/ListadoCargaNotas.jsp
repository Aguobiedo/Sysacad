<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Examen"%>
<%@page import="logic.Controller"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Inicio - Tu Sistema Académico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principalDocente/ListadoCargaNotas.css">
<%
	LinkedList<Examen> examenes = (LinkedList<Examen>)request.getAttribute("examenes");
	String aviso = (String)request.getAttribute("aviso");
	Controller ctrl = new Controller ();
%>    
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  <!-- Encabezado -->
    <header>
        <div class="logo">
            <h1>Tu Sistema Academico</h1>
        </div>
    </header>
        
    <div class="container">
    <!-- Contenido principal -->
        <div class="right">
        	<form action="guardarNotas" method="post">
            	<table class="table table-dark">
                	<thead>
                    	<tr>
                        	<th scope="col">Legajo</th>
                        	<th scope="col">Nombre</th>
                        	<th scope="col">Apellido</th>
                        	<th scope="col">Email</th>
                        	<th scope="col"> Cargar Nota</th>
                    	</tr>
                	</thead>
                	<tbody class="table-group-divider">
                		<% int index = 0; %>
                    	<%for (Examen e : examenes){ %>                    
                    	<tr>
                        	<td scope="row"><%=e.getAlumno().getLegajo() %></td>
                        	<td><%=e.getAlumno().getNombre() %></td>
                        	<td><%=e.getAlumno().getApellido() %></td>
                        	<td><%=e.getAlumno().getEmail() %></td>
                        	<td>
                        		<input type="number" id="nota_<%= index %>" name="notas[<%= index %>]" min="1" max="10">
                        		<input type="hidden" name="legajos[<%= index %>]" value="<%= e	.getAlumno().getLegajo() %>">
                        	</td>
                    	</tr>
                    	<% index++; %>
                    <%} %>
                </tbody>           
            </table>
            <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
        </div>     
    </div>

    <!-- Pie de p�gina -->
    <footer>
        <p>&copy; 2023 Tu Sistema Academico</p>
    </footer>
</body>
</html>