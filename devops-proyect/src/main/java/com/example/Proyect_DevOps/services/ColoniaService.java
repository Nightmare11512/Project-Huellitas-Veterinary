package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.ColoniaRepository;

@Service
public class ColoniaService {

    @Autowired
    @SuppressWarnings("unused")
    private ColoniaRepository coloniaRepository;
}
