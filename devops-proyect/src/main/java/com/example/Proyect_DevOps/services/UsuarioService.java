package com.example.Proyect_DevOps.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.Proyect_DevOps.models.UsuarioModel;
import com.example.Proyect_DevOps.repositories.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean validacionDeLogin(String correo, String contrasena){
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByCorreo(correo);
        UsuarioModel usuario = usuarioOpt.get();
        if (usuarioOpt.isPresent()){
            return passwordEncoder.matches(contrasena, usuario.getContraseña());
        } else {
            return false;
        }
    }
}
