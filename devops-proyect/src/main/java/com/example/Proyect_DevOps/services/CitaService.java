package com.example.Proyect_DevOps.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.Proyect_DevOps.models.CitaModel;
import com.example.Proyect_DevOps.models.MascotaModel;
import com.example.Proyect_DevOps.models.UsuarioModel;
import com.example.Proyect_DevOps.repositories.CitaRepository;
import com.example.Proyect_DevOps.repositories.UsuarioRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<CitaModel> getCitasByUsuarioAndMascota(UsuarioModel usuario, MascotaModel mascota){
        return citaRepository.findByUsuarioMascotaAndMascotaModel(usuario, mascota);
    }

    public long countByUsuarioCitas (String correo){
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()) {
            UsuarioModel usuario = usuarioOpt.get();
            return citaRepository.countByUsuarioMascotaAndEstadoCita(usuario, 2);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
    }

    public List<CitaModel> getCitasByUser (String correo) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()) {
            UsuarioModel usuario = usuarioOpt.get();
            return citaRepository.findByUsuarioMascota(usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
    }
}