package com.example.Proyect_DevOps.models;
import jakarta.persistence.*;

@Entity
@Table(name = "paises")
public class PaisModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPais;
    private String nombre;

    public PaisModel() {
    }

    public PaisModel(String nombre) {
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
