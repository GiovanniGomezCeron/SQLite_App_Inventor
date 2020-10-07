package com.example.appinventaryempresax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.*;
import android.widget.Toast;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Registrar extends AppCompatActivity {

    private RadioButton admin, use;
    private TextInputEditText[] edit_array=new TextInputEditText[7];
    private int u=2;
    private TextInputLayout pregunta;
    String pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        //OBTENIENDO EL TEXTINPUTEDITEX
        pregunta=(TextInputLayout)findViewById(R.id.pregunta);

        //OBTENIENDO LOS RADIOBUTTONS
        admin=(RadioButton)findViewById(R.id.admin);
        use=(RadioButton)findViewById(R.id.user);

        //OBTENIENDO LOS EDITEXT DEL ACTIVITY
        edit_array[0]= (TextInputEditText) findViewById(R.id.e1);
        edit_array[1]= (TextInputEditText)findViewById(R.id.e2);
        edit_array[2]= (TextInputEditText)findViewById(R.id.e3);
        edit_array[3]= (TextInputEditText) findViewById(R.id.e4);
        edit_array[4]= (TextInputEditText)findViewById(R.id.e5);
        edit_array[5]= (TextInputEditText)findViewById(R.id.e6);
        edit_array[6]= (TextInputEditText)findViewById(R.id.e7);

        //LLAMANDO A METODO DE ELEGIR PREGUNTA
        elegirPregunta();

    }
    public boolean validator(View view){
        //EDITTEXT PARA HACER CASTING Y GUARDAR REFERENCIAS DEL ARREGLO
        EditText e;
        for(int i=0; i<7; i++){
            e=edit_array[i];

            //COMPROBANDO SI LOS EDITTEXT ESTAN VACIOS
            if(e.getText().toString().trim().isEmpty()){

                //COLOCANDO MENSAHE DE ERROR
                e.setError("Rellene campo");

                //COLOCANDO EL FOCO EN LAS INPUT
                e.requestFocus();

                //RETORNAR SI ALGUN INPUT ESTA VACIO
                return false;
            }
        }
        //VERIFICANDO SI EL CHECKBOX DE ADMINISTRADOR ESTA MARCADO Y CAMBIAR VALOR DE LA VARIABLE U
       if(admin.isChecked()){
           u=1;
       }

       //VERIFICAR SI LAS CONTRASEÑAS SON IGUALES
        if(edit_array[4].getText().toString().equals(edit_array[5].getText().toString())){
           //LLAMAR A METODO REGISTRAR
           registrar();

       }else{
            //TOAST SI LAS CONTRASEÑAS NO SON IGUALES
           Toast.makeText(this,"Contraseñas no son iguales",Toast.LENGTH_LONG).show();
       }
        return true;
    }

    public void registrar(){

        //INSTANCIA DE OBJETO CONTENTVALUES PARA INGRESAR DATOS A LA BD
        ContentValues cv=new ContentValues();

        Cursor c=Login.sql.rawQuery("select count(nombre) from tb_usuario",null);
        c.moveToNext();

        //INGRESANDO DATOS
        cv.put("id",c.getInt(0)+1);
        cv.put("nombre",edit_array[0].getText().toString());
        cv.put("apellido",edit_array[1].getText().toString());
        cv.put("correo",edit_array[2].getText().toString());
        cv.put("usuario",edit_array[3].getText().toString());
        cv.put("clave",edit_array[4].getText().toString());
        cv.put("tipo",u);
        cv.put("pregunta",pre);
        cv.put("respuesta",edit_array[6].getText().toString());

        //CREANDO CONSULTA EN LA BD (INSERTAR DATOS)
        Login.sql.insert("tb_usuario",null,cv);

        //PRESENTANDO MENSAJE DE EXITO
        Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();

        Login.USER=edit_array[3].getText().toString();

            //CERAR VISTA ACTUAL
            finish();
    }

    public void elegirPregunta(){
        //CREANDO ARREGLO DE PREGUNTAS
        String[] preg={"Nombre de Mascota actual?","Profesión?","Color de ojos?"};

        //ELIGIENDO NUMERO AL AZAR QUE SERA LA PREGUNTA A ELEGIR
        int pos=(int) (Math.random()*3);

        //COLOCANDO PREGUNTA EN EL TEXTVIEW
        pregunta.setHint(preg[pos]);

        pre=preg[pos];


    }
}
