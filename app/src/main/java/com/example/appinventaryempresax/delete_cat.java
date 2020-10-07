package com.example.appinventaryempresax;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static androidx.appcompat.app.AlertDialog.*;

public class delete_cat extends AppCompatActivity {

    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_cat);
        id=getIntent().getExtras().getInt("id");
    }


    public void no(View view){
        startActivity(new Intent(this,RV_Categoria.class));
    }


    public void si(View view){

        Cursor c=Login.sql.rawQuery("select count(categoria) from tb_producto where categoria="+id,null);

        c.moveToNext();


        if(c.getInt(0) > 0 ){
            Toast.makeText(this,"Error, tiene productos con esta categoria",Toast.LENGTH_LONG).show();
        }else{
            Login.sql.delete("tb_categoria","id_categoria="+id,null);
            startActivity(new Intent(this,RV_Categoria.class));
            finish();

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
