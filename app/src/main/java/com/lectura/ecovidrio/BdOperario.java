package com.lectura.ecovidrio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class BdOperario extends Bd{

    Context context;

    public BdOperario(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertarOperario(String nombre1, String nombre2, String apellido1,
            String apellido2, String telefono, String direccion, String correo_electronico, String contrasena) {

        long id = 0;

        try {
            Bd dbHelper = new Bd(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre1", nombre1);
            values.put("nombre2", nombre2);
            values.put("apellido1", apellido1);
            values.put("apellido2", apellido2);
            values.put("telefono", telefono);
            values.put("direccion", direccion);
            values.put("correo_electronico", correo_electronico);
            values.put("contrasena", contrasena);

            id = db.insert(operario, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Operario> mostrarOperarios() {

        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + operario + " ORDER BY nombre1 ASC", null);

        ArrayList<Operario> listaOperario = new ArrayList<>();
        Operario operario;

        if (cursorContactos.moveToFirst()) {
            do {
                operario = new Operario();
                operario.setId(cursorContactos.getInt(0));
                operario.setNombre(cursorContactos.getString(1));
                operario.setNombre2(cursorContactos.getString(2));
                operario.setApellido1(cursorContactos.getString(3));
                operario.setApellido2(cursorContactos.getString(4));
                operario.setTelefono(cursorContactos.getString(5));
                operario.setDireccion(cursorContactos.getString(6));
                operario.setCorreoElectronico(cursorContactos.getString(7));
                operario.setContrasena(cursorContactos.getString(8));

                listaOperario.add(operario);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaOperario;
    }

    public Operario verContacto(int id) {

        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + operario + " WHERE id = " + id + " LIMIT 1", null);
        Operario operario = null;

        if (cursorContactos.moveToFirst()) {
            operario = new Operario();
            operario.setId(cursorContactos.getInt(0));
            operario.setNombre(cursorContactos.getString(1));
            operario.setTelefono(cursorContactos.getString(2));
            operario.setCorreoElectronico(cursorContactos.getString(3));
        }

        cursorContactos.close();

        return operario;
    }

    public boolean editarOperario(int id, String nombre, String telefono, String correo_electronico) {

        boolean correcto = false;

        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + operario + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarOperario(int id) {

        boolean correcto = false;

        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + operario + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
