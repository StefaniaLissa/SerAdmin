package com.example.seradmin.InterfazUsuari;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seradmin.AdaptadorArchivos;
import com.example.seradmin.Archivos;
import com.example.seradmin.ClienteDetalle;
import com.example.seradmin.EventoDetalle;
import com.example.seradmin.Gestor;
import com.example.seradmin.Login;
import com.example.seradmin.R;
import com.example.seradmin.Recycler.Cliente;
import com.example.seradmin.Tree.ControladoresTree.TreeNode;
import com.example.seradmin.Tree.ControladoresTree.TreeViewAdapter;
import com.example.seradmin.calendario.AdaptadorEventos;
import com.example.seradmin.database.eventosDatabase.Evento;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InterfazUsuario extends Fragment {

    public static final int CLAVE_ELIMINAR_CLIENTE = 63;
    public static final int CLAVE_MODIFICAR_CLIENTE = 64;
    private static final int CLAVE_HOME = 60;
    private static final int CLAVE_FILES = 61;
    private static final int CLAVE_CALENDAR = 62;
    private static final int CLAVE_CLIENTE_DETALLE = 65;
    private static final int CLAVE_LISTA = 66;
    //ImageButton home, files, calendar;
    CircleImageView imagen;
    Cliente cliente = new Cliente();
    Gestor gestor = new Gestor();
    private ArrayList<Evento> eventos = new ArrayList<>();
    RecyclerView RVEventos, RVArchivos;
    private TreeViewAdapter treeViewAdapter;
    TreeNode pdfNode = new TreeNode("Mod 134", R.layout.list_item_file);
    AdaptadorEventos aE = new AdaptadorEventos(new ArrayList<>());
    String pattern = "dd-MM-yy HH:mm";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private ImageView logOutButton;
    AdaptadorArchivos adapter;
    private FirebaseFirestore db;
    private List<Archivos> listaPerfiles;
    private SearchView buscador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.interfaz_cliente, container, false);

//        home = view.findViewById(R.id.home);
//        files = view.findViewById(R.id.files);
//        calendar = view.findViewById(R.id.calendar);
        imagen = view.findViewById(R.id.perfilImagenGestor);
        logOutButton = view.findViewById(R.id.logOutFinal);


        RVEventos = view.findViewById(R.id.recyclerEventos);
        RVEventos.setHasFixedSize(true);
        RVEventos.setLayoutManager(new LinearLayoutManager(requireContext()));

        buscador = view.findViewById(R.id.buscador);
//        buscador.setBackgroundResource(R.drawable.ic_search);
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nombre) {
                adapter.filtrado(nombre);
                aE.filtrado(nombre);
                return false;
            }
        });

        listaPerfiles = new ArrayList<>();
        RVArchivos = view.findViewById(R.id.recyclerDocumentos);
        RVArchivos.setLayoutManager(new LinearLayoutManager(getContext()));


        if (getActivity().getIntent().getExtras() != null) {
            if (getActivity().getIntent().getExtras().containsKey("Cliente")) {
                cliente = (Cliente) getActivity().getIntent().getExtras().getSerializable("Cliente");
                Log.d("Cliente", cliente.getNombre());
                //Log.d("Cliente", cliente.getId());
            }

            if (getActivity().getIntent().getExtras().containsKey("Gestor")) {
                gestor = (Gestor) getActivity().getIntent().getExtras().getSerializable("Gestor");
                Log.d("Gestor", gestor.getNombre());
            }
        }

        poblarRecyclerView();
        poblarRecyclerViewArchivos();

//        files.setOnClickListener(view1 -> {
//            Intent intent = new Intent(requireActivity(), MainTree.class);
//            intent.putExtra("Cliente", cliente);
//            intent.putExtra(EXTRA_ID_CLIENTE, cliente.getDni_cliente());
//            intent.putExtra("Files", CLAVE_FILES);
//            controladorInterfaz.launch(intent);
//            requireActivity().finish();
//        });
//
//        calendar.setOnClickListener(view2 -> {
//            Intent intent = new Intent(requireActivity(), Calendario.class);
//            intent.putExtra("Cliente", cliente);
//            intent.putExtra("Calendar", CLAVE_CALENDAR);
//            controladorInterfaz.launch(intent);
//            requireActivity().finish();
//        });

        imagen.setOnClickListener(view3 -> {
            Intent intent = new Intent(requireActivity(), ClienteDetalle.class);
            intent.putExtra("ClienteDetalle", CLAVE_CLIENTE_DETALLE);
            intent.putExtra("Cliente", cliente);
            intent.putExtra("Gestor", gestor);
            controladorInterfaz.launch(intent);
        });

        logOutButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            controladorInterfaz.launch(intent);
            requireActivity().finishAffinity();
        });

        return view;
    }

    private void poblarRecyclerView() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference eventos_firebase = db.collection("Eventos");
        Query eventosCliente = eventos_firebase.whereEqualTo("DNI_Cliente", cliente.getDni_cliente()).limit(4).orderBy("Inicio", Query.Direction.ASCENDING);
        eventosCliente.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    eventos = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        DocumentReference ref = document.getReference();
                        Evento evento = new Evento();
                        Timestamp timestamp = (Timestamp) document.get("Inicio");
                        evento.setId(document.getId());
                        evento.setTitulo(document.get("Titulo").toString());
                        evento.setFechaInicio(simpleDateFormat.format(timestamp.toDate()));
                        eventos.add(evento);
                    }
                    aE = new AdaptadorEventos(eventos);
                    RVEventos.setAdapter(aE);
                    aE.setClickListener(new AdaptadorEventos.ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, Evento evento) {
                            Intent intent = new Intent(getActivity(), EventoDetalle.class);
                            intent.putExtra("Detalle", CLAVE_LISTA);
                            intent.putExtra("Evento", evento);
                            controladorInterfaz.launch(intent);
                        }
                    });
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

    ActivityResultLauncher<Intent> controladorInterfaz = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                int code = result.getResultCode();
                // Handle the result
            });

    @Override
    public void onResume() {
        super.onResume();
        poblarRecyclerView();
        poblarRecyclerViewArchivos();
    }

    private void poblarRecyclerViewArchivos() {

        FirebaseStorage db = FirebaseStorage.getInstance();
        // Crea una referencia al directorio "pdfs" en Firebase Storage
        StorageReference storageRef = db.getReference().child(cliente.getDni_cliente()).child("pdfs");

        storageRef.listAll().addOnSuccessListener(listResult -> {
            //clientRef.listAll().addOnSuccessListener(listResult -> {
            List<TreeNode> fileRoots = new ArrayList<>();
            listaPerfiles = new ArrayList<>();

            // Por cada archivo PDF recuperado, crea un nodo en el árbol
            for (StorageReference item : listResult.getItems()) {
                Archivos archivo = new Archivos();
                archivo.setNombre(item.getName());
                archivo.setDniCliente(cliente.getDni_cliente());
                //TreeNode pdfNode = new TreeNode(fileName, R.layout.list_item_file);
                //pdfNode.addChild(new TreeNode(fileName, R.layout.list_item_file));
                listaPerfiles.add(archivo);

            }
            //listaPerfiles.sort(Comparator.naturalOrder());
            adapter = new AdaptadorArchivos(listaPerfiles);
            RVArchivos.setAdapter(adapter);

        }).addOnFailureListener(e -> {
            // Maneja el error de recuperación de archivos desde Firebase Storage
            Log.e(TAG, "Error retrieving PDF files from Firebase Storage", e);
        });
    }

}
