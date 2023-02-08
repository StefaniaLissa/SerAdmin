package com.example.seradmin.calendario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seradmin.R;

public class EventActivity extends AppCompatActivity {

    EditText event_title, event_location, event_description;
    AppCompatCheckBox event_all_day;
    TextView event_start_date, event_start_time, event_end_date, event_end_time, event_repetition;
    ImageView event_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        EditText event_title = (EditText) findViewById(R.id.event_title);
        EditText event_location = (EditText) findViewById(R.id.event_location);
        EditText event_description = (EditText) findViewById(R.id.event_description);
        AppCompatCheckBox event_all_day = (AppCompatCheckBox) findViewById(R.id.event_all_day);
        TextView event_start_date = (TextView) findViewById(R.id.event_start_date);
        TextView event_start_time = (TextView) findViewById(R.id.event_start_time);
        TextView event_end_date = (TextView) findViewById(R.id.event_end_date);
        TextView event_end_time = (TextView) findViewById(R.id.event_end_time);
        TextView event_repetition = (TextView) findViewById(R.id.event_repetition);
        ImageView event_color = (ImageView) findViewById(R.id.event_color);

    }
}