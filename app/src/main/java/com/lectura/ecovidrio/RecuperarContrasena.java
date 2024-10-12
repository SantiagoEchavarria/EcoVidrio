package com.lectura.ecovidrio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RecuperarContrasena extends AppCompatActivity {

    ArrayList<Operario> listaArrayOperarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

    }


    public void recuperarContrasena(View view) {
        EditText usernameRInput = findViewById(R.id.NombreRUsuarioOperario);
        EditText TelefonoRInput = findViewById(R.id.HoraSalidaTurno);
        EditText SegundoApellido = findViewById(R.id.SegundoApellido);
        TextView textContrasena = findViewById(R.id.textContrasena);

        BdOperario operario = new BdOperario(RecuperarContrasena.this);


        String username = usernameRInput.getText().toString();
        String telefono = TelefonoRInput.getText().toString();
        String segundoApellido = SegundoApellido.getText().toString();

        listaArrayOperarios = new ArrayList<>();
        listaArrayOperarios= operario.mostrarOperarios();

        if (!listaArrayOperarios.isEmpty()) {
            for (Operario o : listaArrayOperarios) {

                if (username.equals( o.getCorreoElectronico()) && telefono.equals(o.getTelefono())
                        && segundoApellido.equals(o.getApellido2())) {

                    textContrasena.setText("Su contrasena es: "+ o.getContrasena() );

                } else {
                    textContrasena.setText("Informacion invalida");
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


}