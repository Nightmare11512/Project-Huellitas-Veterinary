package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Proyect_DevOps.repositories.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    @SuppressWarnings("unused")
    private EstadoRepository estadoRepository;



}
