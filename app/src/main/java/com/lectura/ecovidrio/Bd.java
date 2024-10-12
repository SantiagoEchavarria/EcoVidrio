package com.lectura.ecovidrio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Bd extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String operario = "operarios";
    public static final String turno = "turno";
    public static final String turnoOperario = "Turno_Operario";  // Nueva tabla intermedia
    Context context;

    public Bd(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public Bd(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Crear la tabla operarios
        sqLiteDatabase.execSQL("CREATE TABLE " + operario + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre1 TEXT NOT NULL," +
                "nombre2 TEXT NOT NULL," +
                "apellido1 TEXT NOT NULL," +
                "apellido2 TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "correo_electronico TEXT NOT NULL," +
                "Contrasena TEXT NOT NULL)");

        // Crear la tabla turno
        sqLiteDatabase.execSQL("CREATE TABLE " + turno + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL,"+
                "horaentrada TEXT NOT NULL," +
                "horasalida TEXT NOT NULL)");

        // Crear la tabla intermedia Turno_Operario
        sqLiteDatabase.execSQL("CREATE TABLE " + turnoOperario + "(" +
                "id_operario INTEGER NOT NULL," +
                "id_turno INTEGER NOT NULL," +
                "PRIMARY KEY(id_operario, id_turno)," +
                "FOREIGN KEY(id_operario) REFERENCES " + operario + "(id)," +
                "FOREIGN KEY(id_turno) REFERENCES " + turno + "(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Eliminar las tablas si ya existen
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + turnoOperario);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + operario);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + turno);
        onCreate(sqLiteDatabase);
    }
}


