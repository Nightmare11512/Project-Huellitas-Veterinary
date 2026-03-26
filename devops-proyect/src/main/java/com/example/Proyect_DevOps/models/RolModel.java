package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    private String nombre;

    public RolModel(String nombre) {
        this.nombre = nombre;
    }

    public RolModel() {
    }
    
    public int getIdRol() {
        return idRol;
    }
    
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "idRol=" + idRol + ", nombre=" + nombre;
    }
}