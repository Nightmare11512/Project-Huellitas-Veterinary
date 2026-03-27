package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.SucursalRepository;

@Service
public class SucursalService {

    @Autowired
    @SuppressWarnings("unused")
    private SucursalRepository sucursalRepository;

}
