package com.example.Proyect_DevOps.repositories;

import org.springframework.stereotype.Repository;
import com.example.Proyect_DevOps.models.ColoniaModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ColoniaRepository extends JpaRepository<ColoniaModel, Integer>{

}
