package com.example.appinventaryempresax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class operation_cat extends AppCompatActivity {

    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_cat);
        id=getIntent().getExtras().getInt("id")+1;
    }
    public void u(View view){
       Intent i=new Intent(this,edit_categoria.class);
       i.putExtra("id",id);
       startActivity(i);
        finish();
    }

    public void d(View view){
        Intent i=new Intent(this,delete_cat.class);
        i.putExtra("id",id);
        startActivity(i);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
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
