package com.example.appinventaryempresax;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Menu_items extends AppCompatActivity {

    public void Productos(View view){
        startActivity(new Intent(this,RV_productos.class));
    }

    public void Categorias(View view){
        startActivity(new Intent(this,RV_Categoria.class));
    }

    public void Salir(View view){
        finish();
    }

    public void Perfil(View view){

    }

    public void CerrarSesion(View view){

    }
}
