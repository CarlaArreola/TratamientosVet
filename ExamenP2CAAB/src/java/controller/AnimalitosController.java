/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carla
 */
@WebServlet(name = "AnimalitosController", urlPatterns = {"/AnimalitosController"})
public class AnimalitosController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        try ( PrintWriter out = response.getWriter()) {
            ConnectionBD connBD = new ConnectionBD();
            Connection connection = connBD.getConnectionBD();

            switch (action) {
                case "addAnimal":
                    addAnimal(request, response, connection, out);
                    break;
                case "addTreatment":
                    addTreatment(request, response, connection, out);
                    break;
                default:
                    out.println("Acción no válida");
                    break;
                case "showAnimalTreatment":
                    showAnimalTreatment(request, response, connection, out);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addAnimal(HttpServletRequest request, HttpServletResponse response, Connection connection, PrintWriter out) throws IOException {
        String nombre = request.getParameter("nombre");
        String tipoA = request.getParameter("tipoA");
        String fechaConsulta = request.getParameter("fechaConsulta");
        String horaConsulta = request.getParameter("horaConsulta");
        String nombreT = request.getParameter("nombreT");

        try {
            String query = "INSERT INTO Animal (nombre, tipoA, fechaConsulta,horaConsulta) VALUES (?, ?,?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.setString(2, tipoA);
            ps.setString(3, fechaConsulta);
            ps.setString(4, horaConsulta);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int idAnimal = 0;
            if (rs.next()) {
                idAnimal = rs.getInt(1);
            }

            String supplierQuery = "INSERT INTO Animal_Tratamiento (idAnimal, idTratamiento) VALUES (?, ?)";
            PreparedStatement psTreatment = connection.prepareStatement(supplierQuery);
            psTreatment.setInt(1, idAnimal);
            psTreatment.setString(2, nombreT);
            psTreatment.executeUpdate();

            out.println("Animalito agregado exitosamente");
        } catch (Exception e) {
            out.println("Error al agregar Animalito: " + e.getMessage());
        }
    }

    private void addTreatment(HttpServletRequest request, HttpServletResponse response, Connection connection, PrintWriter out) throws IOException {
        String nombre = request.getParameter("nombre");
        String costo = request.getParameter("costo");

        try {
            String query = "INSERT INTO Tratamiento (nombre, costo) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, costo);
            ps.executeUpdate();

            out.println("Tratamiento agregado exitosamente");
        } catch (Exception e) {
            out.println("Error al agregar Tratamiento: " + e.getMessage());
        }
    }

   private void showAnimalTreatment(HttpServletRequest request, HttpServletResponse response, Connection connection, PrintWriter out) throws IOException {
    try {
        String query = "SELECT a.id, a.nombre, a.tipoA, a.fechaConsulta, a.horaConsulta, t.nombre AS tratamiento "
                + "FROM Animal a "
                + "JOIN Animal_Tratamiento ps ON a.id = ps.idAnimal "
                + "JOIN Tratamiento t ON ps.idTratamiento = t.id";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        out.println("<table border='1'><tr><th>ID</th><th>Nombre</th><th>Tipo</th><th>Fecha Consulta</th><th>Hora Consulta</th><th>Tratamiento</th></tr>");
        while (rs.next()) {
            out.println("<tr><td>" + rs.getInt("id") + "</td>");
            out.println("<td>" + rs.getString("nombre") + "</td>");
            out.println("<td>" + rs.getString("tipoA") + "</td>");
            out.println("<td>" + rs.getDate("fechaConsulta") + "</td>");
            out.println("<td>" + rs.getTime("horaConsulta") + "</td>");
            out.println("<td>" + rs.getString("tratamiento") + "</td></tr>");
        }
        out.println("</table>");
    } catch (Exception e) {
        out.println("Error al mostrar Animalitos: " + e.getMessage());
    }
}

}
