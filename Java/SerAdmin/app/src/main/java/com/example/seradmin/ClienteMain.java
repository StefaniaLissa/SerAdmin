package com.example.seradmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.seradmin.Recycler.AdaptadorListado;

import com.example.seradmin.Recycler.Cliente;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ClienteMain extends AppCompatActivity {

    public static final int NUMERO_PERFILES = 5;
    private static final int CLAVE_LISTA = 55;
    private static final int CLAVE_AÑADIR = 56;
    RecyclerView RVClientes;
    AdaptadorListado aL;
    FloatingActionButton anadirCliente;
    private ArrayList<Cliente> perfiles;
    private FirebaseFirestore db;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_perfiles);

        anadirCliente = findViewById(R.id.añadir);

        RVClientes = (RecyclerView) findViewById(R.id.RVClientes);
        RVClientes.setHasFixedSize(true);
        RVClientes.setLayoutManager(new LinearLayoutManager(this));

        // Obtención de una instancia de FirebaseFirestore
        db = FirebaseFirestore.getInstance();
        //Obtención de la colección "Clientes" en Firebase
        CollectionReference clientes_firebase = db.collection("Clientes");
        // Obtiene los documentos en la colección de clientes y los agrega a la lista de perfiles
        clientes_firebase.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    perfiles.add(new Cliente(
                            document.get("Nombre").toString(),
                            document.get("Apellido").toString(),
                            document.get("DNI").toString(),
                            document.get("DNI_Gestor").toString(),
                            document.get("Num_Tel").toString(),
                            document.get("Contraseña").toString()
                    ));


                }
                aL = new AdaptadorListado(perfiles);
                RVClientes.setAdapter(aL);
                aL.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ClienteMain.this, "Error al cargar los datos ", Toast.LENGTH_SHORT).show();

            }
        });


        // Inicializa el botón flotante y establece un listener para la acción de agregar un cliente
        anadirCliente=findViewById(R.id.añadir);
        anadirCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre una nueva actividad para agregar un cliente
                Intent intent = new Intent(ClienteMain.this, com.example.seradmin.InterfazUsuari.InterfazUsuario.class);
                intent.putExtra("Detalle", CLAVE_LISTA);
                controladorGestor.launch(intent);
            }
        });



    }

    // Filtra la lista de perfiles según el texto ingresado en la vista de búsqueda
//    @Override
//    public boolean onQueryTextSubmit(String s) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String s) {
//        //aL.filtrado(s);
//
//        return false;
//    }

        //aL = new AdaptadorListado(perfiles);
        //RVClientes.setAdapter(aL);


        ActivityResultLauncher controladorGestor = registerForActivityResult(
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


//        anadirCliente.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(GestorMain.this, NuevoCliente.class);
//                intent.putExtra("Añadir", CLAVE_AÑADIR);
//                controladorGestor.launch(intent);
//            }
//        });
    /*
        anadirCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestorMain.this, Ingresar.class);
                intent.putExtra(CLAVE_LISTA, completo);
                someActivityResultLauncher.launch(intent);
            }
        });*/


}