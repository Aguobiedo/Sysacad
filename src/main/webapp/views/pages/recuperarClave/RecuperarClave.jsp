<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <title>Recuperar Contraseña</title>
    <link rel="stylesheet" type="text/css" href="RecuperarClave.css">
</head>
<body>
<section class="recovery-container">
        <form class="recovery-form">
            <h1>Recuperar Contraseña</h1>
            <div class="input-container">
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <button class="recovery-button" type="submit">Recuperar Contraseña</button>
        </form>
        <p>Recuerdas tu contraseña? <a href="../login/Login.jsp">Iniciar sesión aquí</a></p>
        <a href="../homeSysJava/Home.jsp">Home</a>
    </section>
    <footer>
        <p>&copy; 2023 SysJavaProject</p>
    </footer>
</body>
</html>