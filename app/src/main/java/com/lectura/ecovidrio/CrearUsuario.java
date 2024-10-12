package com.lectura.ecovidrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CrearUsuario extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs";
    ArrayList<Operario> listaArrayOperarios;
    private Spinner spinnerTurno, spinnerTipoUsuario;
    BdOperario bdOperario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        // Referenciar los elementos del layout
        EditText firstNameInput = findViewById(R.id.horaEntradaTurno);
        EditText secondNameInput = findViewById(R.id.nombre2Operario);
        EditText lastNameInput = findViewById(R.id.apellidoOperario);
        EditText lastNameInput2 = findViewById((R.id.apellido2Operario));
        EditText telefonoInput = findViewById((R.id.telefonoOperario));
        EditText direccionInput = findViewById((R.id.direccionOperario));
        EditText usernameInput = findViewById(R.id.NombreRUsuarioOperario);
        EditText passwordInput = findViewById(R.id.HoraSalidaTurno);


        Button registerButton = findViewById(R.id.register_button);
        TextView registerStatus = findViewById(R.id.register_status);

        // Inicializa la base de datos y el Spinner
        spinnerTurno = findViewById(R.id.spinnerTurno);
        bdOperario = new BdOperario(this);

        // Cargar los nombres de los operarios en el Spinner
        cargarOperariosEnSpinner();


        // Configurar la acción del botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los campos
                String firstName = firstNameInput.getText().toString().trim();
                String secondName = secondNameInput.getText().toString().trim();
                String lastName = lastNameInput.getText().toString().trim();
                String lastName2 = lastNameInput2.getText().toString().trim();
                String telefono =  telefonoInput.getText().toString().trim();
                String direccion = direccionInput.getText().toString().trim();
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !password.isEmpty()) {

                    // Validar y mostrar los datos obtenidos
                    if (!firstName.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                        registerStatus.setText("Usuario registrado exitosamente:\n" +
                                "Nombre: " + firstName + " " + lastName + "\n");
                    } else {
                        Toast.makeText(CrearUsuario.this, "Por favor, rellena todos los campos obligatorios", Toast.LENGTH_SHORT).show();
                    }


                    //Guardar en la bd
                    BdOperario operario = new BdOperario(CrearUsuario.this);
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

                    registerStatus.setText("Usuario registrado con exito!");
                    Intent intent = new Intent(CrearUsuario.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    registerStatus.setText("Rellena todos los campos");
                }



            }
        });
    }
    private void cargarOperariosEnSpinner() {
        // Obtener lista de nombres de operarios desde la base de datos
        ArrayList<String> listaOperarios = obtenerNombresOperarios();

        // Crear un adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaOperarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinnerTurno.setAdapter(adapter);
    }

    private ArrayList<String> obtenerNombresOperarios() {
        // Obtén los operarios de la base de datos
        ArrayList<Operario> operarios = bdOperario.mostrarOperarios();

        // Crear una lista de nombres de operarios
        ArrayList<String> listaNombres = new ArrayList<>();
        for (Operario operario : operarios) {
            listaNombres.add(operario.getNombre());  // Aquí se asume que solo se mostrará el primer nombre
        }

        return listaNombres;
    }


}