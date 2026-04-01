package com.example.Proyect_DevOps.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.Proyect_DevOps.models.EspecieModel;
import com.example.Proyect_DevOps.services.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/especie")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping("/mostrar")
    public List<EspecieModel> getEspecieModels() {
        return especieService.mostrarEspecies();
    }
    
}
