package com.example.appinventaryempresax;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private Database db;
    public static SQLiteDatabase sql;
    private TextView user, password;
    private String u, p;
    public static String USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* MaterialToolbar toolbar=findViewById(R.id.toolbar_tree);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getLocalClassName()  );*/

        //COLOCANDO TOOLBAR
        //setSupportActionBar(layout.toolbar);
        //getSupportActionBar().setTitle(getLocalClassName());


        //OBTENIENDO TEXTVIEWS
         user = (TextView)findViewById(R.id.user);
         password= (TextView)findViewById(R.id.password);

        //OBTENIENDO DATABASE Y GUARDANDO SU REFERENCIA DE OBJETOS
        db=new Database(this,"SoftInventary",null,1);
        sql=db.getWritableDatabase();

    }

    public Login(){
        USER=null;
    }
    public void registrar(View view){
        startActivity(new Intent(getApplicationContext(),Registrar.class));
    }

    public void authUser(){

        //EJECTUANDO CONSULTA SQL
      Cursor c=sql.rawQuery(" select nombre from tb_usuario where (usuario = '"+u+"'" +
              "and clave = '"+p+"')" +
              " or " +
              "(correo = '"+u+"' and clave = '"+p+"')",null);


        //COMPROBANDO SI HAY ELEMENTOS EN LA CONSULTA
        if(c.getCount() > 0){
            // startActivity(new Intent(getApplicationContext(),Home.class));

            //CREANDO INTENT PARA TRASPORTARNOS A OTRA VISTA O ACTIVITY
            Intent i=new Intent(getApplicationContext(),ValidarPregunta.class);

            //MOVIENDO PUNTERO DE CONSULTA A LA PRIMERA POSICIÓN
            c.moveToFirst();

            //GUARDAR NOMBR DE USUARIO
           USER=c.getString(0);

            //REDIRECCIONANDO A ACTIVITY "ValidarPregunta"
            startActivity(i);
            finish();


        }else{
            //SINO ES CORRECTO PRESENTAR MENSAJE DE ERROR
            Toast.makeText(this,"Ingrese usuario y contraseña validos",Toast.LENGTH_LONG).show();

        }

    }
    public void Registrar(View view){
        //REDIRECCIONAR A LA ACTIVYTY REGISTRAR
        startActivity(new Intent(getApplicationContext(),Registrar.class));

    }
    public void validator(View view){
        //OBTENIENDO DATOS DE LOS TEXTVIEW
         u=user.getText().toString().trim();
         p=password.getText().toString().trim();

        //COMPARANDO SI LOS CAMPOS ESTAN VACIOS
        //SI ESTA VACIO PRESENTAR MENSAJE DE ERROR Y PONER FOCO
        if(u.isEmpty()){
          user. setError("Ingrese el usuario");
          user.requestFocus();

            //SI ESTA VACIO PRESENTAR MENSAJE DE ERROR Y PONER FOCO
        }else if(p.isEmpty()){
            password.setError("Ingrese la contraseña");
            password.requestFocus();
        }
        //SI ESTAN RELLENOS VALIDAR USUARIO
        else{
            authUser();
        }

    }
}
