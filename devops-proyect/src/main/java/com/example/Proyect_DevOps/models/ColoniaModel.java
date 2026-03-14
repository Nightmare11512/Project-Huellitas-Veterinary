package com.example.Proyect_DevOps.models;

import javax.persistence.*;

@Entity
@Table(name = "colonias")
public class ColoniaModel {
    @Id
    @Column(name = "idColonia")
    private int idColonia;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "idCiudad")
    private CiudadModel ciudad;

    public ColoniaModel(int idColonia, String nombre, CiudadModel ciudad) {
        this.idColonia = idColonia;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public ColoniaModel() {
    }

    public int getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(int idColonia) {
        this.idColonia = idColonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CiudadModel getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadModel ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public String toString() {
        return "[idColonia=" + idColonia + ", nombre=" + nombre + ", ciudad=" + ciudad.getIdCiudad() + "]";
    }
}
