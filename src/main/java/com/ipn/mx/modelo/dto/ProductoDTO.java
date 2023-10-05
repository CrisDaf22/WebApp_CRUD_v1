package com.ipn.mx.modelo.dto;

import java.io.Serializable;
import com.ipn.mx.modelo.entidades.Producto;

public class ProductoDTO implements Serializable{
    private Producto entidad;
    
    public ProductoDTO(){
        entidad = new Producto();
    }
    
    public Producto getEntidad(){
        return entidad;
    }
    
    public void setEntidad(Producto entidad){
        this.entidad = entidad;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Clave del producto : ").append(entidad.getIdProducto()).append("\n");
        sb.append(" Nombre del producto : ").append(entidad.getNombreProducto()).append("\n");
        sb.append(" Descripción del producto : ").append(entidad.getDescripcionProducto()).append("\n");
        sb.append(" Cantidad : ").append(entidad.getCantidadProducto()).append("\n");
        
        if (entidad.getImagenProducto() != null) {
            sb.append(" Imagen : ").append("Si hay una imagen asociada").append("\n");
        } else {
            sb.append(" Imagen : ").append("No hay una imagen asociada").append("\n");
        }
        //sb.append(" Imagen : ").append(entidad.getImagenProducto()).append("\n");
        return sb.toString();
    }
}
