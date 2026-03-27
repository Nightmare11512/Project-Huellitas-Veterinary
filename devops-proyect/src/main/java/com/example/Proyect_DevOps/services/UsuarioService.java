package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    @SuppressWarnings("unused")
    private UsuarioRepository usuarioRepository;

}
