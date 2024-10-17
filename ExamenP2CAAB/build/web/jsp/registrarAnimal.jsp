<%-- 
    Document   : registrarAnimal
    Created on : 14/10/2024, 09:32:46 PM
    Author     : carla
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="configuration.ConnectionBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registra un Animalito:</title>
    </head>
    <body>
    <h1>Agregar Producto</h1>

    <%
        ConnectionBD conexionBD = new ConnectionBD();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conexionBD.getConnectionBD();

            String query = "SELECT id, nombre FROM Tratamiento";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
    %>

    <form action="${pageContext.request.contextPath}/AnimalitosController" method="post">
        <input type="hidden" name="action" value="addAnimal">

        <label for="nombre">Nombre del Animalito:</label>
        <input type="text" id="nombre" name="nombre" required><br>
        
        <label for="tipoA">Tipo de Animalito:</label>
        <input type="text" id="tipoA" name="tipoA" required><br>
        
        <label for="fechaConsulta">Fecha de consulta:</label>
        <input type="date" id="fechaConsulta" name="fechaConsulta" required><br><!-- comment -->
        
        <label for="horaConsulta">Hora de Consulta:</label>
        <input type="time" id="horaConsulta" name="horaConsulta" required><br>

        <label for="nombreT">Tratamientos:</label>
        <select id="nombreT" name="nombreT" required>
            <%
                while (rs.next()) {
            %>
                <option value="<%= rs.getInt("id") %>">
                    <%= rs.getString("nombre") %>
                </option>
            <%
                }
            %>
        </select><br>

        <input type="submit" value="Agregar Animalito">
    </form>

    <%
        } catch (Exception e) {
            out.println("Error al conectar o realizar la consulta: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    %>
</body>
