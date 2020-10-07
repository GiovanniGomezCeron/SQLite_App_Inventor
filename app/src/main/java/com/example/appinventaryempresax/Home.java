package com.example.appinventaryempresax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
            getSupportActionBar().setTitle("Home");
        if(Login.USER ==null){
            startActivity(new Intent(this,Login.class));
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i=new Intent(this,RV_productos.class);
        switch(item.getItemId()){
            case R.id.Categorias:
                i=new Intent(this,RV_Categoria.class);
                break;
            case R.id.sesion:
                Login.USER=null;
                i=new Intent(this,Login.class);
                break;
            case R.id.exit:
               finish();
                break;
        }
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }



    public void products(View view){
        startActivity(new Intent(getApplicationContext(),RV_productos.class));
    }

    public void categoria(View view){
        startActivity(new Intent(this,RV_Categoria.class));
    }

    public void Productos(){
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
