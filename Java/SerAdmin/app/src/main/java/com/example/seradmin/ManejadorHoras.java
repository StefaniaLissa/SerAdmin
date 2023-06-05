package com.example.seradmin;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.FragmentManager;

public class ManejadorHoras implements View.OnClickListener {

    EditText hora;
    FragmentManager fragmentManager;

    public ManejadorHoras() {

    }

    public ManejadorHoras(EditText hora) {
        this.hora = hora;
    }

    public ManejadorHoras(EditText hora, FragmentManager fragmentManager) {
        this.hora = hora;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == hora.getId()) {
            showTimePickerDialog();
            //showTimePickerDialog(hora, fragmentManager);
        }
    }

    private void showTimePickerDialog() {
        TimePickerFragment newFragment = TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                // +1 because January is zero
                final String selectedHour = hourOfDay + ":" + minute;
                hora.setText(selectedHour);
            }
        });

        newFragment.show(fragmentManager, "timePicker");
    }

    private void showTimePickerDialog(final EditText hora, final FragmentManager fragmentManager) {
        TimePickerFragment newFragment = TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                // +1 because January is zero
                final String selectedHour = hourOfDay + ":" + minute;
                hora.setText(selectedHour);
            }
        });

        newFragment.show(fragmentManager, "timePicker");
    }
}


