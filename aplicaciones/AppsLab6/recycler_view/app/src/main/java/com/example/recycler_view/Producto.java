package com.example.recycler_view;


public class Producto {
    private Integer foto;
    private String producto;
    private String presentacion;
    private Float precio;

    public Producto(Integer foto, String producto, String presentacion, Float precio) {
        this.foto = foto;
        this.producto = producto;
        this.presentacion = presentacion;
        this.precio = precio;
    }

    public Integer getFoto() {
        return foto;
    }

    public void setFoto(Integer foto) {
        this.foto = foto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}