package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "tratamientos")
public class TratamientoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTratameinto;

    private String medicamento;
    private String descripcion;
    private double costo;

    @ManyToMany(mappedBy = "tratamientos")
    private List<Consulta_MedicaModel> consultas = new ArrayList<>();


    public TratamientoModel(String medicamento, String descripcion, double costo){
        this.medicamento = medicamento;
        this.descripcion = descripcion;
        this.costo = costo;
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

    @Override
    public String toString(){
        return "idTratamiento=" + idTratameinto + ", medicamento=" + medicamento + 
        ", descripcion=" + descripcion + ", costo=" + costo;
    }
}
