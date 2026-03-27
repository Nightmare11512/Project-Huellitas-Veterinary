package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.GestorRepository;

@Service
public class GestorService {

    @Autowired
    @SuppressWarnings("unused")
    private GestorRepository gestorRepository;

}
