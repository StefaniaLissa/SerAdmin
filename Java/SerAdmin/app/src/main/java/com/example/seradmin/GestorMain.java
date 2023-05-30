package com.example.seradmin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.seradmin.InterfazUsuari.InterfazUsuario;
import com.example.seradmin.Recycler.AdaptadorListado;
import com.example.seradmin.Recycler.Cliente;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class GestorMain extends AppCompatActivity {

    public static final int NUMERO_PERFILES = 5;
    private static final int CLAVE_LISTA = 55;
    private static final int CLAVE_AÑADIR = 56;
    private static final String TAG = "";
    RecyclerView RVClientes;
    SearchView buscador;
    AdaptadorListado aL;
    FloatingActionButton anadirCliente;
    private ArrayList<Cliente> perfiles = new ArrayList<>();
    private FirebaseFirestore db;
    private SearchView searchView;
    Bundle extra = new Bundle();
    Intent i = new Intent();
    private String dni_gestor = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_perfiles);

        anadirCliente = findViewById(R.id.añadir);
        buscador = findViewById(R.id.buscador);

        RVClientes = (RecyclerView) findViewById(R.id.RVClientes);
        RVClientes.setHasFixedSize(true);
        RVClientes.setLayoutManager(new LinearLayoutManager(this));

        i = getIntent();
        extra = i.getExtras();
        if (extra != null) {
            //Gestor gestor = (Gestor) getIntent().getExtras().getSerializable("Gestor");
            //dni_gestor = gestor.getDNI();
            dni_gestor = getIntent().getStringExtra("DNI_Gestor");
        }
        //Gestor v_gestor = gestor.

        poblarRecyclerView();

        // Inicializa el botón flotante y establece un listener para la acción de agregar un cliente
        anadirCliente=findViewById(R.id.añadir);
        anadirCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre una nueva actividad para agregar un cliente
                Intent intent = new Intent(GestorMain.this, NuevoCliente.class);
                intent.putExtra("Añadir", CLAVE_AÑADIR);
                intent.putExtra("DNI_Gestor", dni_gestor);
                controladorGestor.launch(intent);
                finish();
            }
        });

        // Filtra la lista de perfiles según el texto ingresado en la vista de búsqueda

        //buscador.setBackgroundResource(R.drawable.ic_search);
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nombre) {
                aL.filtrado(nombre);
                return false;
            }
        });



    }

        //aL = new AdaptadorListado(perfiles);
        //RVClientes.setAdapter(aL);


        ActivityResultLauncher controladorGestor = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    //Log.d(TAG, "Vuelve cancelado");
                    int code = result.getResultCode();
                    switch (code) {
                        case RESULT_CANCELED:
                            break;
                        case NuevoCliente.CLAVE_ADD_CLIENTE:
                            Log.d(TAG, "NUEVO CLIENTE");
                            i = getIntent();
                            extra = i.getExtras();
                            if (extra != null) {
                                Cliente cliente = (Cliente) getIntent().getExtras().getSerializable("Cliente");
                                dni_gestor = cliente.getDni_gestor();
                            }
                            poblarRecyclerView();
                            break;
                        case InterfazUsuario.CLAVE_MODIFICAR_CLIENTE:
                            Log.d(TAG, "CLIENTE MODIFICADO");
                            poblarRecyclerView();
                            break;
                        case InterfazUsuario.CLAVE_ELIMINAR_CLIENTE:
                            Log.d(TAG, "CLIENTE ELIMINADO");
                            poblarRecyclerView();
                            break;

                    }

                });

    public void poblarRecyclerView() {
        // Obtención de una instancia de FirebaseFirestore
        db = FirebaseFirestore.getInstance();
        //Obtención de la colección "Clientes" en Firebase
        CollectionReference clientes_firebase = db.collection("Clientes");
        // Obtiene los documentos en la colección de clientes y los agrega a la lista de perfiles
        //Log.d("DNI_Gestor", dni_gestor);
        Query clientesGestor = clientes_firebase.whereEqualTo("DNI_Gestor", dni_gestor);
        clientesGestor.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    perfiles.add(new Cliente(
                            document.get("Nombre").toString(),
                            document.get("Apellido").toString(),
                            document.get("DNI").toString(),
                            document.get("DNI_Gestor").toString(),
                            document.get("Num_Telf").toString(),
                            document.get("Contraseña").toString(),
                            document.get("Sociedad").toString()
                    ));


                }
                aL = new AdaptadorListado(perfiles);
                RVClientes.setAdapter(aL);
                aL.notifyDataSetChanged();

                aL.setClickListener(new AdaptadorListado.ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, Cliente cliente) {
                        Intent intent = new Intent(GestorMain.this, InterfazUsuario.class);
                        intent.putExtra("Detalle", CLAVE_LISTA);
                        intent.putExtra("Cliente", (Serializable) cliente);
                        controladorGestor.launch(intent);
                        finish();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(GestorMain.this, "Error al cargar los datos ", Toast.LENGTH_SHORT).show();
                Log.d("Error al cargar los datos ", "Error al cargar los datos");

            }
        });
    }
}