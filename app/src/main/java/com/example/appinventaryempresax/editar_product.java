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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class editar_product extends AppCompatActivity {

    private TextInputEditText[] et=new TextInputEditText[5];
    private int id;
    private Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_product);
        ((TextView)findViewById(R.id.titulo)).setText("Actualizar producto");
        sp=(Spinner)findViewById(R.id.es6);

        id=getIntent().getExtras().getInt("id");

        //OBTENER ELEMENTOS

        et[0]=findViewById(R.id.r1);
        et[1]=findViewById(R.id.r2);
        et[2]=findViewById(R.id.r3);
        et[3]=findViewById(R.id.r4);
        et[4]=findViewById(R.id.r5);

        Cursor c=Login.sql.rawQuery("select nombre, descripcion, stock, precio, medida, categoria from tb_producto where id="+id,null);
       c.moveToNext();
        et[0].setText(c.getString(0));
        et[1].setText(c.getString(1));
        et[2].setText(c.getString(2));
        et[3].setText(c.getString(3));
        et[4].setText(c.getString(4));

        Cursor cu=Login.sql.rawQuery("select nom_categoria from tb_categoria",null);

        ArrayList<String> cat=new ArrayList<String>();

        while(cu.moveToNext()){
          cat.add(cu.getString(0));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cat);
        sp.setAdapter(adapter);

        sp.setSelection(c.getInt(5)-1);

    }
    public boolean a(View view){
        TextInputEditText t;
        for(int i=0; i<et.length; i++){
            t=et[i];
            if(t.getText().toString().isEmpty()){
                t.setError("Rellene campo");
                t.requestFocus();
                return false;
            }
        }
        ContentValues cv=new ContentValues();

        cv.put("nombre",et[0].getText().toString());
        cv.put("descripcion",et[1].getText().toString());
        cv.put("stock",Integer.parseInt(et[2].getText().toString()));
        cv.put("precio",Double.parseDouble(et[3].getText().toString()));
        cv.put("medida",et[4].getText().toString());
        cv.put("categoria",sp.getSelectedItemPosition()+1);


        Login.sql.update("tb_producto",cv,"id="+id,null);

        startActivity(new Intent(getApplicationContext(),RV_productos.class));
        finish();
        return true;
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
