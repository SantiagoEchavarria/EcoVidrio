package com.lectura.ecovidrio;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class BdTurno extends Bd {

    Context context;

    public BdTurno(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // Método para insertar un turno con nombre
    public long insertarTurno(String nombre, String horaEntrada, String horaSalida) {
        long id = 0;

        try {
            Bd dbHelper = new Bd(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);  // Insertar el nombre
            values.put("horaentrada", horaEntrada);
            values.put("horasalida", horaSalida);

            id = db.insert(turno, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    // Método para mostrar todos los turnos con nombre
    public ArrayList<Turno> mostrarTurnos() {

        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursorTurnos;
        cursorTurnos = db.rawQuery("SELECT * FROM " + turno + " ORDER BY nombre ASC", null);  // Ordenar por nombre

        ArrayList<Turno> listaTurno = new ArrayList<>();
        Turno turnoObj;

        if (cursorTurnos.moveToFirst()) {
            do {
                turnoObj = new Turno();
                turnoObj.setId(cursorTurnos.getInt(0));
                turnoObj.setNombre(cursorTurnos.getString(1));  // Obtener el nombre
                turnoObj.setHoraEntrada(cursorTurnos.getString(2));
                turnoObj.setHoraSalida(cursorTurnos.getString(3));

                listaTurno.add(turnoObj);
            } while (cursorTurnos.moveToNext());
        }

        cursorTurnos.close();

        return listaTurno;
    }

    // Método para ver un turno específico por id
    public Turno verTurno(int id) {

        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursorTurno;

        cursorTurno = db.rawQuery("SELECT * FROM " + turno + " WHERE id = " + id + " LIMIT 1", null);
        Turno turnoObj = null;

        if (cursorTurno.moveToFirst()) {
            turnoObj = new Turno();
            turnoObj.setId(cursorTurno.getInt(0));
            turnoObj.setNombre(cursorTurno.getString(1));  // Obtener el nombre
            turnoObj.setHoraEntrada(cursorTurno.getString(2));
            turnoObj.setHoraSalida(cursorTurno.getString(3));
        }

        cursorTurno.close();

        return turnoObj;
    }


// Método para editar un turno por su nombre
    public boolean editarTurno(int id, String nombre, String horaEntrada, String horaSalida) {
        boolean correcto = false;

        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + turno + " SET horaentrada = ?, horasalida = ? WHERE nombre = ?",
                    new String[]{horaEntrada, horaSalida, nombre});
            correcto = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }


    // Método para eliminar un turno por su nombre
    public boolean eliminarTurno(String nombre) {
        boolean correcto = false;

        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + turno + " WHERE nombre = ?", new String[]{nombre});
            correcto = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }



    // Método para obtener un turno por su nombre
    public Turno verTurnoPorNombre(String nombre) {
        Bd dbHelper = new Bd(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursorTurno = db.rawQuery("SELECT * FROM " + turno + " WHERE nombre = ? LIMIT 1", new String[]{nombre});
        Turno turnoObj = null;

        if (cursorTurno.moveToFirst()) {
            turnoObj = new Turno();
            turnoObj.setId(cursorTurno.getInt(0));
            turnoObj.setNombre(cursorTurno.getString(1));  // Obtener el nombre
            turnoObj.setHoraEntrada(cursorTurno.getString(2));
            turnoObj.setHoraSalida(cursorTurno.getString(3));
        }

        cursorTurno.close();
        return turnoObj;
    }

}
