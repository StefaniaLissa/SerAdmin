package com.example.seradmin.Carpetas;

import java.util.Date;

public class Carpetas {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getUltimaMod() {
        return ultimaMod;
    }

    public void setUltimaMod(Date ultimaMod) {
        this.ultimaMod = ultimaMod;
    }

    private String nombre;
    private Date ultimaMod;
}
