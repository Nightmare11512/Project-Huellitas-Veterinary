package com.example.Proyect_DevOps.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Proyect_DevOps.models.EspecieModel;
import com.example.Proyect_DevOps.repositories.EspecieRepository;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public List<EspecieModel> mostrarEspecies(){
        return especieRepository.findAll();
    }
}
