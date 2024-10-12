package com.lectura.ecovidrio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TurnoActivity extends AppCompatActivity {


    private Spinner spinnerTurno;
    private EditText nombreTurno, horaEntradaTurno, horaSalidaTurno;
    private Button btnCrear, btnModificar, btnEliminar, btnBuscar;
    private BdTurno bdTurno;
    private ArrayList<Turno> listaTurnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno);


        // Inicializar elementos del layout
        spinnerTurno = findViewById(R.id.spinnerTurno);
        nombreTurno = findViewById(R.id.NombreTurno);
        horaEntradaTurno = findViewById(R.id.horaEntradaTurno);
        horaSalidaTurno = findViewById(R.id.horaEntradaTurno2);
        btnCrear = findViewById(R.id.idCrear);
        btnModificar = findViewById(R.id.modificarTurno);
        btnEliminar = findViewById(R.id.EliminarTurno);
        btnBuscar = findViewById(R.id.BuscarTurno);

        bdTurno = new BdTurno(this);

        // Cargar los turnos en el Spinner
        cargarTurnosEnSpinner();

        // Configurar el evento de selección del Spinner
        spinnerTurno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Verificar si la lista no está vacía antes de actualizar los campos
                if (!listaTurnos.isEmpty()) {
                    Turno turnoSeleccionado = listaTurnos.get(position);
                    actualizarCamposConTurno(turnoSeleccionado);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada cuando no hay selección
            }
        });


        // Listener para el botón "Crear"
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreTurno.getText().toString();
                String horaEntrada = horaEntradaTurno.getText().toString();
                String horaSalida = horaSalidaTurno.getText().toString();

                if (!nombre.isEmpty() && !horaEntrada.isEmpty() && !horaSalida.isEmpty()) {
                    if (!nombreExiste(nombre)) {  // Verificar si el nombre del turno ya existe
                        long id = bdTurno.insertarTurno(nombre, horaEntrada, horaSalida);
                        if (id > 0) {
                            Toast.makeText(TurnoActivity.this, "Turno creado con éxito", Toast.LENGTH_SHORT).show();
                            limpiarCampos();
                            cargarTurnosEnSpinner();
                        } else {
                            Toast.makeText(TurnoActivity.this, "Error al crear turno", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TurnoActivity.this, "El turno con este nombre ya existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TurnoActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener para el botón "Modificar"
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreTurno.getText().toString();
                String horaEntrada = horaEntradaTurno.getText().toString();
                String horaSalida = horaSalidaTurno.getText().toString();

                if (!nombre.isEmpty() && !horaEntrada.isEmpty() && !horaSalida.isEmpty()) {
                    Turno turnoExistente = bdTurno.verTurnoPorNombre(nombre);
                    if (turnoExistente != null) {
                        boolean actualizado = bdTurno.editarTurno(turnoExistente.getId(), nombre, horaEntrada, horaSalida);
                        if (actualizado) {
                            Toast.makeText(TurnoActivity.this, "Turno modificado con éxito", Toast.LENGTH_SHORT).show();
                            limpiarCampos();
                            cargarTurnosEnSpinner();
                        } else {
                            Toast.makeText(TurnoActivity.this, "Error al modificar turno", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TurnoActivity.this, "Turno no encontrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TurnoActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener para el botón "Eliminar"
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreTurno.getText().toString();

                if (!nombre.isEmpty()) {
                    Turno turnoExistente = bdTurno.verTurnoPorNombre(nombre);
                    if (turnoExistente != null) {
                        boolean eliminado = bdTurno.eliminarTurno(turnoExistente.getNombre());
                        if (eliminado) {
                            Toast.makeText(TurnoActivity.this, "Turno eliminado con éxito", Toast.LENGTH_SHORT).show();
                            limpiarCampos();
                            cargarTurnosEnSpinner();
                        } else {
                            Toast.makeText(TurnoActivity.this, "Error al eliminar turno", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TurnoActivity.this, "Turno no encontrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TurnoActivity.this, "Ingrese el nombre del turno a eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener para el botón "Buscar"
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreTurno.getText().toString();

                if (!nombre.isEmpty()) {
                    Turno turno = bdTurno.verTurnoPorNombre(nombre);

                    if (turno != null) {
                        nombreTurno.setText(turno.getNombre());
                        horaEntradaTurno.setText(turno.getHoraEntrada());
                        horaSalidaTurno.setText(turno.getHoraSalida());
                    } else {
                        Toast.makeText(TurnoActivity.this, "Turno no encontrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TurnoActivity.this, "Ingrese el nombre del turno a buscar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para cargar los turnos en el Spinner
    private void cargarTurnosEnSpinner() {
        listaTurnos = bdTurno.mostrarTurnos();
        ArrayList<String> nombresTurnos = new ArrayList<>();

        if (listaTurnos.isEmpty()) {
            Toast.makeText(this, "No hay turnos registrados", Toast.LENGTH_SHORT).show();
            // Si no hay turnos, evita que el spinner quede vacío:
            nombresTurnos.add("No hay turnos disponibles");
            spinnerTurno.setEnabled(false); // Deshabilita el Spinner si no hay turnos
            btnModificar.setEnabled(false); // Deshabilita el botón Modificar
            btnEliminar.setEnabled(false); // Deshabilita el botón Eliminar
        } else {
            for (Turno turno : listaTurnos) {
                nombresTurnos.add(turno.getNombre());
            }
            spinnerTurno.setEnabled(true); // Habilita el Spinner si hay turnos
            btnModificar.setEnabled(true); // Habilita el botón Modificar
            btnEliminar.setEnabled(true); // Habilita el botón Eliminar
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresTurnos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTurno.setAdapter(adapter);
    }



    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        nombreTurno.setText("");
        horaEntradaTurno.setText("");
        horaSalidaTurno.setText("");
    }

    // Método para verificar si el nombre del turno ya existe
    private boolean nombreExiste(String nombre) {
        Turno turnoExistente = bdTurno.verTurnoPorNombre(nombre);
        return turnoExistente != null;
    }

    // Método para actualizar los campos de texto con los datos del turno seleccionado
    private void actualizarCamposConTurno(Turno turno) {
        nombreTurno.setText(turno.getNombre());
        horaEntradaTurno.setText(turno.getHoraEntrada());
        horaSalidaTurno.setText(turno.getHoraSalida());
    }

}