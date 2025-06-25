package com.example.constraintlayout;

public class Producto {
    private Integer foto;
    private String producto;
    private String presentacion;
    private Float precio;

    /*2. Clase Producto: Esta clase define un modelo para los productos que se van a mostrar en el RecyclerView. Cada objeto
        Producto representa un elemento de la lista y contiene los siguientes atributos:

        foto (Integer): Referencia al recurso drawable que contiene la imagen del producto.
        producto (String): Nombre del producto.
        presentacion (String): Descripción sobre la presentación o formato del producto (e.g., "X 100 GR").
        precio (Float): Precio del producto.
        Métodos de la clase Producto:
        Getters y Setters para obtener y modificar los atributos del producto.
        */

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