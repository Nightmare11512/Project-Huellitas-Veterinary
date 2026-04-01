package com.example.Proyect_DevOps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Proyect_DevOps.models.CitaModel;
import com.example.Proyect_DevOps.models.MascotaModel;
import com.example.Proyect_DevOps.models.UsuarioModel;
import com.example.Proyect_DevOps.repositories.CitaRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<CitaModel> getCitasByUsuarioAndMascota(UsuarioModel usuario, MascotaModel mascota){
        return citaRepository.findByUsuarioMascotaAndMascotaModel(usuario, mascota);
    }
}
