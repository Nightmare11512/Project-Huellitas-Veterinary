package com.example.Proyect_DevOps.repositories;

import com.example.Proyect_DevOps.models.PaisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<PaisModel, Integer> {
}
