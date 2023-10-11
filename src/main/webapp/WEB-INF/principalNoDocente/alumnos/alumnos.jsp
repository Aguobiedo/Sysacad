<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Alumno"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Inicio - Tu Sistema Académico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principalNoDocente/alumnos/alumnos.css">
<%
	LinkedList<Alumno> alumnos = (LinkedList<Alumno>)request.getAttribute("alumnos");
	String aviso = (String)request.getAttribute("aviso");
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
            <table class="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">Legajo</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido</th>
                        <th scope="col">DNI</th>
                        <th scope="col">Direccion</th>
                        <th scope="col">Email</th>
                        <th scope="col">Usuario</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody class="table-group-divider">
                    <%for (Alumno a : alumnos){ %>
                    <tr>
                        <td scope="row"><%=a.getLegajo() %></td>
                        <td><%=a.getNombre() %></td>
                        <td><%=a.getApellido() %></td>
                        <td><%=a.getDni() %></td>
                        <td><%=a.getDireccion() %></td>
                        <td><%=a.getEmail() %></td>
                        <td><%=a.getUsuario() %></td>
                        <td><button><i class="bi bi-pencil-fill"></i></button><button><i class="bi bi-trash-fill"></i></button></td>
                    </tr>
                    <%} %>
                </tbody>           
            </table>
        </div>
        <script src="altaAlumno"></script>       
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
                <%if(aviso.equals("ALUMNO CARGADO CON EXITO")) {%>
                    <dialog data-modal class="modal">
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
                <%} %>

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