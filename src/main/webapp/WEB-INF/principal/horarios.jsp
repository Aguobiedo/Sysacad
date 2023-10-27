<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Inscripcion"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Horarios</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principal/horarios/horarios.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="views/pages/principal/horarios/horarios.js"></script>
    <%
	    LinkedList<Inscripcion> inscripciones = (LinkedList<Inscripcion>)request.getAttribute("inscripciones");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		int min = 1;
		int max = 4;
		int random_int = 0;
    %>
</head>
<body>
    <div class="cd-schedule loading">
        <div class="timeline">
            <ul>
                <li><span>07:00</span></li>
                <li><span>08:00</span></li>
                <li><span>09:00</span></li>
                <li><span>10:00</span></li>
                <li><span>11:00</span></li>
                <li><span>12:00</span></li>
                <li><span>13:00</span></li>
                <li><span>14:00</span></li>
                <li><span>15:00</span></li>
                <li><span>16:00</span></li>
                <li><span>17:00</span></li>
                <li><span>18:00</span></li>
                <li><span>19:00</span></li>
                <li><span>20:00</span></li>
                <li><span>21:00</span></li>
                <li><span>22:00</span></li>
                <li><span>23:00</span></li>
                <li><span>00:00</span></li>
                <li><span>01:00</span></li>
            </ul>
        </div> <!-- .timeline -->

        <div class="events">
            <ul class="wrap">
                <li class="events-group">
                    <div class="top-info"><span>Lunes</span></div>
                    <ul>
                    	<% for(Inscripcion i : inscripciones) {%>
                    	<% random_int = (int)Math.floor(Math.random() * (max - min + 1) + min); %>
                    		<%if(i.getClase().getDiaSemanaCursado().equals("Lunes")){%>
		                        <li class="single-event" data-start=<%=formatoHora.format(i.getClase().getHorarioInicio())%> data-end=<%=formatoHora.format(i.getClase().getHorarioFin())%> data-content="event-<%i.getClase().getMateria().getNombre(); %>" data-event="event-<%=random_int%>">
		                            <a href="#0">
		                                <em class="event-name"><%=i.getClase().getMateria().getNombre()%></em>
		                            </a>
		                        </li>
		                    <%} %>
		                 <%} %>
                    </ul>
                </li>
                <li class="events-group">
                    <div class="top-info"><span>Martes</span></div>
                    <ul>
                    	<% for(Inscripcion i : inscripciones) {%>
                    	<% random_int = (int)Math.floor(Math.random() * (max - min + 1) + min); %>
                    		<%if(i.getClase().getDiaSemanaCursado().equals("Martes")){%>
		                        <li class="single-event" data-start=<%=formatoHora.format(i.getClase().getHorarioInicio())%> data-end=<%=formatoHora.format(i.getClase().getHorarioFin())%> data-content="event-<%i.getClase().getMateria().getNombre(); %>" data-event="event-<%=random_int%>">
		                            <a href="#0">
		                                <em class="event-name"><%=i.getClase().getMateria().getNombre()%></em>
		                            </a>
		                        </li>
		                    <%} %>
		                 <%} %>
                    </ul>
                </li>

                <li class="events-group">
                    <div class="top-info"><span>Miercoles</span></div>
                    <ul>
                    	<% for(Inscripcion i : inscripciones) {%>
                    	<% random_int = (int)Math.floor(Math.random() * (max - min + 1) + min); %>
                    		<%if(i.getClase().getDiaSemanaCursado().equals("Miercoles")){%>
		                        <li class="single-event" data-start=<%=formatoHora.format(i.getClase().getHorarioInicio())%> data-end=<%=formatoHora.format(i.getClase().getHorarioFin())%> data-content="event-<%i.getClase().getMateria().getNombre(); %>" data-event="event-<%=random_int%>">
		                            <a href="#0">
		                                <em class="event-name"><%=i.getClase().getMateria().getNombre()%></em>
		                            </a>
		                        </li>
		                    <%} %>
		                 <%} %>
                    </ul>
                </li>

                <li class="events-group">
                    <div class="top-info"><span>Jueves</span></div>
                    <ul>
                    	<% for(Inscripcion i : inscripciones) {%>
                    	<% random_int = (int)Math.floor(Math.random() * (max - min + 1) + min); %>
                    		<%if(i.getClase().getDiaSemanaCursado().equals("Jueves")){%>
		                        <li class="single-event" data-start=<%=formatoHora.format(i.getClase().getHorarioInicio())%> data-end=<%=formatoHora.format(i.getClase().getHorarioFin())%> data-content="event-<%i.getClase().getMateria().getNombre(); %>" data-event="event-<%=random_int%>">
		                            <a href="#0">
		                                <em class="event-name"><%=i.getClase().getMateria().getNombre()%></em>
		                            </a>
		                        </li>
		                    <%} %>
		                 <%} %>
                    </ul>
                </li>

                <li class="events-group">
                    <div class="top-info"><span>Viernes</span></div>
                    <ul>
                    	<% for(Inscripcion i : inscripciones) {%>
                    	<% random_int = (int)Math.floor(Math.random() * (max - min + 1) + min); %>
                    		<%if(i.getClase().getDiaSemanaCursado().equals("Viernes")){%>
		                        <li class="single-event" data-start=<%=formatoHora.format(i.getClase().getHorarioInicio())%> data-end=<%=formatoHora.format(i.getClase().getHorarioFin())%> data-content="event-<%i.getClase().getMateria().getNombre(); %>" data-event="event-<%=random_int%>">
		                            <a href="#0">
		                                <em class="event-name"><%=i.getClase().getMateria().getNombre()%></em>
		                            </a>
		                        </li>
		                    <%} %>
		                 <%} %>
                    </ul>
                </li>
        <!--        -->
        <li class="events-group">
                    <div class="top-info"><span>Sabado</span></div>
                    <ul>
                    	<% for(Inscripcion i : inscripciones) {%>
                    	<% random_int = (int)Math.floor(Math.random() * (max - min + 1) + min); %>
                    		<%if(i.getClase().getDiaSemanaCursado().equals("Sabado")){%>
		                        <li class="single-event" data-start=<%=formatoHora.format(i.getClase().getHorarioInicio())%> data-end=<%=formatoHora.format(i.getClase().getHorarioFin())%> data-content="event-<%i.getClase().getMateria().getNombre(); %>" data-event="event-<%=random_int%>">
		                            <a href="#0">
		                                <em class="event-name"><%=i.getClase().getMateria().getNombre()%></em>
		                            </a>
		                        </li>
		                    <%} %>
		                 <%} %>
                    </ul>
                </li>
    <!--        -->
        
        <!--        -->
        <li class="events-group">
                    <div class="top-info"><span>Domingo</span></div>
                    <ul>
                    	<% for(Inscripcion i : inscripciones) {%>
                    	<% random_int = (int)Math.floor(Math.random() * (max - min + 1) + min); %>
                    		<%if(i.getClase().getDiaSemanaCursado().equals("Domingo")){%>
		                        <li class="single-event" data-start=<%=formatoHora.format(i.getClase().getHorarioInicio())%> data-end=<%=formatoHora.format(i.getClase().getHorarioFin())%> data-content="event-<%i.getClase().getMateria().getNombre(); %>" data-event="event-<%=random_int%>">
		                            <a href="#0">
		                                <em class="event-name"><%=i.getClase().getMateria().getNombre()%></em>
		                            </a>
		                        </li>
		                    <%} %>
		                 <%} %>
                    </ul>
                </li>
    <!--        -->
            </ul>
        </div>

        <div class="event-modal">
            <header class="header">
                <div class="content">
                    <span class="event-date"></span>
                    <h3 class="event-name"></h3>
                </div>

                <div class="header-bg"></div>
            </header>

            <div class="body">
                <div class="event-info"></div>
                <div class="body-bg"></div>
            </div>

            <a href="#0" class="close">Close</a>
        </div>

        <div class="cover-layer"></div>
    </div> <!-- .cd-schedule -->
</body>
</html>