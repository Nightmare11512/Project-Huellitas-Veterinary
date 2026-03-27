package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.MascotaRepository;

@Service
public class MascotaService {

    @Autowired
    @SuppressWarnings("unused")
    private MascotaRepository mascotaRepository;

}
