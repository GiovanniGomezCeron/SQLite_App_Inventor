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

public class edit_categoria extends AppCompatActivity {

    private TextInputEditText e;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_categoria);
        e=(TextInputEditText)findViewById(R.id.nombre_c);

         id=getIntent().getExtras().getInt("id");

        Cursor c = Login.sql.rawQuery("select nom_categoria from tb_categoria where id_categoria="+id,null);
        c.moveToNext();
        e.setText(c.getString(0));
    }
    public void actualizar(View view){
        ContentValues cv=new ContentValues();
        cv.put("nom_categoria",e.getText().toString());

        Login.sql.update("tb_categoria",cv,"id_categoria="+id,null);
        startActivity(new Intent(this,RV_Categoria.class));
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
