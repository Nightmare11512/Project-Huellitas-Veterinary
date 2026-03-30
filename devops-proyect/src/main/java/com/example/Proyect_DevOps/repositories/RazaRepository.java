package com.example.Proyect_DevOps.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Proyect_DevOps.models.RazaModel;

@Repository
public interface RazaRepository extends JpaRepository<RazaModel, Integer>{

    List<RazaModel> findByEspecieId (int especieId);

}
