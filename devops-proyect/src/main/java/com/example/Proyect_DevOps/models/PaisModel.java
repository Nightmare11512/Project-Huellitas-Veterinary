package com.example.Proyect_DevOps.models;
import javax.persistence.*;

public class PaisModel {

    @Id
    @Column(name = "idPais")
    private int idPais;
    private String nombre;

    public PaisModel() {
    }

    public PaisModel(int idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "[idPais=" + idPais + ", nombre=" + nombre + "]";
    }
}
