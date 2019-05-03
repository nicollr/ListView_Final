package com.example.listview_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    // Información de la tabla
    public static final String TABLE_ALUMNOS = "alumnos";
    public static final String ALUMNO_ID = "id";
    public static final String ALUMNO_NOMBRE = "nom";
    public static final String ALUMNO_APELLIDO_PATERNO = "ap";
    public static final String ALUMNO_APELLIDO_MATERNO = "am";

    // información del a base de datos
    static final String DB_NAME = "DBALUMOS";
    static final int DB_VERSION = 1;

    // Información de la base de datos
    private static final String CREATE_TABLE = "create table " + TABLE_ALUMNOS + "(" + ALUMNO_ID + " INTEGER, " + ALUMNO_NOMBRE + " TEXT NOT NULL, " + ALUMNO_APELLIDO_MATERNO + " TEXT NOT NULL, " + ALUMNO_APELLIDO_PATERNO + " TEXT NOT NULL);";

    public dbHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUMNOS);
        onCreate(db);
    }
}
