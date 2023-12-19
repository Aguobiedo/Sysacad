<!-- MateriasAlumno.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.AlumnoPlan"%>
<%@ page import="java.util.LinkedList" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Materias del Alumno</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="views/pages/principal/Inscripciones.css">

</head>
<body>
    <div class="container mt-4">
        <h1 class="tituloMaterias">Materias del Alumno</h1>

        <%
        LinkedList<AlumnoPlan> alumnoPlanes = (LinkedList<AlumnoPlan>) request.getAttribute("alumnoPlanes");
        if (alumnoPlanes != null && !alumnoPlanes.isEmpty()) {
        %>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Materia</th>
                        <th>Plan</th>
                        <th>Resolucion</th>
                        <th>Carrera</th>
                        <!-- Agrega más columnas según sea necesario -->
                    </tr>
                </thead>
                <tbody>
                    <%
                    for (AlumnoPlan alumnoPlan : alumnoPlanes) {
                    %>
                        <tr>
                             <td><%= alumnoPlan.getMateria().getNombre() %></td>
                            <td><%= alumnoPlan.getPlan().getIdPlan() %></td>
                            <td><%= alumnoPlan.getMateria().getResolucion() %></td>
                            <td><%= alumnoPlan.getPlan().getCarrera().getNombre() %></td>
                            <!-- Agrega más columnas según sea necesario -->
                        </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
        <%
        } else {
        %>
            <p>No hay materias disponibles para este alumno.</p>
        <%
        }
        %>

        <!-- Agrega cualquier otro contenido o enlace a scripts JavaScript aquí -->

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
                integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jz"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
                integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyZ3Pa4GN3AOhEIbbVd7Bnv9paAL9ik9RE"
                crossorigin="anonymous"></script>
    </div>
</body>
</html>
