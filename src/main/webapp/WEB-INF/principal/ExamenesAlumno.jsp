<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Examen"%>
<%@ page import="entities.Clase"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Examenes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="views/pages/principal/Inscripciones.css">
</head>
<body>
    <div class="container mt-4">
        <h1 class="tituloExamenes">Lista de exámenes</h1>


        <% 
        LinkedList<Examen> examenes = (LinkedList<Examen>) request.getAttribute("examenes");
        if (examenes != null && !examenes.isEmpty()) {
        %>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Materia</th>
                        <th>Año Cursado</th>
                        <th>Número Comisión</th>
                        <th>Fecha de Inscripción</th>
                        <th>Nombre Docente</th>
                        <th>Apellido Docente</th>
                        <th>Nota</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    for (Examen examen : examenes) {    
                    %>
                        <tr>
                            <td><%= examen.getClase().getMateria().getNombre() %></td>
                            <td><%= examen.getClase().getComision().getAnioCursado() %></td>
                            <td><%= examen.getClase().getComision().getNumComision() %></td>
                            <td><%= examen.getFechaHoraInscripcion() %></td>
                            <td><%= examen.getClase().getDocente().getNombre() %></td>
                            <td><%= examen.getClase().getDocente().getApellido() %></td>
                            <td><%= examen.getNota() %></td>
                            <td><%= examen.getEstado() %></td>
                             
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No hay exámenes disponibles.</p>
        <% } %>
        
      <% 
String exitoMsg = (String) request.getAttribute("exito");
if (exitoMsg != null && !exitoMsg.isEmpty()) {
%>
    <div id="exitoModal" class="modal">
        <div><%= exitoMsg %></div>
        <button onclick="cerrarModal()">Aceptar</button>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var exitoModal = document.getElementById('exitoModal');
            if (exitoModal) {
                exitoModal.style.display = 'block';
            }
        });

        function cerrarModal() {
            var exitoModal = document.getElementById('exitoModal');
            if (exitoModal) {
                exitoModal.style.display = 'none';
            }
        }
    </script>
<% } %>
 
        
        <% 
        String errorMsg = (String) request.getAttribute("error");
        if (errorMsg != null && !errorMsg.isEmpty()) {
        %>
            <div class="alert alert-danger" role="alert">
                <%= errorMsg %>
            </div>
        <% } %>

        <h1 class="tituloExamenes">Anotarse a un examen</h1>

        <%
        LinkedList<Clase> clases = (LinkedList<Clase>) request.getAttribute("clases");
        if (clases != null && !clases.isEmpty()) {
        %>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Materia</th>
                        <th>Año Cursado</th>
                        <th>Número Comisión</th>
                        <th>Nombre Docente</th>
                        <th>Apellido Docente</th>
                        <th>IdClase </th>                        
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    for (Clase clase : clases) {
                    %>
                        <tr>
                            <td><%= clase.getMateria().getNombre() %></td>
                            <td><%= clase.getComision().getAnioCursado() %></td>
                            <td><%= clase.getComision().getNumComision() %></td>
                            <td><%= clase.getDocente().getNombre() %></td>
                            <td><%= clase.getDocente().getApellido() %></td>
                            <td><%= clase.getIdClase()%></td>
                            <td>
                                <form action="ExamenesAlumnoServlet" method="post">
                                    <input type="hidden" name="idClase" value="<%= clase.getIdClase()%>">
                                    <button type="submit">Inscribirse</button>
                                </form>
                            </td>
                        </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
        <%
        } else {
        %>
            <p>No hay exámenes disponibles.</p>
        <%
        }
        %>
    </div>

    <!-- Agrega cualquier enlace a scripts JavaScript aquí -->

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jz" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyZ3Pa4GN3AOhEIbbVd7Bnv9paAL9ik9RE" crossorigin="anonymous"></script>
</body>
</html>
