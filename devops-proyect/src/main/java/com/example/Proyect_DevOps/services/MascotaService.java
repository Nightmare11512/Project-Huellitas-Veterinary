package com.example.Proyect_DevOps.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Proyect_DevOps.models.MascotaModel;
import com.example.Proyect_DevOps.models.UsuarioModel;
import com.example.Proyect_DevOps.repositories.MascotaRepository;
import com.example.Proyect_DevOps.repositories.UsuarioRepository;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository user;

    public List<MascotaModel> getAllMascotaByUser(String correo){
        Optional<UsuarioModel> usuarioOpt = user.findByCorreo(correo);
        if (usuarioOpt.isPresent()){
            UsuarioModel usuario = usuarioOpt.get();
            return mascotaRepository.findByUsuario(usuario);
        } else {
            return List.of();
        }
    }

    public long getCountMascotasByUser (String correo) throws RuntimeException {
        Optional<UsuarioModel> usuarioOpt = user.findByCorreo(correo);
        if (usuarioOpt.isPresent()){
            UsuarioModel usuario = usuarioOpt.get();
            return mascotaRepository.countByUsuario(usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
    }
}