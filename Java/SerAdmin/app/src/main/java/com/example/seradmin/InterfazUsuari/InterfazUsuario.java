package com.example.seradmin.InterfazUsuari;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seradmin.ClienteDetalle;
import com.example.seradmin.R;
import com.example.seradmin.Recycler.Cliente;
import com.example.seradmin.Tree.*;
import com.example.seradmin.calendario.Calendario;

import de.hdodenhof.circleimageview.CircleImageView;

public class InterfazUsuario extends AppCompatActivity {

    public static final int CLAVE_ELIMINAR_CLIENTE = 63;
    public static final int CLAVE_MODIFICAR_CLIENTE = 64;
    private static final int CLAVE_HOME = 60;
    private static final int CLAVE_FILES = 61;
    private static final int CLAVE_CALENDAR = 62;
    private static final int CLAVE_CLIENTE_DETALLE = 65;
    ImageButton home,files,calendar;
    CircleImageView imagen;
    Cliente cliente = new Cliente();
    RecyclerView eventos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_cliente);

        home = findViewById(R.id.home);
        files = findViewById(R.id.files);
        calendar = findViewById(R.id.calendar);
        imagen = findViewById(R.id.LogOut);

        eventos = findViewById(R.id.recyclerEventos);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("Cliente")) {
                cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
                Log.d("Cliente", cliente.getNombre());
            }

            if (getIntent().getExtras().containsKey("Gestor")) {
                cliente = (Cliente) getIntent().getSerializableExtra("Gestor");
                Log.d("Cliente", cliente.getNombre());
            }

        }
        //Log.d("Cliente", cliente.getId());

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

//        home.setOnClickListener(view -> {
//            Intent intent = new Intent(InterfazUsuario.this, InterfazUsuario.class);
//            intent.putExtra("Home", CLAVE_HOME);
//            controladorInterfaz.launch(intent);
//            finish();
//        });

        files.setOnClickListener(view -> {
            Intent intent = new Intent(InterfazUsuario.this, MainTree.class);
            intent.putExtra("Files", CLAVE_FILES);
            controladorInterfaz.launch(intent);
            finish();
        });

        calendar.setOnClickListener(view -> {
            Intent intent = new Intent(InterfazUsuario.this, Calendario.class);
            intent.putExtra("Calendar", CLAVE_CALENDAR);
            controladorInterfaz.launch(intent);
            finish();
        });

        imagen.setOnClickListener(view -> {
            Intent intent = new Intent(InterfazUsuario.this, ClienteDetalle.class);
            intent.putExtra("ClienteDetalle", CLAVE_CLIENTE_DETALLE);
            intent.putExtra("Cliente", cliente);
            //Log.d("Cliente", cliente.getId());
            controladorInterfaz.launch(intent);
        });
    }

}
