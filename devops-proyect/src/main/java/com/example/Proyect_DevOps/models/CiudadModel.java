package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ciudades")
public class CiudadModel {

    @Id
    @Column(name = "idCiudad")
    private int idCiudad;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "idEstado")
    private EstadoModel idEstado;

    public CiudadModel(int idCiudad, String nombre, EstadoModel idEstado) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.idEstado = idEstado;
    }

    public CiudadModel() {
    }
    
    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public EstadoModel getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoModel idEstado) {
        this.idEstado = idEstado;
    }
    
    @Override
    public String toString() {
        return "[idCiudad=" + idCiudad + ", nombre=" + nombre + ", idEstado=" + idEstado.getIdEstado() + "]";
    }
}