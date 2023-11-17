<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    Carrera a = (Carrera)request.getAttribute("carrera");
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
    <form class="form-Modificar" action="modificarCarrera" method="post">
        <div class="form-floating mb-3">
            <input name="legajo" type="text" class="form-control" value="<%=a.getIdCarrera()%>" id="floatingInput" placeholder="name@example.com" readonly>
            <label for="floatingInput">ID Carrera</label>
        </div>
        <div class="form-floating">
            <input name="name" type="text" class="form-control" value="<%=a.getNombre()%>" id="floatingInput" placeholder="Juan">
            <label for="floatingInput">Nombre</label>
        </div>
        <% request.setAttribute("carrera", a); %>
        <button class="alta-button" type="submit">Modificar</button>
    </form>
    </div>    

    <!-- Pie de p�gina -->
    <footer>
        <p>&copy; 2023 Tu Sistema Acad�mico</p>
    </footer>
</body>
</html>