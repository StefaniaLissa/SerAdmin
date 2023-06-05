package com.example.seradmin.calendario;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.seradmin.DatePickerFragment;
import com.example.seradmin.EventoMain;
import com.example.seradmin.NuevoEvento;
import com.example.seradmin.R;
import com.example.seradmin.Recycler.Cliente;
import com.example.seradmin.TimePickerFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventActivity extends AppCompatActivity {

    private static final int CLAVE_INSERTADO = 90;
    EditText event_title, event_location, event_description;
    AppCompatCheckBox event_all_day;
    TextView event_start_date, event_start_time, event_end_date, event_end_time, event_repetition;
    ImageView event_color, event_show_on_map;
    MaterialToolbar event_toolbar;
    Button crearEvento;
    Cliente cliente = new Cliente();
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        event_title = (EditText) findViewById(R.id.event_title);
        event_location = (EditText) findViewById(R.id.event_location);
        event_show_on_map = findViewById(R.id.event_show_on_map);
        event_description = (EditText) findViewById(R.id.event_description);
        event_all_day = (AppCompatCheckBox) findViewById(R.id.event_all_day);
        event_start_date = (TextView) findViewById(R.id.event_start_date);
        event_start_time = (TextView) findViewById(R.id.event_start_time);
        event_end_date = (TextView) findViewById(R.id.event_end_date);
        event_end_time = (TextView) findViewById(R.id.event_end_time);
        event_repetition = (TextView) findViewById(R.id.event_repetition);
        event_color = (ImageView) findViewById(R.id.event_color);
        //crearEvento = (Button) findViewById(R.id.crearEvento);
        event_toolbar = (MaterialToolbar) findViewById(R.id.event_toolbar);
        db = FirebaseFirestore.getInstance();

//        MaterialToolbar event_toolbar = findViewById(R.id.event_toolbar);
//        getMenuInflater().inflate(R.menu.menu_event, (Menu) event_toolbar);

        if (getIntent().getExtras().containsKey("Cliente")) {
            cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
            Log.d("Cliente", cliente.getNombre());
        }

        ManejadorFechas manejadorFechaSalida = new ManejadorFechas(event_start_date);
        ManejadorFechas manejadorFechaVuelta = new ManejadorFechas(event_end_date);

        event_start_date.setOnClickListener(manejadorFechaSalida);
        event_end_date.setOnClickListener(manejadorFechaVuelta);

        ManejadorHoras manejadorHoraSalida = new ManejadorHoras(event_start_time);
        ManejadorHoras manejadorHoraVuelta = new ManejadorHoras(event_end_time);

        event_start_time.setOnClickListener(manejadorHoraSalida);
        event_end_time.setOnClickListener(manejadorHoraVuelta);

        event_toolbar.getChildAt(0).setOnClickListener(view -> {
            crearEvento();
        });

//        crearEvento.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Map<String, Object> evento = prepararEvento();
//
//                if (evento != null) {
//
//                    db.collection("Eventos").add(evento).addOnSuccessListener(documentReference -> {
//                                Log.d(TAG, "Insertado evento con ID: " + documentReference.getId());
//
//                            })
//                            .addOnFailureListener(e -> Log.w(TAG, "Error insertando evento", e));
//
//                    Intent intent = new Intent(EventActivity.this, EventoMain.class);
//                    setResult(CLAVE_INSERTADO, intent);
//                    EventActivity.super.onBackPressed();
//                    finish();
//
//                } else {
//
//                    new AlertDialog.Builder(EventActivity.this)
//                            .setTitle("Faltan datos")
//                            .setMessage("Tienes que rellenar todos los campos")
//                            .setIcon(android.R.drawable.ic_dialog_dialer)
//                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int whichButton) {
//
//                                }}
//                            ).show();
//
//                }
//
//            }
//
//        });

        event_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMapa();
            }
        });

        event_show_on_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMapa();
            }
        });

    }

    public class ManejadorFechas implements View.OnClickListener {

        EditText fecha;

        TextView fechaT;

        public ManejadorFechas() {

        }

        public ManejadorFechas(EditText fecha) {
            this.fecha = fecha;
        }

        public ManejadorFechas(TextView fechaT) {
            this.fechaT = fechaT;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.event_start_date:
                    showDatePickerDialog(event_start_date);
                    break;

                case R.id.event_end_date:
                    showDatePickerDialog(event_end_date);
                    break;
            }
        }

    }

    public class ManejadorHoras implements View.OnClickListener {

        EditText hora;

        TextView horaT;

        public ManejadorHoras() {

        }

        public ManejadorHoras(EditText hora) {
            this.hora = hora;
        }

        public ManejadorHoras(TextView horaT) {
            this.horaT = horaT;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.event_start_time:
                    showTimePickerDialog(event_start_time);
                    break;

                case R.id.event_end_time:
                    showTimePickerDialog(event_end_time);
                    break;
            }
        }
    }

    private void showDatePickerDialog(final TextView fecha) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "-" + (month+1) + "-" + year;
                fecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void showTimePickerDialog(final TextView hora) {
        TimePickerFragment newFragment = TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                // +1 because January is zero
                final String selectedHour = hourOfDay + ":" + minute;
                hora.setText(selectedHour);
            }
        });

        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public Map<String, Object> prepararEvento () {

        String s_titulo = event_title.getText().toString(), s_descripcion = event_description.getText().toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String stringDateInicio = event_start_date.getText().toString() + " " + event_start_time.getText().toString();
        String stringDateFin = event_end_date.getText().toString() + " " + event_end_time.getText().toString();
        Date dateInicio = null;
        Date dateFin = null;
        Map<String, Object> evento = new HashMap<>();

        if (s_titulo.isEmpty() || stringDateInicio.isEmpty() || stringDateFin.isEmpty() ||
                //latitud.getText().toString().isEmpty() || longitud.getText().toString().isEmpty() ||
                s_descripcion.isEmpty()) {

            Log.d(TAG, "Tienes que rellenar todos los campos");

        } else {

            try {
                dateInicio = simpleDateFormat.parse(stringDateInicio);
                dateFin = simpleDateFormat.parse(stringDateFin);
            } catch (ParseException e) {
                Log.d(TAG, e.toString());
            }

            Timestamp timeStampInicio = new Timestamp(dateInicio);
            Timestamp timeStampFin = new Timestamp(dateFin);
            //Float s_latitud = Float.valueOf(latitud.getText().toString());
            //Float s_longitud = Float.valueOf(longitud.getText().toString());

            evento.put("Titulo", s_titulo);
            evento.put("Inicio", timeStampInicio);
            evento.put("Fin", timeStampFin);
            evento.put("DNI_Cliente", cliente.getDni_cliente());
            //evento.put("Latitud", s_latitud);
            //evento.put("Longitud", s_longitud);
            evento.put("Descripcion", s_descripcion);
            return evento;

        }

        return null;

    }

    private void abrirMapa(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MapaFragment mapaFragment = new MapaFragment();
        fragmentTransaction.add(mapaFragment, "MapaFragment");
        fragmentTransaction.commit();
    }

    public void crearEvento() {
        Map<String, Object> evento = prepararEvento();

        if (evento != null) {

            db.collection("Eventos").add(evento).addOnSuccessListener(documentReference -> {
                        Log.d(TAG, "Insertado evento con ID: " + documentReference.getId());

                    })
                    .addOnFailureListener(e -> Log.w(TAG, "Error insertando evento", e));

            Intent intent = new Intent(EventActivity.this, Calendario.class);
            setResult(CLAVE_INSERTADO, intent);
            EventActivity.super.onBackPressed();
            finish();

        } else {

            new AlertDialog.Builder(EventActivity.this)
                    .setTitle("Faltan datos")
                    .setMessage("Tienes que rellenar todos los campos")
                    .setIcon(android.R.drawable.ic_dialog_dialer)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }}
                    ).show();

        }
    }

}