package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class ServicioModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idServicio;

    private String nombre;
    private double costo;

    public ServicioModel(int idServicio, String nombre, double costo) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.costo = costo;
    }

    public ServicioModel() {
    }

    public int getIdServicio() {
        return idServicio;
    }
    
    public void setIdServicio(int id){
        this.idServicio = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "idServicio=" + idServicio + ", Nombre=" + nombre + ", Costo=" + costo;
    }
}