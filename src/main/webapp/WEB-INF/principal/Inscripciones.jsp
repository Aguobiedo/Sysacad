<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Inscripcion"%>
<%@ page import="java.util.LinkedList"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Inscripciones</title>
     
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="views/pages/principal/Inscripciones.css">
</head>
<body>
    <div class="container mt-4">
        <h1 class="tituloInscripciones">Lista de inscripciones</h1>

        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID Clase</th>
                    <th>Nombre Materia</th>
                    <th>Apellido Docente</th>
                    <th>Número Comisión</th>
                    <th>Año Cursado</th>
                    <th>Día Semana Cursado</th>
                    <th>Horario Inicio</th>
                    <th>Horario Fin</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    LinkedList<Inscripcion> inscripciones = (LinkedList<Inscripcion>) request.getAttribute("inscripciones");
                    if (inscripciones != null && !inscripciones.isEmpty()) {
                        for (Inscripcion inscripcion : inscripciones) {
                            entities.Clase clase = inscripcion.getClase();
                            entities.Comision comision = clase.getComision();
                %>
                <tr>
                    <td><%= clase.getIdClase() %></td>
                    <td><%= clase.getMateria().getNombre() %></td>
                    <td><%= clase.getDocente().getApellido() %></td>
                    <td><%= comision.getNumComision() %></td>
                    <td><%= comision.getAnioCursado() %></td>
                    <td><%= clase.getDiaSemanaCursado() %></td>
                    <td> </td>
                    <td> </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6">No hay inscripciones disponibles.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
        
        <a class="btn btn-primary btn-volver" href="javascript:history.back()">Volver</a>
        

         
    </div>

    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jz" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyZ3Pa4GN3AOhEIbbVd7Bnv9paAL9ik9RE" crossorigin="anonymous"></script>
</body>
</html>
