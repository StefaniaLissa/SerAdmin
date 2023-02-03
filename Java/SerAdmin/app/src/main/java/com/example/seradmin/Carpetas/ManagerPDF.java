package com.example.seradmin.Carpetas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.seradmin.R;
import com.example.seradmin.Recycler.AdaptadorListado;
import com.example.seradmin.Recycler.PerfilesClientes;

import java.util.ArrayList;

public class ManagerPDF extends AppCompatActivity {
    RecyclerView RVPDF;
    AdaptadorPDF adaptadorPDF;
    private ArrayList<Archivos> archivos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_pdf);

        RVPDF = (RecyclerView) findViewById(R.id.RVPDF);
        RVPDF.setHasFixedSize(true);
        RVPDF.setLayoutManager(new LinearLayoutManager(this));

        //archivos = new ArrayList(new PerfilesClientes().generarPerfiles(NUMERO_ARCHIVOS));

        adaptadorPDF = new AdaptadorPDF(archivos);
        RVPDF.setAdapter(adaptadorPDF);
    }
}