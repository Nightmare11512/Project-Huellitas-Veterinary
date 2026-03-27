package com.example.Proyect_DevOps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Proyect_DevOps.repositories.PaisRepository;
//import com.example.Proyect_DevOps.models.PaisModel;

@Service
public class PaisService {

    @Autowired
    @SuppressWarnings("unused")
    private PaisRepository paisRepository;

}
