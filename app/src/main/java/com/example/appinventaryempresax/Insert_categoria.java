package com.example.appinventaryempresax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class Insert_categoria extends AppCompatActivity {

    private TextInputEditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_categoria);
        e=(TextInputEditText)findViewById(R.id.c1);
    }

    public void Ingresar(View view){
        if(e.getText().toString().isEmpty()){
            e.setError("Rellena campo");
            e.requestFocus();
        }else{
           Cursor c=Login.sql.rawQuery("select count(nom_categoria) from tb_categoria",null);
           c.moveToFirst();

            int id=(c.getInt(0)) + 1;

            ContentValues cv=new ContentValues();
            cv.put("id_categoria",id);
            cv.put("nom_categoria",e.getText().toString());
            cv.put("estado_categoria",1);

            Login.sql.insert("tb_categoria",null,cv);

                startActivity(new Intent(getApplicationContext(),RV_Categoria.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
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
}
