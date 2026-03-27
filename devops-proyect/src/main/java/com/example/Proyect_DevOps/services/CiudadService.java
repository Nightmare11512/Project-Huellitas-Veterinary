package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.CiudadRepository;

@Service
public class CiudadService {

    @Autowired
    @SuppressWarnings("unused")
    private CiudadRepository ciudadRepository;
}
