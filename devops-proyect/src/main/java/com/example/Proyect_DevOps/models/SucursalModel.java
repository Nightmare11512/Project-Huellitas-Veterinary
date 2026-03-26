package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sucursales")
public class SucursalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSucursal;

    @OneToOne
    @JoinColumn(name = "idDireccion")
    private DireccionModel direccion;

    @OneToOne
    @JoinColumn(name = "idGestor")
    private GestorModel gestor;

    public SucursalModel(DireccionModel direccion, GestorModel gestor) {
        this.direccion = direccion;
        this.gestor = gestor;
    }

    public SucursalModel() {
    }

    public int getIdSucursal() {
        return idSucursal;
    }
    
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public GestorModel getGestor() {
        return gestor;
    }
    
    public void setGestor(GestorModel gestor) {
        this.gestor = gestor;
    }

    @Override
    public String toString() {
        return "idSucursal=" + idSucursal + ", direccion=" + direccion.getIdDireccion() + ", gestor=" + gestor.getIdGestor();
    }
}
