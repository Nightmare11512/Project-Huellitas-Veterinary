package com.example.Proyect_DevOps.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.Proyect_DevOps.models.UsuarioModel;
import com.example.Proyect_DevOps.repositories.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    ** Este metodo recibe los datos de correo y contraseña del login
    ** si el usuario que se ingreso el correo no esta dentro del sistema
    ** retorna false, si el usuario existe encripta la contraseña y la busca en el usuario
    ** retorna true si la contraseña es correcta y si no retorna false
    */
    public boolean validacionDeLogin(String correo, String contrasena){
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()){
            UsuarioModel usuario = usuarioOpt.get();
            return usuario.getContraseña().equalsIgnoreCase(contrasena);
            //return passwordEncoder.matches(contrasena, usuario.getContraseña());
        } else {
            return false;
        }
    }


    /*
        Con el correo del que inicia secion se extrae del modelo el objeto rol y 
        de este se extrae el id que contenga este mismo si no se encontro el el usuario por correo
        retorna un -1
    */
    public Integer buscarIdRol (String correo){
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()){
            UsuarioModel usuario = usuarioOpt.get();
            System.out.println(usuario.getRol().getIdRol());
            return usuario.getRol().getIdRol();
        } else {
            return -1;
        }
    }

    public UsuarioModel guardaUsuario(UsuarioModel usuario){
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioRepository.save(usuario);
    }

    /*
        Extrae el nombre del Usuario que ingreso su correo dentro del forntend
        si no lo encuentra retorna un "User not found" y si lo encuentra 
        retorna el nombre completo del usuario 
    */
    public String extraerNombre(String correo){
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()){
            UsuarioModel usuario = usuarioOpt.get();
            return usuario.getNombre() + " " + usuario.getPaterno()+ " " + usuario.getMaterno();
        } else {
            return "User not found";
        }
    }
}
