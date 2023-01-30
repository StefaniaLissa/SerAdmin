package com.example.seradmin.Recycler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PerfilesClientes {

    String nombre, apellidos, sexo, sociedad;
    Date nac;
    int imagen;


    public PerfilesClientes(){}

    public PerfilesClientes(String nombre, String apellidos, Date nac, String sexo, String sociedad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nac = nac;
        this.sexo = sexo;
        this.sociedad=sociedad;
    }

    public PerfilesClientes(String nombre, String apellidos, Date nac, String sexo, String sociedad, int imagen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nac = nac;
        this.sexo = sexo;
        this.sociedad=sociedad;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagenes(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String nombre) {
        this.apellidos = apellidos;
    }

    public Date getNac() {
        return nac;
    }

    public void setEdad(Date nac) {
        this.nac = nac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSociedad() {return sociedad; }

    public void setSociedad(String sociedad) {this.sociedad = sociedad; }

    public String getLetra() {
        String letra= getNombre().substring(0);
        return letra;
    };


    public ArrayList<PerfilesClientes> generarPerfiles(int n) {
        ArrayList<PerfilesClientes> perfiles = new ArrayList<PerfilesClientes>();
        Date fecha = new Date(21/03/1994);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < n; i++) {
            perfiles.add( new PerfilesClientes("Nombre " + (i + 1), "Apellido " + (i + 1), fecha,
                    "masculino", "autonomo"));
        }
        return perfiles;
    }

    /*public void idRandom() {
        uid = UUID.randomUUID().toString();
    }*/
}
