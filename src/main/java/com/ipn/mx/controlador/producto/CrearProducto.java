package com.ipn.mx.controlador.producto;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CrearProducto", value = "/CrearProducto")
public class CrearProducto extends HttpServlet {

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
            out.println("<title>Crear producto</title>");   
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class='container' style='width: 30%'>");
            out.println("<h1 class='h1' style='text-align: center'>Crear</h1>");
            out.println("<form action='ProcesarC' method='POST' enctype='multipart/form-data'>");
            
            out.println("<div class='form-group'>");
            out.println("<label for='txt_nombre'>Nombre del producto</label>");
            out.println("<input type='text' class='form-control' id='txt_nombre' placeholder='Nombre' name='txt_nombre'>");
            out.println("</div>");
            
            out.println("<div class='form-group'>");
            out.println("<label for='txt_descripcion'>Descripción del producto</label>");
            out.println("<input type='text' class='form-control' id='txt_descripcion' placeholder='Descripción' name='txt_descripcion'>");
            out.println("</div>");
            
            out.println("<div class='form-group'>");
            out.println("<label for='txt_cantidad'>Cantidad del producto</label>");
            out.println("<input type='number' class='form-control' id='txt_cantidad' min='10' max='100' name='txt_cantidad'>");
            out.println("</div>");
            
            out.println("<div class='form-group'>");
            out.println("<label for='txt_imagen'>Imágen del producto</label>");
            out.println("<input type='file' class='form-control' id='txt_imagen' placeholder='Sube una imágen' name='txt_imagen'>");
            out.println("</div>");
            
            out.println("<br>");
            out.println("<center><input type='submit' class='btn btn-outline-primary' text='Crear'></center>");
            
            // TODO: Agregar enlace para regresar al listado de productos.
            
            out.println("</form>");
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
