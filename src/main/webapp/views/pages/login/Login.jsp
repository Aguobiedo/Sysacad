<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entities.MiembroFacultad" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="Login.css">
</head>
<body>
<section class="container">
    <div class="login-container">
        <form class="login-form" action="../../../login" method="post">
            <h1>Iniciar Sesión</h1>
            <div class="input-container">
                <label for="username">Usuario:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-container">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button class="login-button" type="submit">Iniciar Sesión</button>
        </form>
        <p>Olvidaste tu clave? <a href="../recuperarClave/RecuperarClave.jsp">Recuperar contraseña</a></p>
        <a href="../homeSysJava/Home.jsp">Home</a>
    </div>
</section>
    <footer>
        <p>&copy; 2023 SysJavaProject</p>
    </footer>
</body>
</html>