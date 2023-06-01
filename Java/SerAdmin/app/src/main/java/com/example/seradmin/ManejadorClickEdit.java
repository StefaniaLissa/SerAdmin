package com.example.seradmin;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ManejadorClickEdit implements View.OnClickListener {

    EditText editText;
    ImageView icono;
    boolean editable = true;
    String decorador;

    ManejadorClickEdit(){

    }

    ManejadorClickEdit(EditText editText, ImageView icono, String decorador){
        this.editText = editText;
        this.icono = icono;
        this.decorador = decorador;
    }

    @Override
    public void onClick(View v) {
        String texto = editText.getText().toString();
        if (editable) {
            editText.setText("");
            editText.setEnabled(true);
            icono.setImageResource(R.drawable.zic_check_vector);
            editable = false;
        } else {
            editText.setText(decorador + texto);
            editText.setEnabled(false);
            icono.setImageResource(android.R.drawable.ic_menu_edit);
            editable = true;
        }
    }
}
