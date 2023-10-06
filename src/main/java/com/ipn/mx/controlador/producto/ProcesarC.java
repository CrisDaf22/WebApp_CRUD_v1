package com.ipn.mx.controlador.producto;

import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProcesarC", value = "/ProcesarC")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 2,      // 2 MB
    maxRequestSize = 1024 * 1024 * 5   // 5 MB
)
public class ProcesarC extends HttpServlet {

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
            out.println("<title>Respuesta creación</title>"); 
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 class='h1' style='text-align: center'>Respuesta del sistema</h1>");
            out.println("<div class='container' style='text-align: center'>");
            
            String nombreProducto = request.getParameter("txt_nombre");
            String descripcionProducto = request.getParameter("txt_descripcion");
            int cantidadProducto = Integer.parseInt(request.getParameter("txt_cantidad"));
            
            Part parteArchivo = request.getPart("txt_imagen");
            InputStream is = parteArchivo.getInputStream();
            
            ProductoDTO dto = new ProductoDTO();
            ProductoDAO dao = new ProductoDAO();
            dto.getEntidad().setNombreProducto(nombreProducto);
            dto.getEntidad().setDescripcionProducto(descripcionProducto);
            dto.getEntidad().setCantidadProducto(cantidadProducto);
            // Máximo 64 kb debido al tipo BLOB 
            dto.getEntidad().setImagenProducto(is);
            
            try {
                dao.insertarProducto(dto);
                
                out.println("<div class='alert alert-success' role='alert'>El producto se creó con éxito.</div>");
            } catch (SQLException ex) {
                Logger.getLogger(ProcesarC.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<div class='alert alert-danger' role='alert'>No se pudo registrar el producto..</div>");
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
