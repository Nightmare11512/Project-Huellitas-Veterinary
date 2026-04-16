package com.example.Proyect_DevOps.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Proyect_DevOps.models.UsuarioModel;
import com.example.Proyect_DevOps.repositories.MascotaRepository;
import com.example.Proyect_DevOps.repositories.UsuarioRepository;
import com.example.Proyect_DevOps.models.CitaModel;
import com.example.Proyect_DevOps.models.MascotaModel;
import com.example.Proyect_DevOps.services.CitaService;



@RestController
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping("/{correo}/{idMascota}")
    public ResponseEntity<List<CitaModel>> getListaDeCitasSegunUsuarioYMascota(
            @PathVariable String correo, 
            @PathVariable int idMascota){
        try {
            UsuarioModel usuario = usuarioRepository.findByCorreo(correo)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            MascotaModel mascota = mascotaRepository.findById(idMascota)
                    .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
            List<CitaModel> citas = citaService.getCitasByUsuarioAndMascota(usuario, mascota);
            return ResponseEntity.ok(citas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{correo}")
    public long contarCitasPorUsuario(@PathVariable String correo) {
        return citaService.countByUsuarioCitas(correo);
    }

    @GetMapping("/{correo}")
    public List<CitaModel> conseguirListaDeCitas(@PathVariable String correo) {
        return citaService.getCitasByUser(correo);
    }
    
    
}