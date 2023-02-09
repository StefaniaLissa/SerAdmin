package com.example.seradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NuevaContraseña extends AppCompatActivity {

    private static final int CLAVE_CONTRASEÑA_CAMBIADA = 55;
    EditText nuevaContraseña, repetirContraseña;
    TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_contrasenya);

        nuevaContraseña = findViewById(R.id.nuevaContraseña);
        repetirContraseña = findViewById(R.id.repetirContraseña);
        mensaje = findViewById(R.id.mensaje);

        if (nuevaContraseña.getText().toString().equals(repetirContraseña.getText().toString())) {
            Intent intent = new Intent(NuevaContraseña.this, Login.class);
            intent.putExtra("CONTRASEÑA_CAMBIADA",CLAVE_CONTRASEÑA_CAMBIADA);
            startActivity(intent);
        } else {
            mensaje.setTextColor(Integer.parseInt("#AB2A3E"));
            mensaje.setText("Los campos deben coincidir");
        }

    }
}