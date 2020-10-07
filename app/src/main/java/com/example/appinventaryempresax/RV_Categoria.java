package com.example.appinventaryempresax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.LauncherActivity.ListItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class RV_Categoria extends AppCompatActivity {

    private ListView rv;
    private CardView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv__categoria);
        rv=(ListView)findViewById(R.id.rv_cat);

        Categoria c=new Categoria();
        c.listar();

        AdapterCategoria a=new AdapterCategoria(getApplicationContext(),c.getCategorias(),R.layout.activity_listar_categoria);
        rv.setAdapter(a);

        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(getApplicationContext(),operation_cat.class);
                in.putExtra("id",i);

                startActivity(in);

                Toast.makeText(getApplicationContext(),String.valueOf(1),1).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void Agregar(View view){
        startActivity(new Intent(this,Insert_categoria.class));
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
        }
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

}
