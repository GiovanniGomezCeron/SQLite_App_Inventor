package com.example.appinventaryempresax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CerrarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);
    }
    public void Si(View view){
        startActivity(new Intent(this,Home.class));
        finish();
    }

    public void No(View view){
        finish();
    }
}
