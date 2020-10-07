package com.example.appinventaryempresax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.database.Cursor;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ValidarPregunta extends AppCompatActivity {

    private TextInputLayout pregunta;
    private TextInputEditText respuesta;
    private String resp;
    private Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_pregunta);

        //OBTENIENDO LAS REFERNCIAS DE LOS OBJETOS O LOS INPUT
        pregunta=(TextInputLayout)findViewById(R.id.preg);
        respuesta=(TextInputEditText)findViewById(R.id.res);

        Toast.makeText(this,Login.USER,1).show();
        //CONSULTANDO LA PREGUNTA DEL USUARIO
        c=Login.sql.rawQuery("select pregunta,respuesta from tb_usuario where (correo = '"+Login.USER+"' or usuario='"+Login.USER+"')",null);


        //MOVIENDO PUNTERO A PRIMER POSICION
        c.moveToFirst();

        //OBTENIENDO LA PREGUNTA
        pregunta.setHint(c.getString(0));
    }
    public void validator(View view){

            //OBTENIENDO LA RESPUESTA
            resp=c.getString(1);

            //VALIDANDO SI LA RESPUESTA PROPORCIONADA POR EL USUARIO ES CORRECTA
        if(resp.equals(respuesta.getText().toString())){

            //REDIRECCIONAR A LA ACTITVITY HOME
            startActivity(new Intent(getApplicationContext(),Home.class));
            finish();

        }else{
            respuesta.setError("Respuesta Incorrecta");
            respuesta.requestFocus();
        }

     }
}
