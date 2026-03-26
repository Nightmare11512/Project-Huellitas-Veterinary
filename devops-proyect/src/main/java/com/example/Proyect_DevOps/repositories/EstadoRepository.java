package com.example.Proyect_DevOps.repositories;

import com.example.Proyect_DevOps.models.EstadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Integer>{

}
