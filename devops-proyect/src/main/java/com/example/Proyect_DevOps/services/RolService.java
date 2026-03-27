package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyect_DevOps.repositories.RolRepository;

@Service
public class RolService {

    @Autowired
    @SuppressWarnings("unused")
    private RolRepository rolRepository;

}
