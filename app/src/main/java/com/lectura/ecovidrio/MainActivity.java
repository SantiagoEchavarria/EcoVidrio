package com.lectura.ecovidrio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Operario> listaArrayOperarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciar los elementos del layout
        EditText usernameInput = findViewById(R.id.NombreRUsuarioOperario);
        EditText passwordInput = findViewById(R.id.HoraSalidaTurno);
        Button loginButton = findViewById(R.id.login_button);
        Button crearUsuario = findViewById(R.id.crearUsuario);
        TextView loginStatus = findViewById(R.id.login_status);

        // Obtener el Spinner
        Spinner spinnerRoles = findViewById(R.id.spinner_roles);

        // Definir las opciones para el Spinner
        String[] roles = new String[]{"Administrador", "Operario"};

        // Crear un ArrayAdapter usando un layout predeterminado para spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);

        // Especificar el layout que se usará cuando las opciones del spinner sean desplegadas
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar el adaptador al spinner
        spinnerRoles.setAdapter(adapter);


        // Configurar la acción del botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

               //bd
                BdOperario operario = new BdOperario(MainActivity.this);
                listaArrayOperarios = new ArrayList<>();
                listaArrayOperarios= operario.mostrarOperarios();



                if (!listaArrayOperarios.isEmpty()) {
                    for (Operario o : listaArrayOperarios) {

                        if (username.equals( o.getCorreoElectronico()) && password.equals(o.getContrasena())) {
                            loginStatus.setText("Se ha ingresado con exito");
                            Intent intent = new Intent(MainActivity.this, Menu.class);
                            startActivity(intent);
                        } else {
                            loginStatus.setText("Nombre o contrasena invalida");
                        }

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

            }
        });
    }
    public void irHaciaCrearUsuario(View view) {
        Intent intent = new Intent(MainActivity.this, CrearUsuario.class);
        startActivity(intent);

    }

    public void irHaciaRecuperarContrasena(View view) {
        Intent intent = new Intent(MainActivity.this, RecuperarContrasena.class);
        startActivity(intent);

    }
}