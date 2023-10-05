package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductoDTO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {
    private static final String SQL_INSERT = "INSERT INTO PRODUCTOS (nombreProducto, descripcionProducto, cantidadProducto, imagenProducto) "
            + "values (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE PRODUCTOS set nombreProducto=?, descripcionProducto=?, cantidadProducto=?, imagenProducto=? "
            + "WHERE idProducto=?";
    private static final String SQL_DELETE = "DELETE FROM PRODUCTOS WHERE idProducto=?";
    private static final String SQL_SELECT = "SELECT * FROM PRODUCTOS WHERE idProducto=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM PRODUCTOS";
    private Connection conexion;
    
    public ProductoDAO() {
    }
    
    private void getConexion(){
        String usuario = "root";
        String clave = "root";
        String url = "jdbc:mysql://localhost:3306/Proyecto1";
        String driverBD = "com.mysql.cj.jdbc.Driver";
        
        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void insertarProducto(ProductoDTO dto) throws SQLException {
        getConexion();
        
        PreparedStatement ps = null;
        
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcionProducto());
            ps.setInt(3, dto.getEntidad().getCantidadProducto());
            ps.setBlob(4, dto.getEntidad().getImagenProducto());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    public ProductoDTO leerProducto(ProductoDTO dto) throws SQLException {
        getConexion();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            rs = ps.executeQuery();
            
            lista = getResultados(rs);
            if(!lista.isEmpty()) {
                return (ProductoDTO) lista.get(0);
            } else {
                return null;
            }
            
        } finally {
            if(conexion != null) {
                conexion.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        }
    }
    
    public void actualizarProducto(ProductoDTO dto) throws SQLException {
        getConexion();
        
        PreparedStatement ps = null;
        
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcionProducto());
            ps.setInt(3, dto.getEntidad().getCantidadProducto());
            ps.setBlob(4, dto.getEntidad().getImagenProducto());
            ps.setInt(5, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
        } finally {
            if (conexion != null) {
                conexion.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }
    
    public void eliminarProducto(ProductoDTO dto) throws SQLException {
        getConexion();
        
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
        } finally {
            if (conexion != null) {
                conexion.close();
            }
            if(ps != null) {
                ps.close();
            }
        }
    }
    
    public List leerProductos() throws SQLException {
        getConexion();;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        List l = null;
        
        try {
            ps = conexion.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            l = getResultados(rs);
            
            if (!l.isEmpty()) {
                return l;
            } else {
                return null;
            }
        } finally {
            if(conexion != null) {
                conexion.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        }
    }
    
    public List getResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        
        while(rs.next()) {
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(rs.getInt("idProducto"));
            dto.getEntidad().setNombreProducto(rs.getString("nombreProducto"));
            dto.getEntidad().setDescripcionProducto(rs.getString("descripcionProducto"));
            dto.getEntidad().setCantidadProducto(rs.getInt("cantidadProducto"));
            
            // Convertir BLOB a FileInputStream
            Blob datosImg = rs.getBlob("imagenProducto");
            InputStream is = datosImg.getBinaryStream();
            
            dto.getEntidad().setImagenProducto(is);
            
            resultados.add(dto);
        }
        
        return resultados;
    }
    
    public static void main(String[] args) {
        ProductoDTO dto = new ProductoDTO();
        
        /* INSERT
        dto.getEntidad().setNombreProducto("Concha de chocolate");
        dto.getEntidad().setDescripcionProducto("Pan de dulce.");
        dto.getEntidad().setCantidadProducto(25);
        
        try {
            dto.getEntidad().setImagenProducto(new FileInputStream("D:\\Cristian\\Pictures\\Imagenes para proyectos\\concha.png"));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
        }
        */
        
        
        dto.getEntidad().setIdProducto(1);
        
        
        /* UPDATE
        dto.getEntidad().setIdProducto(1);
        dto.getEntidad().setNombreProducto("Lapices de colores");
        dto.getEntidad().setDescripcionProducto("Paquete con varias piezas.");
        dto.getEntidad().setCantidadProducto(10);
        
        try {
            dto.getEntidad().setImagenProducto(new FileInputStream("D:\\Cristian\\Pictures\\Imagenes para proyectos\\frijol_en_lata.jpg"));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
        }
        */
        
        ProductoDAO dao = new ProductoDAO();
        
        try {
            /*
            dao.insertarProducto(dto);
            System.out.println("Se insertó el registro con éxito.");
            */
            
            /*
            ProductoDTO dtor = null;
            dtor = dao.leerProducto(dto);
            System.out.println(dtor.toString());
            */
            
            /*
            dao.actualizarProducto(dto);
            System.out.println("Se actualizó el producto.");
            */
            
            dao.eliminarProducto(dto);
            System.out.println("Se eliminó con éxito.");
            System.out.println(dao.leerProductos());
            
            /*
            System.out.println(dao.leerProductos());
            */
        } catch (SQLException e) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
