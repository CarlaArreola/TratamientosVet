<%-- 
    Document   : MostrarAnimalitos
    Created on : 16/10/2024, 08:53:17 PM
    Author     : carla
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="configuration.ConnectionBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Animalitos</title>
    </head>
    <body>
        <h1>Animalitos:</h1>
        <form action="${pageContext.request.contextPath}/AnimalitosController" method="post">
            <input type="hidden" name="action" value="showAnimalTreatment">
            <input type="submit" value="Mostrar Animalitos">
        </form>

        <div id="ListaAnimalitos">
            <%
                if (request.getParameter("action") != null && request.getParameter("action").equals("showAnimalTreatment")) {
                    ConnectionBD connBD = new ConnectionBD();
                    try ( Connection connection = connBD.getConnectionBD();  Statement statement = connection.createStatement();  ResultSet rs = statement.executeQuery("SELECT a.id, a.nombre, a.tipoA, a.fechaConsulta, a.horaConsulta, t.nombre AS tratamiento "
                + "FROM Animal a "
                + "JOIN Animal_Tratamiento ps ON a.id = ps.idAnimal "
                + "JOIN Tratamiento t ON ps.idTratamiento = t.id")) {

                        out.println("<table border='1'><tr><th>ID</th><th>Producto</th><th>Precio</th><th>Cantidad</th><th>Proveedor</th></tr>");
                        while (rs.next()) {
                            out.println("<tr><td>" + rs.getInt("id") + "</td>");
                            out.println("<td>" + rs.getString("nombre") + "</td>");
                            out.println("<td>" + rs.getString("tipoA") + "</td>");  // Corregido a getString
                            out.println("<td>" + rs.getDate("fechaConsulta") + "</td>");  // Corregido a getDate
                            out.println("<td>" + rs.getTime("horaConsulta") + "</td>");  // Corregido a getTime
                            out.println("<td>" + rs.getString("idTratamiento") + "</td></tr>");
                        }
                        out.println("</table>");
                    } catch (Exception e) {
                        out.println("Error al mostrar Animalitos " + e.getMessage());
                    }
                }
            %>
        </div>

    </body>
</html>
