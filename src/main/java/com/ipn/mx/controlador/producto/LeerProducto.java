package com.ipn.mx.controlador.producto;

import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LeerProducto", value = "/LeerProducto")
public class LeerProducto extends HttpServlet {

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
            out.println("<title>Leer producto</title>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center><h1 class='h1'>Leer producto</h1></center>");
            out.println("<div class='container' style='text-align: center;width: 50%'>");
            
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(idProducto);
            ProductoDAO dao = new ProductoDAO();
            
            try {
                ProductoDTO dtor = null;
                dtor = dao.leerProducto(dto);
                
                // Procesar datos de imágen
                InputStream is = dtor.getEntidad().getImagenProducto();
                byte[] buffer = new byte[is.available()];
                int bytesLeidos;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((bytesLeidos = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesLeidos);
                }
                byte[] datos = baos.toByteArray();
                String encoded = Base64.getEncoder().encodeToString(datos);
                
                out.println("<table class='table table-bordered border-primary'>");
                out.println("<tr>");
                out.println("<th scope='row'>ID</th>");
                out.println("<td>" + dtor.getEntidad().getIdProducto() + "</td>");
                out.println("</tr>");
                out.println("</thead>");
                
                out.println("<tr>");
                out.println("<th scope='row'>Nombre</th>");
                out.println("<td>" + dtor.getEntidad().getNombreProducto() + "</td>");
                out.println("</tr>");
                out.println("</thead>");
                
                out.println("<tr>");
                out.println("<th scope='row'>Descripción</th>");
                out.println("<td>" + dtor.getEntidad().getDescripcionProducto() + "</td>");
                out.println("</tr>");
                out.println("</thead>");
                
                out.println("<tr>");
                out.println("<th scope='row'>Cantidad</th>");
                out.println("<td>" + dtor.getEntidad().getCantidadProducto() + "</td>");
                out.println("</tr>");
                out.println("</thead>");
                
                out.println("<tr>");
                out.println("<th scope='row'>Imágen</th>");
                out.println("<td><img src='data:image/jpeg;base64," + encoded + "' width='125' height='125'/></td>");
                out.println("</tr>");
                out.println("</thead>");
                
                out.println("</table>");
            } catch (SQLException ex) {
                Logger.getLogger(LeerProducto.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<div class='alert alert-danger' role='alert'>No se pudo encontrar el producto.</div>");
            }
            
            out.println("<center><a href='ListadoProductos' class='btn btn-outline-primary'>Regresar</a></center>");
            out.println("</div>");
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
