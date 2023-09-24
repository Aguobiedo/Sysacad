<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Tu Sistema Acad�mico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principal/Principal.css">
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
        
    <section class="container">
    <!-- Contenido principal -->
    <div class="infoContainer">
        <h2>Bienvenido a Tu Sistema Acad�mico</h2>
        <p>Aqu� puedes acceder a todas las funcionalidades de tu sistema acad�mico.</p>
        <!-- Agrega aqu� el contenido principal de la p�gina -->
    </div>

    <!-- Men� -->
        <nav>
            <ul>
                
                <li><a href="views/pages/principal/Cursos.jsp">Cursos</a></li>
                <li><a href="#">Calificaciones</a></li>
                <li><a href="#">Inscripciones</a></li>
                <li><a href="#">Horarios</a></li>
                <!-- Agrega m�s opciones de men� seg�n tu sistema -->
            </ul>
        </nav>
    </section>

    <!-- Pie de p�gina -->
    <footer>
        <p>&copy; 2023 Tu Sistema Acad�mico</p>
    </footer>
</body>
</html>