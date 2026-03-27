package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.CitaRepository;

@Service
public class CitaService {

    @Autowired
    @SuppressWarnings("unused")
    private CitaRepository citaRepository;
}
