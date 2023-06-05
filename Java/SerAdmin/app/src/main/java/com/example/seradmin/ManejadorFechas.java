package com.example.seradmin;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.FragmentManager;

public class ManejadorFechas implements View.OnClickListener {

    EditText fecha;
    FragmentManager fragmentManager;

    public ManejadorFechas() {

    }

    public ManejadorFechas(EditText fecha) {
        this.fecha = fecha;
    }

    public ManejadorFechas(EditText fecha, FragmentManager fragmentManager) {
        this.fecha = fecha;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == fecha.getId()) {
            showDatePickerDialog();
            //showDatePickerDialog(fecha, fragmentManager);
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "-" + (month+1) + "-" + year;
                fecha.setText(selectedDate);
            }
        });

        newFragment.show(fragmentManager, "datePicker");
    }

    private void showDatePickerDialog(final EditText fecha, final FragmentManager fragmentManager) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "-" + (month+1) + "-" + year;
                fecha.setText(selectedDate);
            }
        });

        newFragment.show(fragmentManager, "datePicker");
    }

}