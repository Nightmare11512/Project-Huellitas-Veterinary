package com.example.Proyect_DevOps.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "Consulta_Medica")
public class Consulta_MedicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsulta;

    @OneToOne
    private CitaModel cita;

    @ManyToMany
    @JoinTable(name = "Consulta-Tratamiento", joinColumns = @JoinColumn(name = "idCita"),
    inverseJoinColumns = @JoinColumn(name = "idTratamiento"))
    private List<TratamientoModel> tratamientos = new ArrayList<>();

    private String motivo;
    private String diagnostico;
    private String recomendaciones;

    public Consulta_MedicaModel(CitaModel cita, String motivo, 
        String diagnostico, String recomendaciones){

        this.cita = cita;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.recomendaciones = recomendaciones;
    }

    public Consulta_MedicaModel(){    
    }

    public int getId(){
        return idConsulta;
    }
 
    public void setId(int idConsulta){
        this.idConsulta = idConsulta;
    }

    public String getMotivo(){
        return motivo;
    }

    public void setMotivo(String motivo){
        this.motivo = motivo;
    }

    public String getDiagnostivo(){
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }

    public String getRecomendaciones(){
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones){
        this.recomendaciones = recomendaciones;
    }

    @Override
    public String toString(){
        return "idConsulta=" + idConsulta + ", Motivo=" + motivo + 
        ", Diagnostico=" + diagnostico + ", Recomendaciones=" + recomendaciones;
    }
}
