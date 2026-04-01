package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "razas")
public class RazaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRaza;
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "idEspecie")
    private EspecieModel especie;

    public RazaModel(String nombre, EspecieModel especie) {
        this.nombre = nombre;
        this.especie = especie;
    }

    public RazaModel() {
    }
    
    public int getIdRaza() {
        return idRaza;
    }
    
    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EspecieModel getEspecie(){
        return especie;
    }

    public void setEspecie(EspecieModel especie){
        this.especie = especie;
    }
    
    @Override
    public String toString() {
        return "idRaza=" + idRaza + ", nombre=" + nombre + ", especie=" + especie.getIdEspecie();
    }
}
