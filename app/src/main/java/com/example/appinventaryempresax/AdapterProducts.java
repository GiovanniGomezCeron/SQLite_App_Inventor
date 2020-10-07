package com.example.appinventaryempresax;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProducts extends BaseAdapter {
  private ArrayList<Producto> productos;
  private int resource;
  private Context context;
    public AdapterProducts(ArrayList<Producto> products, int resource, Context context) {
        this.productos=products;
        this.resource=resource;
        this.context=context;
    }

    @Override
    public int getCount() {
        return this.productos.size();
    }

    @Override
    public Object getItem(int i) {
        return this.productos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        LayoutInflater inflater=LayoutInflater.from(context);
        v = inflater.inflate(this.resource,null);

       int ca=this.productos.get(i).getCategoria() ;

      Cursor c= Login.sql.rawQuery("select nom_categoria from tb_categoria where id_categoria="+ca,null);
      c.moveToNext();

         (((TextView)v.findViewById(R.id.categoria))).setText(c.getString(0));
        (((TextView)v.findViewById(R.id.unidad_p))).setText(this.productos.get(i).getUnidad());
        (((TextView)v.findViewById(R.id.id_p))).setText(String.valueOf(this.productos.get(i).getId()));
        ((TextView) v.findViewById(R.id.nombre_p)).setText(this.productos.get(i).getNombre());
        ((TextView) v.findViewById(R.id.stock_p)).setText(String.valueOf(this.productos.get(i).getStock()));
        return v;

    }
}
