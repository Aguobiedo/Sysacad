<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Tu Sistema Académico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principal/Principal.css">
</head>
<body>
  <!-- Encabezado -->
    <header>
        <div class="logo">
            <h1>Tu Sistema Académico</h1>
        </div>
        <div class="profile-options">
            <a href="#">Ver Mi Perfil</a>
            <a href="#">Configuración</a>
            <a href="../home/Home.html">Cerrar Sesión</a>
        </div>
    </header>
        
    <section class="container">
    <!-- Contenido principal -->
    <div class="infoContainer">
        <h2>Bienvenido a Tu Sistema Académico</h2>
        <p>Aquí puedes acceder a todas las funcionalidades de tu sistema académico.</p>
        <!-- Agrega aquí el contenido principal de la página -->
    </div>

    <!-- Menú -->
        <nav>
            <ul>
                
                <li><a href="views/pages/principal/Cursos.jsp">Cursos</a></li>
                <li><a href="#">Calificaciones</a></li>
                <li><a href="#">Inscripciones</a></li>
                <li><a href="#">Horarios</a></li>
                <!-- Agrega más opciones de menú según tu sistema -->
            </ul>
        </nav>
    </section>

    <!-- Pie de página -->
    <footer>
        <p>&copy; 2023 Tu Sistema Académico</p>
    </footer>
</body>
</html>