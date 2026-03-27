package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    @SuppressWarnings("unused")
    private ServicioRepository servicioRepository;

}
