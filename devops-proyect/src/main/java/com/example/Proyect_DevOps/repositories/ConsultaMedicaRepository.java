package com.example.Proyect_DevOps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Proyect_DevOps.models.Consulta_MedicaModel;

@Repository
public interface ConsultaMedicaRepository extends JpaRepository<Consulta_MedicaModel, Integer>{

}
