package com.example.Proyect_DevOps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Proyect_DevOps.models.MascotaModel;
import com.example.Proyect_DevOps.services.MascotaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/{correo}")
    public List<MascotaModel> getListMascota(@PathVariable String correo) {
        return mascotaService.getAllMascotaByUser(correo);
    }
    
    @GetMapping("/usuario/{correo}")
    public long contarMascotasPorUsuario(@PathVariable String correo) {
        return mascotaService.getCountMascotasByUser(correo);
    }
    
}
