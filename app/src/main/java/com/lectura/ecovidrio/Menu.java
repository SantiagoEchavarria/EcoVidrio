package com.lectura.ecovidrio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Referencia del TextView que corresponde a la columna "Perfil"
        TextView perfilTextView = findViewById(R.id.perfil);
        TextView registroUsuario = findViewById(R.id.usaurio);

        // Seteando el click listener
        perfilTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad TurnoActivity
                Intent intent = new Intent(Menu.this, TurnoActivity.class);
                startActivity(intent);
            }
        });

        // Asignar un OnClickListener para abrir la actividad CrudOperario
        registroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para abrir CrudOperario
                Intent intent = new Intent(Menu.this, CrudOperario.class);
                startActivity(intent);
            }
        });

    }
}