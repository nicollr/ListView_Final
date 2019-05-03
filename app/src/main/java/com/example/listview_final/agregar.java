package com.example.listview_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;


public class agregar extends Activity implements OnClickListener{
    EditText idEd, nomEd, apEd, amEd;
    ImageButton agregar;
    sqlControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        idEd = (EditText) findViewById(R.id.IDEd);
        nomEd =(EditText) findViewById(R.id.NomEd);
        apEd =(EditText) findViewById(R.id.A_patED);
        amEd =(EditText) findViewById(R.id.A_matEd);
        agregar = (ImageButton) findViewById(R.id.Agregar);

        dbconeccion = new sqlControlador(this);
        dbconeccion.abrirBaseDeDatos();
        agregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Agregar:
                String id = idEd.getText().toString();
                String nom = nomEd.getText().toString();
                String am = amEd.getText().toString();
                String ap = apEd.getText().toString();
                dbconeccion.insertarDatos(id);
                dbconeccion.insertarDatos(nom);
                dbconeccion.insertarDatos(am);
                dbconeccion.insertarDatos(ap);
                Intent main = new Intent(com.example.listview_final.agregar.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            default:
                break;
        }
    }
}
