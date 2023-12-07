<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Alumno, logic.Controller"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="views/pages/principal/Principal.css">
    <title>Perfil del Alumno</title>
</head>
<body>
    <header>
        <div class="logo">
            <h1>Tu Sistema Académico</h1>
        </div>
        <div class="navbar">
            <a href="#">Configuración</a>
            <a href="../home/Home.html">Cerrar Sesión</a>
        </div>
    </header>
            <div class="navbar-secundario">
            <a href="inscripciones" class="btn btn-primary">Inscripciones</a>
            <a href="materias-cursadas" class="btn btn-primary">Materias Cursadas</a>
            <a href="horarios" class="btn btn-primary">Horarios</a>
        </div>

    <div class="container">
        <div class="profile">
             
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Información del Alumno</h5>
                    <%
                    Alumno alumno = (Alumno) request.getSession().getAttribute("alumno");
                    // Supongamos que Alumno tiene campos como legajo, nombre, apellido, dni, etc.
                    %>
                    <p>Legajo: <%= alumno.getLegajo() %></p>
                    <p>Nombre: <%= alumno.getNombre() %></p>
                    <p>Apellido: <%= alumno.getApellido() %></p>
                    <p>DNI: <%= alumno.getDni() %></p>
                    <!-- Agrega más campos según la estructura de tu entidad Alumno -->
                </div>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2023 Tu Sistema Académico</p>
    </footer>
</body>
</html>