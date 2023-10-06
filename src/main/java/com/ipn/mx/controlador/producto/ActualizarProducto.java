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

@WebServlet(name = "ActualizarProducto", value = "/ActualizarProducto")
public class ActualizarProducto extends HttpServlet {

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
            out.println("<title>Actualizar producto</title>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center><h1 class='h1'>Actualizar</h1></center>");
            out.println("<div class='container' style='width: 30%'>");
            
            // Obtener datos alamacenados.
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(idProducto);
            
            ProductoDAO dao = new ProductoDAO();
            
            try {
                ProductoDTO dtor = null;
                dtor = dao.leerProducto(dto);
                
                out.println("<form action='ProcesarA' method='POST' enctype='multipart/form-data'>");
            
                out.println("<div class='form-group'>");
                out.println("<label for='txt_nombre'>Nombre del producto</label>");
                out.println("<input type='text' class='form-control' id='txt_nombre' placeholder='Nombre' value='" + dtor.getEntidad().getNombreProducto() + "' name='txt_nombre'>");
                out.println("</div>");

                out.println("<div class='form-group'>");
                out.println("<label for='txt_descripcion'>Descripción del producto</label>");
                out.println("<input type='text' class='form-control' id='txt_descripcion' placeholder='Descripción' value='" + dtor.getEntidad().getDescripcionProducto() + "' name='txt_descripcion'>");
                out.println("</div>");

                out.println("<div class='form-group'>");
                out.println("<label for='txt_cantidad'>Cantidad del producto</label>");
                out.println("<input type='number' class='form-control' id='txt_cantidad' min='10' max='100' value='" + dtor.getEntidad().getCantidadProducto() + "' name='txt_cantidad'>");
                out.println("</div>");
                
                // Presentar imagen asociada
                InputStream is = dtor.getEntidad().getImagenProducto();
                String encoded = getBase64(is);
                
                out.println("<div class='form-group'>");
                out.println("<label for='txt_imagen'>Imágen del producto</label>");
                out.println("<input type='file' accept='image/png, image/jpg, image/jpeg' class='form-control' id='txt_imagen' placeholder='Sube una imágen' name='txt_imagen'>");
                out.println("</div>");
                out.println("<img src='data:image/jpeg;base64," + encoded + "' width='125' height='125'/>");
                
                out.println("<input type='hidden' value='" + dtor.getEntidad().getIdProducto() + "' name='txt_idProducto'>");

                out.println("<br>");
                out.println("<center><input type='submit' class='btn btn-outline-primary' text='Actualizar'></center><br>");

                out.println("</form>");
            } catch (SQLException ex) {
                out.println("<div class='alert alert-danger' role='alert'>No se pudo encontrar el producto.</div>");
                Logger.getLogger(ActualizarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("<center><a href='ListadoProductos' class='btn btn-outline-danger'>Regresar</a></center>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    public String getBase64 (InputStream is) throws IOException {
        byte[] buffer = new byte[is.available()];
        int bytesLeidos;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((bytesLeidos = is.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesLeidos);
        }
        byte[] datos = baos.toByteArray();
        String encoded = Base64.getEncoder().encodeToString(datos);
        
        return encoded;
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
