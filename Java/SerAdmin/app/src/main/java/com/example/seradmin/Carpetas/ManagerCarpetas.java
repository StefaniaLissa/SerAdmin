package com.example.seradmin.Carpetas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seradmin.R;
import com.example.seradmin.Recycler.AdaptadorListado;
import com.example.seradmin.Recycler.PerfilesClientes;

import java.util.ArrayList;

public class ManagerCarpetas extends AppCompatActivity {
//    private static final int SELECCION_CARPETAS = 5;
//    RecyclerView RVCarpetas;
//    AdaptadorPDF adaptadorCarpetas;
//    private ArrayList<Carpetas> carpetas;
//    Button anadirCarpeta;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.manager_carpetas);
//
//        Button anadirCarpeta = findViewById(R.id.anadirCarpeta);
//
//        RVCarpetas = (RecyclerView) findViewById(R.id.RVCarpetas);
//        RVCarpetas.setHasFixedSize(true);
//        RVCarpetas.setLayoutManager(new LinearLayoutManager(this));
//
//        carpetas = new ArrayList(new Carpetas());
//
//        adaptadorCarpetas = new AdaptadorCarpetas(carpetas);
//        RVCarpetas.setAdapter(carpetas);
//
//
//       anadirCarpeta.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("application/pdf");
//                startActivityForResult(intent, SELECCION_CARPETAS);
//            }
//        });
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            if (requestCode == SELECCION_CARPETAS && resultCode == RESULT_OK) {
//                Uri uri = data.getData();
//                // Procesa el URI del archivo seleccionado
//            }
//        }
//
//    }
}