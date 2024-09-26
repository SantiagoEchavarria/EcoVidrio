package com.lectura.ecovidrio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Bd extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String operario = "operarios";
    Context context;

    public Bd(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context= context;
    }
    public Bd(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }



    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + operario + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre1 TEXT NOT NULL," +
                "nombre2 TEXT NOT NULL," +
                "apellido1 TEXT NOT NULL," +
                "apellido2 TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "correo_electronico TEXT NOT NULL,"+
                "Contrasena TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + operario);
        onCreate(sqLiteDatabase);

    }


}
