package com.lectura.ecovidrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CrearUsuario extends AppCompatActivity {
    // Nombre del archivo SharedPreferences
    private static final String PREFS_NAME = "UserPrefs";
    ArrayList<Operario> listaArrayOperarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        // Referenciar los elementos del layout
        EditText firstNameInput = findViewById(R.id.first_name);
        EditText secondNameInput = findViewById(R.id.first_name2);
        EditText lastNameInput = findViewById(R.id.last_name);
        EditText lastNameInput2 = findViewById((R.id.last_name2));
        EditText telefonoInput = findViewById((R.id.telefono));
        EditText direccionInput = findViewById((R.id.direccion));
        EditText usernameInput = findViewById(R.id.NombreRUsuario1);
        EditText passwordInput = findViewById(R.id.TelefonoR);

        Button registerButton = findViewById(R.id.register_button);
        TextView registerStatus = findViewById(R.id.register_status);

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
                   /* // Guardar los datos del usuario en SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("firstName", firstName);
                    editor.putString("lastName", lastName);
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();*/
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
}