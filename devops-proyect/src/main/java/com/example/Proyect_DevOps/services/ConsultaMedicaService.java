package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.ConsultaMedicaRepository;

@Service
public class ConsultaMedicaService {

    @Autowired
    @SuppressWarnings("unused")
    private ConsultaMedicaRepository consultaMedicaRepository;
}
