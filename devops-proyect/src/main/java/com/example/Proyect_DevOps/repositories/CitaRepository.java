package com.example.Proyect_DevOps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Proyect_DevOps.models.CitaModel;
import com.example.Proyect_DevOps.models.MascotaModel;
import com.example.Proyect_DevOps.models.UsuarioModel;

import java.util.List;


@Repository
public interface CitaRepository extends JpaRepository<CitaModel, Integer>{

    List<CitaModel> findByUsuarioMascotaAndMascotaModel (UsuarioModel usuarioMascota, MascotaModel mascotaModel);
}