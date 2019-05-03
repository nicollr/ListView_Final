package com.example.listview_final;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView Lista;
    ImageButton agregar;
    ListView lista;
    sqlControlador dbconeccion;
    TextView Id, Apellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbconeccion = new sqlControlador(this);
        dbconeccion.abrirBaseDeDatos();
        agregar = (ImageButton) findViewById(R.id.Agregar);
        Lista=(ListView)findViewById(R.id.lista);

        //acción del boton agregar miembro
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iagregar = new Intent(MainActivity.this, com.example.listview_final.agregar.class);
                startActivity(iagregar);
            }
        });

        // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
        Cursor cursor = dbconeccion.leerDatos();

        String[] from = new String[] {
                dbHelper.ALUMNO_ID,
                dbHelper.ALUMNO_APELLIDO_PATERNO,
                dbHelper.ALUMNO_APELLIDO_MATERNO
        };
        int[] to = new int[] {
                R.id.ID,
                R.id.APELLIDOS,
                R.id.APELLIDOS
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.elemento_lista, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                Id = (TextView) view.findViewById(R.id.ID);
                Apellidos = (TextView) view.findViewById(R.id.APELLIDOS);

                String aux_alumnoId = Id.getText().toString();
                String aux_alumnoApellidos = Apellidos.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), modificar.class);
                modify_intent.putExtra("miembroId", aux_alumnoId);
                modify_intent.putExtra("miembroNombre", aux_alumnoApellidos);
                startActivity(modify_intent);
            }
        });
    }
}
