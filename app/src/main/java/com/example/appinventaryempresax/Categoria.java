package com.example.appinventaryempresax;

import android.database.Cursor;

import java.util.ArrayList;

public class Categoria {
    private String name;
    private int id;
    private int estado;
    private ArrayList<Categoria> categorias;

    public Categoria() {
        this.categorias=new ArrayList<Categoria>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void listar(){
        this.categorias=new ArrayList<Categoria>();
       Cursor c= Login.sql.rawQuery("select id_categoria,nom_categoria,estado_categoria from tb_categoria",null);

        while(c.moveToNext()){
            Categoria ca=new Categoria();
            ca.setId(c.getInt(0));
            ca.setName(c.getString(1));
            ca.setEstado(c.getInt(2));
            categorias.add(ca);
        }
    }
    public ArrayList<Categoria> cat(){
        return this.categorias;
    }

}
