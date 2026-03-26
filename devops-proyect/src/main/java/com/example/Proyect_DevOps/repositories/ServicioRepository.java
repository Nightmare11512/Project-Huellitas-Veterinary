package com.example.Proyect_DevOps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Proyect_DevOps.models.ServicioModel;

@Repository
public interface ServicioRepository extends JpaRepository<ServicioModel, Integer>{

}
