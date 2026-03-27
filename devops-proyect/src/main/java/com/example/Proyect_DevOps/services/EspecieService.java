package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.EspecieRepository;

@Service
public class EspecieService {

    @Autowired
    @SuppressWarnings("unused")
    private EspecieRepository especieRepository;
}
