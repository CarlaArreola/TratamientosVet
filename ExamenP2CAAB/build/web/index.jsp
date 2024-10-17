<%-- 
    Document   : index
    Created on : 14/10/2024, 08:31:54 PM
    Author     : carla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido a la Veterinaria!</h1>

        <a href="${pageContext.request.contextPath}/jsp/registrarAnimal.jsp">Agregar Animalito</a><br>
        <a href="${pageContext.request.contextPath}/jsp/registrarTrat.jsp">Agregar Tratamiento</a><br>
        
        <a href="${pageContext.request.contextPath}/jsp/MostrarAnimalitos.jsp">Mostrar Animalitos</a><br>
    </body>
</html>
