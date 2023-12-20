<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Materia"%>
<%@page import="entities.Carrera"%>
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
    Materia m = (Materia)request.getAttribute("materia");
	Materia correlativa1 = (Materia)request.getAttribute("correlativa1");
	Materia correlativa2 = (Materia)request.getAttribute("correlativa2");
	LinkedList<Materia> materias = (LinkedList<Materia>)request.getAttribute("materias");
    LinkedList<Carrera> carreras = (LinkedList<Carrera>)request.getAttribute("carreras");
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
    <form class="form-Modificar" action="modificarMateria" method="post">
	    <div class="form-floating mb-3">
	        <input name="id" type="text" class="form-control" value="<%=m.getIdMateria()%>" id="floatingInput" placeholder="" readonly>
	        <label for="floatingInput">ID</label>
	    </div>
        <div class="form-floating mb-3">
            <input name="nombre" type="text" class="form-control" value="<%=m.getNombre()%>" id="floatingInput" placeholder="">
            <label for="floatingInput">Nombre de Materia</label>
        </div>
        <div class="form-floating mb-3">
            <input name="horas" type="number" class="form-control" value="<%=m.getHsSemanales()%>" id="floatingInput">
            <label for="floatingInput">Horas Semanales</label>
        </div>
        <div class="form-floating mb-3">
            <input name="resolucion" type="text" class="form-control" value="<%=m.getResolucion()%>" id="floatingInput" placeholder="Juan">
            <label for="floatingInput">Resolucion</label>
        </div>
        <div class="form-floating mb-3">
            <input name="anio" type="number" class="form-control" value="<%=m.getAnioCursado()%>" id="floatingInput">
            <label for="floatingInput">Año en que se cursa la materia</label>
        </div>
        <select name="idcarrera" class="form-select mb-3" aria-label="Default select example">
            <option selected value=<%=m.getCarrera().getIdCarrera()%>><%=m.getCarrera().getNombre() %></option>
            <%for (Carrera c : carreras){ %>
                <option value="<%=c.getIdCarrera()%>"><%=c.getNombre()%></option>
            <%} %>
        </select>
        <select name="idcorrelativa1" class="form-select mb-3" aria-label="Default select example">
        	<%if(correlativa1.getNombre().equals("") == false && correlativa1.getIdMateria() != 0) {%>
	            <option selected value=<%=correlativa1.getIdMateria()%>><%=correlativa1.getNombre()%></option>
	            <%for (Materia a : materias){ %>
	                <option value="<%=a.getIdMateria()%>"><%=a.getNombre()%></option>
	            <%} %>
	        <%}else{ %>
	        	<option selected value='0'>Seleccione la primer correlativa</option>
	            <%for (Materia a : materias){ %>
	                <option value="<%=a.getIdMateria()%>"><%=a.getNombre()%></option>
	            <%} %>
	        <%} %>
        </select>
        <select name="idcorrelativa2" class="form-select mb-3" aria-label="Default select example">
        	<%if(correlativa2.getNombre().equals("") == false && correlativa2.getIdMateria() != 0) {%>
	            <option selected value=<%=correlativa2.getIdMateria()%>><%=correlativa2.getNombre() %></option>
	            <%for (Materia a : materias){ %>
	                <option value="<%=a.getIdMateria()%>"><%=a.getNombre()%></option>
	            <%} %>
	        <%}else{ %>
	        	<option selected value='0'>Seleccione la segunda correlativa</option>
	            <%for (Materia a : materias){ %>
	                <option value="<%=a.getIdMateria()%>"><%=a.getNombre()%></option>
	            <%} %>
	        <%} %>
        </select>
        <% request.setAttribute("materia", m); %>
        <button class="alta-button" type="submit">Modificar</button>
    </form>
    </div>    

    <!-- Pie de p�gina -->
    <footer>
        <p>&copy; 2023 Tu Sistema Acad�mico</p>
    </footer>
</body>
</html>