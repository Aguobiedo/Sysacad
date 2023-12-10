<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio - Tu Sistema Academico</title>
    <link rel="stylesheet" type="text/css" href="views/pages/principalNoDocente/PrincipalNoDocente.css">
</head>
<body>
  <!-- Encabezado -->
    <header>
        <div class="logo">
            <h1>Tu Sistema Academico</h1>
        </div>
        <div class="profile-options">
            <a href="#">Ver Mi Perfil</a>
            <a href="#">Configuraci�n</a>
            <a href="../home/Home.html">Cerrar Sesi�n</a>
        </div>
    </header>
        
    <section class="container">
    <!-- Contenido principal -->
    <!-- Men� -->
        <nav>
            <ul>
                
                <li><a href="#">Clases</a></li>
                <li><a href="readAlumno">Alumnos</a></li>
                <li><a href="readDocente">Docentes</a></li>
                <li><a href="readNoDocente">No Docentes</a></li>
                <li><a href="readMateria">Materias</a></li>
                <li><a href="readComision">Comisiones</a></li>
                <li><a href="readCarrera">Carreras</a></li>
                <li><a href="readPlan">Planes</a></li>
                <li><a href="readInscripcion">Inscripciones</a></li>
                <li><a href="#">Examenes</a></li>

                <!-- Agrega m�s opciones de men� seg�n tu sistema -->
            </ul>
        </nav>
    </section>

    <!-- Pie de p�gina -->
    <footer>
        <p>&copy; 2023 Tu Sistema Academico</p>
    </footer>
</body>
</html>