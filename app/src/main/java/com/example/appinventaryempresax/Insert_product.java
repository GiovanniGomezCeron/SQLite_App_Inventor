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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Insert_product extends AppCompatActivity {

    private TextInputEditText[] te= new TextInputEditText[5];
    private Spinner sp;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Cursor cu=Login.sql.rawQuery("select nom_categoria,id_categoria from tb_categoria",null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        sp=(Spinner)findViewById(R.id.categorias);

        if(cu.getCount() > 0){

        te[0]=findViewById(R.id.p1); //NOMBRE
        te[1]=findViewById(R.id.p2);//DESC
        te[2]=findViewById(R.id.p3);//STCOK
        te[3]=findViewById(R.id.p4);//PRECIO
        te[4]=findViewById(R.id.p5);//UNIDAD

        }else {

            Intent i = new Intent();
            i.putExtra("operacion", 1);
            startActivity(new Intent(this, Insert_categoria.class));
        }

        ArrayList<String> categorias=new ArrayList<String>();

        while (cu.moveToNext()){
           categorias.add(cu.getString(0));
        }
        ArrayAdapter<String> array=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categorias);
        sp.setAdapter(array);

    }
    public boolean validator(View view){
        TextInputEditText t;
        for(int i=0; i< te.length; i++){
            t=te[i];
            if(t.getText().toString().isEmpty()){
                t.setError("Rellene campo");
                t.requestFocus();
                return false;
            }
        }

            ContentValues cv=new ContentValues();
        String nom=te[0].getText().toString();
        String des=te[1].getText().toString();
        double stock=Double.parseDouble(te[2].getText().toString());
        String unidad=te[4].getText().toString();
        double precio=Double.parseDouble(te[3].getText().toString());

        int cat=(sp.getSelectedItemPosition())+1;

        Cursor c=Login.sql.rawQuery("select count(nombre) from tb_producto",null);
        c.moveToFirst();
        int id=(c.getInt(0)) + 1;

        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();


            cv.put("id",id);
            cv.put("nombre",nom);
            cv.put("descripcion",des);
            cv.put("stock",stock);
            cv.put("precio",precio);
            cv.put("medida",unidad);
            cv.put("estado",1);
            cv.put("categoria",cat);

            Date date=new Date();
            String fecha=date.toString().substring(0,20);

            cv.put("fecha",fecha);

            Login.sql.insert("tb_producto",null,cv);


        startActivity(new Intent(this,RV_productos.class));

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
