<%@page import="java.util.LinkedList"%>
<%@page import="entities.Inscripcion"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Tu Sistema Acad�mico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principal/Inscripciones.css">
<%
	LinkedList<Inscripcion> inscripciones = (LinkedList<Inscripcion>)request.getAttribute("inscripciones");
	SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
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
    <!-- Contenido principal -->
      <section class="container">
      	<% for (Inscripcion i : inscripciones) { %>
	        <div class="inscripcion">
	            <h1><%=i.getClase().getMateria().getNombre()%></h1>
	            <p>Titular: <%=i.getClase().getDocente().getApellido()%></p>
	            <p>Comision: <%=i.getClase().getComision().getNumComision()%></p>
	            <p>A�o de cursado: <%=i.getClase().getComision().getAnioCursado() %></p>
	            <p>Dia de cursado: <%=i.getClase().getDiaSemanaCursado() %></p>
	            <p>Horario de Inicio: <%=formatoHora.format(i.getClase().getHorarioInicio()) %> hs.</p>
	            <p>Horario de Fin: <%=formatoHora.format(i.getClase().getHorarioFin()) %> hs.</p>
	        </div>
	    <%} %>
      </section>
    <!-- Pie de p�gina -->
        <footer>
            <p>&copy; 2023 Tu Sistema Acad�mico</p>
        </footer>
      
</body>