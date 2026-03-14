package com.example.Proyect_DevOps.models;

import javax.persistence.*;

@Entity
@Table(name = "calles")
public class CalleModel {
    @Id
    @Column(name = "idCalle")
    private int idCalle;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idColonia")
    private ColoniaModel colonia;
    
    public CalleModel(int idCalle, String nombre, ColoniaModel colonia) {
        this.idCalle = idCalle;
        this.nombre = nombre;
        this.colonia = colonia;
    }
    public CalleModel() {
    }

    public int getIdCalle() {
        return idCalle;
    }

    public void setIdCalle(int idCalle) {
        this.idCalle = idCalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ColoniaModel getColonia() {
        return colonia;
    }
    
    public void setColonia(ColoniaModel colonia) {
        this.colonia = colonia;
    }

    @Override
    public String toString() {
        return "[idCalle=" + idCalle + ", nombre=" + nombre + ", colonia=" + colonia.getIdColonia() + "]";
    }
}