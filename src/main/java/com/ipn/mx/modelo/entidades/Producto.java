package com.ipn.mx.modelo.entidades;

import java.io.InputStream;
import java.io.Serializable;

public class Producto implements Serializable{
    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private InputStream imagen;
    
    // GETTERS
    public int getIdProducto(){
        return id;
    } 
    
    public String getNombreProducto(){
        return nombre;
    }
    
    public String getDescripcionProducto(){
        return descripcion;
    }
    
    public int getCantidadProducto(){
        return cantidad;
    }
    
    public InputStream getImagenProducto(){
        return imagen;
    }
    
    // SETTERS
    public void setIdProducto(int id){
        this.id = id;
    } 
    
    public void setNombreProducto(String nombre){
        this.nombre = nombre;
    }
    
    public void setDescripcionProducto(String descripcion){
        this.descripcion = descripcion;
    }
    
    public void setCantidadProducto(int cantidad){
        this.cantidad = cantidad;
    }
    
    public void setImagenProducto(InputStream imagen){
        this.imagen = imagen;
    }
}
