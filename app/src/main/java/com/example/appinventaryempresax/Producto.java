package com.example.appinventaryempresax;

import android.database.Cursor;
import java.util.ArrayList;

public class Producto {
    private ArrayList<Producto> productos;
    private int id;
    private double precio;
    private String nombre;
    private int stock;
    private String descripcion;
    private String unidad;
    private int estado;
    private int  categoria;
    private String fecha;

    public Producto() {
        this.productos=new ArrayList<Producto>();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void listar(){
        this.productos=new ArrayList<Producto>();

    Cursor c=Login.sql.rawQuery("select id, nombre, descripcion, categoria , estado, medida, stock, precio,fecha from tb_producto ",null);
    while(c.moveToNext()){
        Producto p=new Producto();
        p.setId(c.getInt(0));
        p.setNombre(c.getString(1));
        p.setDescripcion(c.getString(2));
        p.setCategoria(c.getInt(7));
        p.setEstado(c.getInt(6));
        p.setUnidad(c.getString(5));
        p.setStock(c.getInt(3));
        p.setPrecio(c.getDouble(4));
        p.setFecha(c.getString(8));
        productos.add(p);
     }
    }


    public ArrayList<Producto> producto(){
        return this.productos;
    }

}
