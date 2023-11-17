<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <title>Recuperar Contrase�a</title>
    <link rel="stylesheet" type="text/css" href="RecuperarClave.css">
</head>
<body>
<section class="recovery-container">
        <form class="recovery-form">
            <h1>Recuperar Contrase�a</h1>
            <div class="input-container">
                <label for="email">Correo Electr�nico:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <button class="recovery-button" type="submit">Recuperar Contrase�a</button>
        </form>
        <p>Recuerdas tu contrase�a? <a href="../login/Login.jsp">Iniciar sesi�n aqu�</a></p>
        <a href="../homeSysJava/Home.jsp">Home</a>
    </section>
    <footer>
        <p>&copy; 2023 SysJavaProject</p>
    </footer>
</body>
</html>