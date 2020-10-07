package com.example.appinventaryempresax;

import android.content.ContentValues;
import android.content.Context;
import android.os.VibrationEffect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCategoria extends BaseAdapter {

    private Context r;
    private ArrayList<Categoria> categorias;
    private int layout;

    public AdapterCategoria(Context re, ArrayList<Categoria> c,int layout) {
        this.r=re;
        this.categorias=c;
        this.layout=layout;
    }

    @Override
    public int getCount() {
        return this.categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return this.categorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        LayoutInflater in = LayoutInflater.from(r);
        v = in.inflate(this.layout, null);

        ((TextView) v.findViewById(R.id.id_c)).setText(String.valueOf(this.categorias.get(i).getId()));
        ((TextView) v.findViewById(R.id.nom_c)).setText(String.valueOf(this.categorias.get(i).getName()));
        String es;
        if (this.categorias.get(i).getEstado() == 1) {
            es = "Activo";
            ((TextView) v.findViewById(R.id.estado_c)).setText(es);
        }
        return v;
    }

}
