package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "especies")
public class EspecieModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspecie;
    private String nombre;

    public EspecieModel(int idEspecie, String nombre) {
        this.idEspecie = idEspecie;
        this.nombre = nombre;
    }

    public EspecieModel() {
    }

    public int getIdEspecie() {
        return idEspecie;
    }
    
    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "idEspecie=" + idEspecie + ", nombre=" + nombre;
    }
}