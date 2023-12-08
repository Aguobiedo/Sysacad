<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Examen" %>
<%@ page import="entities.Clase" %>
<%@ page import="entities.Docente" %>
<%@ page import="entities.Alumno" %>
<%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Examenes</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principal/ListaExamenesAlumnos.css">
    <!-- Agregamos estilos para el modal -->
    <style>
        /* Estilos para el modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1 class="tituloInscripciones">Lista de inscripciones</h1>

    <table class="table table-striped">
        <!-- Encabezado de la tabla -->
        <thead class="thead-dark">
            <tr>
                <th>Legajo Alumno</th>
                <th>ID Clase</th>
                <th>Fecha y Hora Inicio</th>
                <th>Fecha y Hora Inscripción</th>
                <!-- Agrega más columnas según sea necesario -->
            </tr>
        </thead>
        <tbody>
            <% 
                LinkedList<Examen> examenes = (LinkedList<Examen>) request.getAttribute("examenes");
                if (examenes != null && !examenes.isEmpty()) {
                    for (Examen examen : examenes) {
                        Clase clase = examen.getClase();
                        Alumno alumno = examen.getAlumno();
            %>
            <tr>
                <td><%= alumno.getLegajo() %></td>
                <td><%= clase.getIdClase() %></td>
                <td><%= examen.getFechaHoraInicio() %></td>
                <td><%= examen.getFechaHoraInscripcion() %></td>
                <!-- Agrega más columnas según sea necesario -->
            </tr>
            <% 
                    }
                } else {
            %>
            <!-- Si no hay exámenes disponibles, mostrar un mensaje -->
            <tr>
                <td colspan="4">No hay exámenes disponibles.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
    
    <a class="btn btn-primary btn-volver" href="javascript:history.back()">Volver</a>
    

    <!-- Botón para abrir el modal de inscripción -->
    <button class="btn btn-primary btn-volver" onclick="openModal()">Inscribirse a un examen final</button>

    <!-- Modal de inscripción -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <!-- Agrega aquí el contenido del formulario de inscripción -->
            <h2>Formulario de Inscripción</h2>
            <!-- Puedes incluir campos de formulario, etiquetas, etc. -->
            <form action="ProcesarInscripcionServlet" method="post">
                <!-- Campos del formulario -->
                <!-- ... -->
                <input type="submit" value="Inscribirse">
            </form>
        </div>
    </div>

    <!-- Script para manejar la apertura y cierre del modal -->
    <script>
        function openModal() {
            document.getElementById('myModal').style.display = 'block';
        }

        function closeModal() {
            document.getElementById('myModal').style.display = 'none';
        }

        // Cerrar el modal haciendo clic fuera de él
        window.onclick = function(event) {
            var modal = document.getElementById('myModal');
            if (event.target == modal) {
                modal.style.display = 'none';
            }
        }
    </script>
</body>
</html>
