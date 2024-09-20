package com.lectura.ecovidrio;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CrearUsuario extends AppCompatActivity {
    // Nombre del archivo SharedPreferences
    private static final String PREFS_NAME = "UserPrefs";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        // Referenciar los elementos del layout
        EditText firstNameInput = findViewById(R.id.first_name);
        EditText lastNameInput = findViewById(R.id.last_name);
        EditText usernameInput = findViewById(R.id.username);
        EditText passwordInput = findViewById(R.id.password);
        Button registerButton = findViewById(R.id.register_button);
        TextView registerStatus = findViewById(R.id.register_status);

        // Configurar la acción del botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los campos
                String firstName = firstNameInput.getText().toString();
                String lastName = lastNameInput.getText().toString();
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                    // Guardar los datos del usuario en SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("firstName", firstName);
                    editor.putString("lastName", lastName);
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    registerStatus.setText("User registered successfully!");
                } else {
                    registerStatus.setText("Please fill in all fields");
                }
            }
        });
    }
}