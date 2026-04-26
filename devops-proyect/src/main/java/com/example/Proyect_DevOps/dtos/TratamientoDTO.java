package com.example.Proyect_DevOps.dtos;

import lombok.Data;

@Data
public class TratamientoDTO {

    private int idTratameinto;
    private String medicamento;
    private String descripcion;
    private double costo;
    private int estatus;
    private String mascotaNombre;

}
