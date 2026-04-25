package com.example.Proyect_DevOps.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tratamientos")
public class TratamientoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTratameinto;

    private String medicamento;
    private String descripcion;
    private double costo;
    private int estatus;

    @ManyToMany(mappedBy = "tratamientos")
    private List<Consulta_MedicaModel> consultas = new ArrayList<>();


    public TratamientoModel(String medicamento, String descripcion, double costo, int estatus){
        this.medicamento = medicamento;
        this.descripcion = descripcion;
        this.costo = costo;
        this.estatus = estatus;
    }

    public TratamientoModel(){
    }

    public int getId(){
        return idTratameinto;
    }

    public void setId(int idTratameinto){
        this.idTratameinto = idTratameinto;
    }

    public String getMedicamento(){
        return medicamento;
    }

    public void setMedicamento(String medicamento){
        this.medicamento = medicamento;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public double getCosto(){
        return costo;
    }

    public void setCosto(double costo){
        this.costo = costo;
    }

    public List<Consulta_MedicaModel> getListaConsultas(){
        return consultas;
    }

    public void setListaConsultas(List<Consulta_MedicaModel> consultas){
        this.consultas = consultas;
    }

    public int getStatus(){
        return estatus;
    }

    public void setStatus(int estatus){
        this.estatus = estatus;
    }

    @Override
    public String toString(){
        return "idTratamiento=" + idTratameinto + ", medicamento=" + medicamento + 
        ", descripcion=" + descripcion + ", costo=" + costo + ", estatus=" + estatus;
    }
}
