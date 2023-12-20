<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Materia"%>
<%@page import="entities.Clase"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Inicio - Tu Sistema Académico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principalNoDocente/alumnos/alumnosUpdate.css">   
<%
    Clase c = (Clase)request.getAttribute("clase");
	LinkedList<Materia> materias = (LinkedList<Materia>)request.getAttribute("materias");
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
    <form class="form-Modificar" action="modificarClase" method="post">
	    <div class="form-floating mb-3">
	        <input name="idclase" type="number" class="form-control" value="<%=c.getIdClase()%>" id="floatingInput" placeholder="" readonly>
	        <label for="floatingInput">ID</label>
	    </div>
        <div class="form-floating mb-3">
            <input name="legajo" type="number" class="form-control" value="<%=c.getDocente().getLegajo()%>" id="floatingInput" placeholder="" readonly>
            <label for="floatingInput">Legajo del docente</label>
        </div>
        <div class="form-floating mb-3">
            <input name="materia" type="number" class="form-control" value="<%=c.getMateria().getIdMateria()%>" id="floatingInput" readonly>
            <label for="floatingInput"><%=c.getMateria().getNombre() %></label>
        </div>
        <div class="form-floating mb-3">
            <input name="nrocomision" type="number" class="form-control" value="<%=c.getComision().getNumComision()%>" id="floatingInput" placeholder="Juan" readonly>
            <label for="floatingInput">Nro de comision</label>
        </div>
        <div class="form-floating mb-3">
            <input name="aniocursado" type="number" class="form-control" value="<%=c.getComision().getAnioCursado()%>" id="floatingInput" readonly>
            <label for="floatingInput">Año en que se cursa la materia</label>
        </div>
        <select name="diasemana" class="form-select mb-3" aria-label="Default select example">
                    <option selected value="<%=c.getDiaSemanaCursado()%>"><%=c.getDiaSemanaCursado()%></option>              
                    <option value="Lunes">Lunes</option> 
                    <option value="Martes">Martes</option> 
                    <option value="Miercoles">Miercoles</option> 
                    <option value="Jueves">Jueves</option> 
                    <option value="Viernes">Viernes</option> 
                    <option value="Sabado">Sabado</option> 
                </select>
        <div class="form-floating mb-3">
            <input name="horarioinicio" type="time" class="form-control" value="<%=c.getHorarioInicio()%>" id="floatingInput">
            <label for="floatingInput">Horario de inicio</label>
        </div>
        <div class="form-floating mb-3">
            <input name="horariofin" type="time" class="form-control" value="<%=c.getHorarioFin()%>" id="floatingInput">
            <label for="floatingInput">Horario de fin</label>
        </div>
        <% request.setAttribute("clase", c); %>
        <button class="alta-button" type="submit">Modificar</button>
    </form>
    </div>    

    <!-- Pie de p�gina -->
    <footer>
        <p>&copy; 2023 Tu Sistema Acad�mico</p>
    </footer>
</body>
</html>