package com.example.seradmin.Recycler;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {

    public String nombre;
    public String apellidos;
    public String sexo;
    public String sociedad;
    public Date nac;
    public int imagen;

    private String dni_cliente;

    private String dni_gestor;

    private String num_tel;

    private String contraseña;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, String sexo, String sociedad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.sociedad = sociedad;
    }

    public Cliente(String nombre, String apellidos, String dni_cliente, String dni_gestor, String num_tel, String contraseña, String sociedad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni_cliente = dni_cliente;
        this.dni_gestor = dni_gestor;
        this.num_tel = num_tel;
        this.contraseña = contraseña;
        this.sociedad = sociedad;
    }

    public Cliente(String nombre, String apellidos, Date nac, String sexo, String sociedad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nac = nac;
        this.sexo = sexo;
        this.sociedad = sociedad;
    }

    public Cliente(String nombre, String apellidos, Date nac, String sexo, String sociedad, int imagen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nac = nac;
        this.sexo = sexo;
        this.sociedad = sociedad;
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

    public String getSociedad() {
        return sociedad;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public void setNac(Date nac) {
        this.nac = nac;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public String getDni_gestor() {
        return dni_gestor;
    }

    public void setDni_gestor(String dni_gestor) {
        this.dni_gestor = dni_gestor;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getLetra() {
        String letra = String.valueOf(getNombre().charAt(0));
        return letra;
    }

    ;


    /*public ArrayList<PerfilesClientes> generarPerfiles(int n) {
        ArrayList<PerfilesClientes> perfiles = new ArrayList<>();
        Date fecha = new Date("1994-03-21");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < n; i++) {
            perfiles.add( new PerfilesClientes("Nombre " + (i + 1), "Apellido " + (i + 1), fecha,
                    "masculino", "autonomo"));
        }
        return perfiles;
    }*/
    public ArrayList<Cliente> generarPerfiles(int n) {
        ArrayList<Cliente> perfiles = new ArrayList<>();
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(1994, Calendar.MARCH, 21);
        //Date fecha = calendar.getTime();
        for (int i = 0; i < n; i++) {
            perfiles.add(new Cliente("Nombre " + (i + 1), "Apellido " + (i + 1), "masculino", "autonomo"));
        }
        return perfiles;
    }

}
