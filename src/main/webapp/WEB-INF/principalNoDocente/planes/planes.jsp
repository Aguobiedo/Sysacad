<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Plan"%>
<%@page import= "entities.Carrera" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Inicio - Tu Sistema Académico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principalNoDocente/carreras/carreras.css">
<%
	LinkedList<Plan> planes = (LinkedList<Plan>)request.getAttribute("planes");
	LinkedList<Carrera> carreras = (LinkedList<Carrera>)request.getAttribute("carreras");
	String aviso = (String)request.getAttribute("aviso");
%>    
</head>
<body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
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
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">ID Plan</th>
                        <th scope="col">Descripcion</th>
                        <th scope="col">Carrera</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody class="table-group-divider">
                    <%for (Plan a : planes){ %>                    
                    <tr>
                        <td scope="row"><%=a.getIdPlan() %></td>
                        <td><%=a.getDescripcion() %></td>
                        <td><%=a.getCarrera().getNombre() %></td>
                        <td>
                            <!-- Button trigger modal -->
                            <a href="modificarPlan?id=<%=a.getIdPlan()%>" class="btn btn-primary">
                                <i class="bi bi-pencil-fill"></i>
                            </a>
                            <!-- Button trigger modal -->
                            <a href="bajaPlan?id=<%=a.getIdPlan()%>" class="btn btn-danger"> 
                                <i class="bi bi-trash-fill"></i>
                            </a>
                        </td> 
                    </tr>
                    <%} %>
                </tbody>           
            </table>
            
        </div>     
        <div class="left">
            <form class="altaAlumno-form" action="altaPlan" method="post">
                <h1 style="color:whitesmoke;text-align: left;">Cargar Plan</h1>
                <div class="form-floating mb-3">
                    <input name="idPlan" type="text" class="form-control" id="floatingInput" placeholder="">
                    <label for="floatingInput">ID de Plan</label>
                </div>
                <div class="form-floating mb-3">
                    <input name="descripcion" type="text" class="form-control" id="floatingInput" placeholder="Juan">
                    <label for="floatingInput">Descripcion</label>
                </div>
                <select name="idCarrera" class="form-select" aria-label="Default select example">
                    <option selected>Seleccione una carrera</option>
                    <%for (Carrera c : carreras){ %>
                    	<option value="<%=c.getIdCarrera()%>"><%=c.getNombre()%></option>
                    <%} %>
                </select>
                <%if(aviso.equals("")) {%>
                <%}else{%>
                    <dialog data-modal>
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
                <%}%>

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