package com.ipn.mx.controlador.producto;

import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// urlPatterns = {} para varios url
// value = "" cuando solo es uno
@WebServlet(name = "ListadoProductos", value = "/ListadoProductos")
public class ListadoProductos extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de productos</title>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class='container'>");
            out.println("<h1 class='h1' style='text-align: center'>Productos</h1>");
            out.println("<a href='CrearProducto' class='btn btn-primary'>Crear producto</a>");
            out.println("<table class='table'>");
            out.println("<thead class='thead-dark'>");
            out.println("<tr>");
            out.println("<th scope='col'>ID</th>");
            out.println("<th scope='col'>Nombre</th>");
            out.println("<th scope='col'>Acciones</th>");
            out.println("</tr>");
            out.println("</thead>");
            
            ProductoDAO dao = new ProductoDAO();
            
            List<ProductoDTO> productos;
            try {
                productos = dao.leerProductos();
                
                if (!productos.isEmpty()) {
                    out.println("<tbody>");
                    for (ProductoDTO p : productos) {
                        out.println("<tr>");
                        out.println("<td>" + p.getEntidad().getIdProducto() + "</td>");
                        out.println("<td>" + p.getEntidad().getNombreProducto()+ "</td>");
                        out.println("<td>");
                        out.println("<a href='LeerProducto?idProducto=" + p.getEntidad().getIdProducto() + "' class='btn btn-outline-success'>Leer</a> &nbsp");
                        out.println("<a href='ActualizarProducto?idProducto=" + p.getEntidad().getIdProducto() + "' class='btn btn-outline-warning'>Actualizar</a> &nbsp");
                        out.println("<a href='EliminarProducto?idProducto=" + p.getEntidad().getIdProducto() + "' class='btn btn-outline-info'>Eliminar</a>");
                        out.println("</td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                } else {
                    out.println("<div class='alert alert-danger' role='alert'>No hay productos registrados.</div>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListadoProductos.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<div class='alert alert-danger' role='alert'>Error al realizar la consulta.</div>");
            }
            
            out.println("</table>");
            out.println("<div>");
            
            out.println("</body>");
            out.println("</html>");
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

}