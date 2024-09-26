package com.lectura.ecovidrio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Definir credenciales de ejemplo
    private final String correctUsername = "admin";
    private final String correctPassword = "12345";
    ArrayList<Operario> listaArrayOperarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Referenciar los elementos del layout
        EditText usernameInput = findViewById(R.id.username);
        EditText passwordInput = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);
        Button crearUsuario = findViewById(R.id.crearUsuario);
        TextView loginStatus = findViewById(R.id.login_status);


        // Configurar la acción del botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                //Guardar en la bd
                BdOperario operario = new BdOperario(MainActivity.this);
                listaArrayOperarios = new ArrayList<>();

                listaArrayOperarios= operario.mostrarOperarios();



                if (!listaArrayOperarios.isEmpty()) {
                    for (Operario o : listaArrayOperarios) {

                        if (username.equals( o.getCorreoElectronico()) && password.equals(o.getNombre())) {
                            loginStatus.setText("Se ha ingresado con exito");
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
                        System.out.println("-------------------------------");
                    }
                } else {
                    System.out.println("La lista de operarios está vacía");
                }

               /*
                // Verificar credenciales
                if (username.equals(correctUsername) && password.equals(correctPassword)) {
                    loginStatus.setText("Se ha ingresado con exito");
                } else {
                    loginStatus.setText("Nombre o contrasena invalida");
                }*/
            }
        });
    }
    public void irHaciaCrearUsuario(View view) {
        Intent intent = new Intent(MainActivity.this, CrearUsuario.class);
        startActivity(intent);
    }
}