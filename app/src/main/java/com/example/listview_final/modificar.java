package com.example.listview_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class modificar extends Activity implements OnClickListener {

    EditText idEd,nomEd,apEd,amEd;
    Button Actualizar, Eliminar;

    long alumno_id;

    sqlControlador dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbcon = new sqlControlador(this);
        dbcon.abrirBaseDeDatos();

        idEd =(EditText)findViewById(R.id.IDEd);
        nomEd =(EditText)findViewById(R.id.NomEd);
        apEd =(EditText)findViewById(R.id.A_patED);
        amEd =(EditText)findViewById(R.id.A_matEd);
        Actualizar = (Button) findViewById(R.id.Guardar);
        Eliminar = (Button) findViewById(R.id.Cancelar);

        Intent i = getIntent();
        String alumnoID = i.getStringExtra("alumnoId");
        String alumnoNOMBRE = i.getStringExtra("alumnoNombre");
        String alumnoAP = i.getStringExtra("alumnoAp");
        String alumnoAM = i.getStringExtra("alumnoAm");

        alumno_id = Long.parseLong(alumnoID);

        nomEd.setText(alumnoNOMBRE);
        apEd.setText(alumnoAP);
        amEd.setText(alumnoAM);


        Actualizar.setOnClickListener(this);
        Eliminar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.Guardar:
                String aluNombre_upd = nomEd.getText().toString();
                String aluA_paterno_upd = apEd.getText().toString();
                String aluA_materno_upd =amEd.getText().toString();
                dbcon.actualizarDatos(alumno_id, aluNombre_upd,aluA_paterno_upd,aluA_materno_upd);
                this.returnHome();
                break;

            case R.id.Cancelar:
                dbcon.deleteData(alumno_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }
}
