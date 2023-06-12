package com.example.seradmin.calendario;

import org.joda.time.DateTime;

import java.util.Date;

public class Event {
    private DateTime inicio;
    private DateTime Fin;
    private String titulo;
    private int color;

    public Event(DateTime inicio, DateTime fin, String titulo) {
        this.inicio = inicio;
        Fin = fin;
        this.titulo = titulo;
    }

    public Event(DateTime inicio, DateTime fin, String titulo, int color) {
        this.inicio = inicio;
        Fin = fin;
        this.titulo = titulo;
        this.color = color;
    }

    public DateTime getInicio() {
        return inicio;
    }

    public void setInicio(DateTime inicio) {
        this.inicio = inicio;
    }

    public DateTime getFin() {
        return Fin;
    }

    public void setFin(DateTime fin) {
        Fin = fin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
