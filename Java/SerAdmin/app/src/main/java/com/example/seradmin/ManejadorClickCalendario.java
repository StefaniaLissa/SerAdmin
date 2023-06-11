package com.example.seradmin;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.seradmin.InterfazUsuari.Navegador;

public class ManejadorClickCalendario implements View.OnClickListener {

    LinearLayout linearLayout;
    boolean hasEvent = true;

    ManejadorClickCalendario(){

    }

    ManejadorClickCalendario(LinearLayout linearLayout){
        this.linearLayout = linearLayout;
    }

    @Override
    public void onClick(View v) {
        if (hasEvent) {
            //Intent intent = new Intent(Navegador.this, EventoDetalle.class);
            //startActivity(intent);
        } else {

        }
    }
}
