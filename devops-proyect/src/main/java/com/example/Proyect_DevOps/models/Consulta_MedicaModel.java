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
}
