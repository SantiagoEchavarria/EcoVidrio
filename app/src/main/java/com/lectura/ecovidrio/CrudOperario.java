package com.lectura.ecovidrio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CrudOperario extends AppCompatActivity {
    ArrayList<Operario> listaArrayOperarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_operario);

        EditText firstNameInput = findViewById(R.id.horaEntradaTurno);
        EditText secondNameInput = findViewById(R.id.nombre2Operario);
        EditText lastNameInput = findViewById(R.id.apellidoOperario);
        EditText lastNameInput2 = findViewById((R.id.apellido2Operario));
        EditText telefonoInput = findViewById((R.id.telefonoOperario));
        EditText direccionInput = findViewById((R.id.direccionOperario));
        EditText usernameInput = findViewById(R.id.NombreRUsuarioOperario);
        EditText passwordInput = findViewById(R.id.HoraSalidaTurno);

        Button crearButton = findViewById(R.id.crearOperario);
        Button modificarButton = findViewById(R.id.modificarOperario);
        Button eliminarButton = findViewById(R.id.EliminarOperario);
        Button buscarButton = findViewById(R.id.BuscarOperario);


        crearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString().trim();
                String secondName = secondNameInput.getText().toString().trim();
                String lastName = lastNameInput.getText().toString().trim();
                String lastName2 = lastNameInput2.getText().toString().trim();
                String telefono =  telefonoInput.getText().toString().trim();
                String direccion = direccionInput.getText().toString().trim();
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                   /* // Guardar los datos del usuario en SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("firstName", firstName);
                    editor.putString("lastName", lastName);
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();*/
                    //Guardar en la bd
                    BdOperario operario = new BdOperario(CrudOperario.this);
                    listaArrayOperarios = new ArrayList<>();
                    operario.insertarOperario(firstName,secondName,lastName, lastName2, telefono,direccion,
                            username, password);
                    listaArrayOperarios= operario.mostrarOperarios();

                    if (!listaArrayOperarios.isEmpty()) {
                        for (Operario o : listaArrayOperarios) {
                            System.out.println("Operario ID: " + o.getId());
                            System.out.println("Nombre: " + o.getNombre());
                            System.out.println("Segundo nombre: " + o.getNombre2());
                            System.out.println("Apellido 1: " + o.getApellido1());
                            System.out.println("Apellido 2: " + o.getApellido2());
                            System.out.println("Teléfono: " + o.getTelefono());
                            System.out.println("Dirección: " + o.getDireccion());
                            System.out.println("Correo Electrónico: " + o.getCorreoElectronico());
                            System.out.println("Contrasena: " + o.getContrasena());
                            System.out.println("-------------------------------");
                        }

                    } else {
                        System.out.println("La lista de operarios está vacía");
                    }

                    Intent intent = new Intent(CrudOperario.this, MainActivity.class);
                    startActivity(intent);
                } else {

                }
            }
        });


        modificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString().trim();
                String newPhone = telefonoInput.getText().toString().trim();
                String newEmail = usernameInput.getText().toString().trim();

                if (!firstName.isEmpty() && !newPhone.isEmpty() && !newEmail.isEmpty()) {
                    BdOperario operarioDb = new BdOperario(CrudOperario.this);
                    ArrayList<Operario> operarios = operarioDb.mostrarOperarios();

                    for (Operario operario : operarios) {
                        if (operario.getNombre().equals(firstName)) {
                            boolean result = operarioDb.editarOperario(operario.getId(), firstName, newPhone, newEmail);
                            if (result) {
                                // Modificación exitosa
                            } else {
                                // Error al modificar
                            }
                            break;
                        }
                    }
                } else {
                    // Error: uno o más campos están vacíos
                }
            }
        });



        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString().trim();

                if (!firstName.isEmpty()) {
                    BdOperario operarioDb = new BdOperario(CrudOperario.this);
                    ArrayList<Operario> operarios = operarioDb.mostrarOperarios();

                    for (Operario operario : operarios) {
                        if (operario.getNombre().equals(firstName)) {
                            boolean result = operarioDb.eliminarOperario(operario.getId());
                            if (result) {
                                // Eliminación exitosa
                            } else {
                                // Error al eliminar
                            }
                            break;
                        }
                    }
                } else {
                    // Error: el campo de nombre está vacío
                }
            }
        });



        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString().trim();

                if (!firstName.isEmpty()) {
                    BdOperario operarioDb = new BdOperario(CrudOperario.this);
                    ArrayList<Operario> operarios = operarioDb.mostrarOperarios();  // Traemos todos los operarios

                    for (Operario operario : operarios) {
                        if (operario.getNombre().equals(firstName)) {
                            // Llenamos los campos con los datos del operario encontrado
                            secondNameInput.setText(operario.getNombre2());
                            lastNameInput.setText(operario.getApellido1());
                            lastNameInput2.setText(operario.getApellido2());
                            telefonoInput.setText(operario.getTelefono());
                            direccionInput.setText(operario.getDireccion());
                            usernameInput.setText(operario.getCorreoElectronico());
                            passwordInput.setText(operario.getContrasena());
                            break;
                        }
                    }
                } else {
                    // Error: no se ha ingresado un nombre para buscar
                }
            }
        });





    }
}