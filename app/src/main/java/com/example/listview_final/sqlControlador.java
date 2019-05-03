package com.example.listview_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class sqlControlador {
    private dbHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public sqlControlador(Context c) {
        ourcontext = c;
    }

    public sqlControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new dbHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }


    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                dbHelper.ALUMNO_ID,
                dbHelper.ALUMNO_APELLIDO_PATERNO,
                dbHelper.ALUMNO_APELLIDO_MATERNO
        };
        Cursor c = database.query(dbHelper.TABLE_ALUMNOS, todasLasColumnas, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatos(long alumnoID, String alumnoNombre, String alumnoAp_paterno, String alumnoAp_materno) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(dbHelper.ALUMNO_NOMBRE, alumnoNombre);
        cvActualizar.put(dbHelper.ALUMNO_APELLIDO_PATERNO,alumnoAp_paterno);
        cvActualizar.put(dbHelper.ALUMNO_APELLIDO_MATERNO,alumnoAp_materno);
        int i = database.update(dbHelper.TABLE_ALUMNOS, cvActualizar,
                dbHelper.ALUMNO_ID + " = " + alumnoID, null);
        return i;
    }

    public void deleteData(long alumonID) {
        database.delete(dbHelper.TABLE_ALUMNOS, dbHelper.ALUMNO_ID + "=" + alumonID, null);
    }

    public void insertarDatos(String id) {
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.ALUMNO_ID,id);
        database.insert(dbHelper.TABLE_ALUMNOS, null, cv);
    }
}
