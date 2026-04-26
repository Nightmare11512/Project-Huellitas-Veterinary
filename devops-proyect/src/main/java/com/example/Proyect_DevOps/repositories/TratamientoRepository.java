package com.example.Proyect_DevOps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Proyect_DevOps.models.TratamientoModel;

@Repository
public interface TratamientoRepository extends JpaRepository<TratamientoModel, Integer>{

    List<TratamientoModel> findByTratamientoModel_ConsultaMedicaModel_Citas_Usuario_correo(String correo);
}
