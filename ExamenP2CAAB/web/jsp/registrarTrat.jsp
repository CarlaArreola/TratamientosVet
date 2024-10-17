<%-- 
    Document   : registrarTrat
    Created on : 14/10/2024, 09:32:12 PM
    Author     : carla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registra un tratamiento:</title>
    </head>
    <body>
        <h1>Agregar tratamiento:</h1>
        <form action="${pageContext.request.contextPath}/AnimalitosController" method="post">
            <input type="hidden" name="action" value="addTreatment">
            <label for="nombre">Nombre del tratamiento:</label>
            <input type="text" id="nombre" name="nombre" required><br>
            <label for="costo">Costo del Tratamiento:</label>
            <input type="number" id="costo" name="costo" step="0.01" required><br><!-- comment -->
            <input type="submit" value="Agregar Tratamiento:">
        </form>
    </body>
</html>
