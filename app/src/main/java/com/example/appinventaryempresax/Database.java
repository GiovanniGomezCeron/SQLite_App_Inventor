package com.example.appinventaryempresax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creando tabla categoria
        db.execSQL("create table tb_categoria (id_categoria int(5) primary key  , " +
                " nom_categoria text(50) ," +
                "estado_categoria int(1) )");

        //creando tabla producto
        db.execSQL("create table tb_producto(" +
                "id int(9)  primary key, " +
                "nombre text(50)," +
                "descripcion text(90), " +
                "stock int(4) ," +
                "precio real," +
                "medida text(20)," +
                "estado int(1)," +
                "fecha text(20), " +
                "categoria int(5));");

        //create table usuarios
        db.execSQL("create table tb_usuario(" +
                "id int(5) primary key," +
                "nombre text(60)," +
                "apellido text(30)," +
                "correo text(45)," +
                "usuario text(30)," +
                "clave text(150)," +
                "tipo int(1) ," +
                "estado int(1)," +
                "pregunta text(60)," +
                "respuesta text(35)," +
                "fecha_registro text(20)" +
                ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
