package com.example.appinventaryempresax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class RV_productos extends AppCompatActivity {
    private ListView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_productos);
        rv=(ListView)findViewById(R.id.rv_producto);

        Producto p=new Producto();
        p.listar();

        Cursor c=Login.sql.rawQuery("select * from tb_producto",null);

        //if (p.getProductos().size() > 0) {


            AdapterProducts adapterProducts=new AdapterProducts(p.getProductos(),R.layout.products_item,getApplicationContext());
            rv.setAdapter(adapterProducts);

            rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in=new Intent(getApplicationContext(),operation_prod.class);
                    in.putExtra("id",i);
                    startActivity(in);
                }
            });
    }
    public void Ingresar(View view){
        startActivity(new Intent(this,Insert_product.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
