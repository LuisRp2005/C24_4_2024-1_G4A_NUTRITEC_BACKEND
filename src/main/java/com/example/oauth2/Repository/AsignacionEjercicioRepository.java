package com.example.oauth2.Repository;

import com.example.oauth2.Modelo.AsignacionEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionEjercicioRepository extends JpaRepository<AsignacionEjercicio, Integer> {
}
