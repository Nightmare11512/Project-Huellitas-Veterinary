package com.example.Proyect_DevOps.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Proyect_DevOps.models.UsuarioModel;
import com.example.Proyect_DevOps.services.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        
        String correo = body.get("correo");
        String contrasena = body.get("contrasena");

        boolean valido = usuarioService.validacionDeLogin(correo, contrasena);

        if (valido) {
            return ResponseEntity.ok(Map.of("mensaje", "Login Exitoso", "login", true));
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

    @GetMapping("/getRol")
    public Integer obtenerIdRol(@RequestParam String correo) {
        return usuarioService.buscarIdRol(correo);
    }
    
    @PostMapping("/getNombre")
    public ResponseEntity<?> extraerNombre(@RequestBody String correo) {
        String nombre = usuarioService.extraerNombre(correo);
        if (!nombre.equals("User not found")){
            return ResponseEntity.ok(Map.of(
                "nombre", nombre
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of("User found", false));
        }
    }
    
    @GetMapping
    public List<UsuarioModel> getUsuarios() {
        return usuarioService.mostrarUsuarios();
    }
    
}
