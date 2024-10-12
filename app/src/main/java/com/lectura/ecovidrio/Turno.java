package com.lectura.ecovidrio;



public class Turno {
    int id;
    String nombre;
    String horaEntrada;
    String horaSalida;

    // Constructor con parámetros
    public Turno(int id, String nombre, String horaEntrada, String horaSalida) {
        this.id = id;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    // Constructor vacío
    public Turno() {
    }

    // Getters
    public String getNombre(){
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public  void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
}
