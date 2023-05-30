package com.example.seradmin.InterfazUsuari;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.seradmin.R;
import com.example.seradmin.Tree.MainTree;
import com.example.seradmin.calendario.Calendario;

public class InterfazUsuario extends AppCompatActivity {

    public static final int CLAVE_ELIMINAR_CLIENTE = 63;
    public static final int CLAVE_MODIFICAR_CLIENTE = 64;
    private static final int CLAVE_HOME = 60;
    private static final int CLAVE_FILES = 61;
    private static final int CLAVE_CALENDAR = 62;
    ImageButton home,files,calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_cliente);

        home = findViewById(R.id.home);
        files = findViewById(R.id.files);
        calendar = findViewById(R.id.calendar);

        ActivityResultLauncher controladorInterfaz = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    //Log.d(TAG, "Vuelve cancelado");
                    int code = result.getResultCode();
                    /*switch (code) {
                        case RESULT_CANCELED:
                            break;
                        case CLAVE_INGRESAR:
                            Log.d(TAG, "NUEVO INGRESO");
                            PerfilesImagen nuevoPerfil = (PerfilesImagen) result.getData().getSerializableExtra(mensaje);
                            completo.add(nuevoPerfil);
                            contactoDao.insert(nuevoPerfil);
                            AdaptadorListado = new AdaptadorListado(completo, listener);
                            rV.setAdapter(AdaptadorListado);
                            break;

                        case CLAVE_VOLVER:
                            AdaptadorListado = new AdaptadorListado(completo, listener);
                            rV.setAdapter(AdaptadorListado);
                            break;

                        case CLAVE_ELIMINAR:
                            Log.d(TAG, "NUEVO ELIMINADO");
                            //Intent elim = result.getData();
                            String nom = result.getData().getStringExtra(mensaje2);
                            Log.d(TAG, nom);
                            PerfilesImagen elimPerfil = contactoDao.findByName(nom);
                            Log.d(TAG, elimPerfil.getNombre());
                            completo.remove(elimPerfil);
                            contactoDao.delete(elimPerfil);
                            AdaptadorListado = new AdaptadorImagen(completo, listener);
                            rV.setAdapter(AdaptadorListado);
                            break;

                    }*/

                });

        home.setOnClickListener(view -> {
            Intent intent = new Intent(InterfazUsuario.this, InterfazUsuario.class);
            intent.putExtra("Home", CLAVE_HOME);
            controladorInterfaz.launch(intent);
        });

        files.setOnClickListener(view -> {
            Intent intent = new Intent(InterfazUsuario.this, MainTree.class);
            intent.putExtra("Files", CLAVE_FILES);
            controladorInterfaz.launch(intent);
        });

        calendar.setOnClickListener(view -> {
            Intent intent = new Intent(InterfazUsuario.this, Calendario.class);
            intent.putExtra("Calendar", CLAVE_CALENDAR);
            controladorInterfaz.launch(intent);
        });
    }

}
