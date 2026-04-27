package com.example.Proyect_DevOps.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.dtos.TratamientoDTO;
import com.example.Proyect_DevOps.models.ConsultaMedicaModel;
import com.example.Proyect_DevOps.models.TratamientoModel;
import com.example.Proyect_DevOps.repositories.TratamientoRepository;

@Service
public class TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    private TratamientoDTO convertirADTO(TratamientoModel tratamiento){
        TratamientoDTO tratamientoDTO = new TratamientoDTO();
        tratamientoDTO.setIdTratamiento(tratamiento.getId());
        tratamientoDTO.setCosto(tratamiento.getCosto());
        tratamientoDTO.setDescripcion(tratamiento.getDescripcion());
        tratamientoDTO.setEstatus(tratamiento.getStatus());
        tratamientoDTO.setMedicamento(tratamiento.getMedicamento());
        return tratamientoDTO;
    }

    public List<TratamientoDTO> mostrarTratamientosPorUsuario(String correo){
        List<TratamientoModel> listaModel = tratamientoRepository.findByconsultas_cita_usuarioMascota_correo(correo);
        List<TratamientoDTO> listaDTO = new ArrayList<>();
    
        for (TratamientoModel tratamiento : listaModel) {
            for (ConsultaMedicaModel consulta : tratamiento.getListaConsultas()) {
                TratamientoDTO dto = convertirADTO(tratamiento);
                dto.setMascotaNombre(consulta.getCita().getMascotaModel().getNombre());
                listaDTO.add(dto);
            }
        }
        return listaDTO;
    }
}
