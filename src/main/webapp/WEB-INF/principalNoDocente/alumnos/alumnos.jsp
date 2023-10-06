<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Alumno"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio - Tu Sistema Académico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principalNoDocente/alumnos/alumnos.css">
<%
	LinkedList<Alumno> alumnos = (LinkedList<Alumno>)request.getAttribute("alumnos");
%>    
</head>
<body>
  <!-- Encabezado -->
    <header>
        <div class="logo">
            <h1>Tu Sistema Acad�mico</h1>
        </div>
        <div class="profile-options">
            <a href="#">Ver Mi Perfil</a>
            <a href="#">Configuraci�n</a>
            <a href="../home/Home.html">Cerrar Sesi�n</a>
        </div>
    </header>
        
    <div class="container">
    <!-- Contenido principal -->
        <div class="right">
            <table>
                <thead>
                    <tr>
                        <th>Legajo</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>DNI</th>
                        <th>Direccion</th>
                        <th>Email</th>
                        <th>Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Alumno a : alumnos){ %>
                    <tr>
                        <td><%=a.getLegajo() %></td>
                        <td><%=a.getNombre() %></td>
                        <td><%=a.getApellido() %></td>
                        <td><%=a.getDni() %></td>
                        <td><%=a.getDireccion() %></td>
                        <td><%=a.getEmail() %></td>
                        <td><%=a.getUsuario() %></td>
                    </tr>
                    <%} %>
                </tbody>           
            </table>
        </div>       
        <div class="left">
            <form class="altaAlumno-form" action="altaAlumno" method="post">
                <h1>Cargar Alumno</h1>
                <div class="input-container">
                    <label for="legajo">Legajo:</label>
                    <input type="text" id="legajo" name="legajo" required>
                </div>
                <div class="input-container">
                    <label for="name">Nombre:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="input-container">
                    <label for="last-name">Apellido:</label>
                    <input type="text" id="last-name" name="last-name" required>
                </div>
                <div class="input-container">
                    <label for="dni">DNI:</label>
                    <input type="text" id="dni" name="dni" required>
                </div>
                <div class="input-container">
                    <label for="adress">Direccion:</label>
                    <input type="text" id="adress" name="adress" required>
                </div>
                <div class="input-container">
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" required>
                </div>
                <div class="input-container">
                    <label for="username">Usuario:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="input-container">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" required>
                </div>

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