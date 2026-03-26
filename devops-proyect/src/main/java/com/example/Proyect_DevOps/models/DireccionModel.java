package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "direcciones")
public class DireccionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDireccion;

    @ManyToOne
    @JoinColumn(name = "idCalle")
    private CalleModel calle;

    public DireccionModel(CalleModel calle) {
        this.calle = calle;
    }

    public DireccionModel() {
    }
    
    public int getIdDireccion() {
        return idDireccion;
    }
    
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    public CalleModel getCalle() {
        return calle;
    }
    
    public void setCalle(CalleModel calle) {
        this.calle = calle;
    }
    
    @Override
    public String toString() {
        return "idDireccion=" + idDireccion + ", calle=" + calle.getIdCalle();
    }
}
