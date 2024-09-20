package com.lectura.ecovidrio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Definir credenciales de ejemplo
    private final String correctUsername = "admin";
    private final String correctPassword = "12345";

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

                // Verificar credenciales
                if (username.equals(correctUsername) && password.equals(correctPassword)) {
                    loginStatus.setText("Se ha registrado con exito");
                } else {
                    loginStatus.setText("Nombre o contrasena invalida");
                }
            }
        });
    }
    public void irHaciaCrearUsuario(View view) {
        Intent intent = new Intent(MainActivity.this, CrearUsuario.class);
        startActivity(intent);
    }
}