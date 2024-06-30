package com.example.oauth2.Repository;

import com.example.oauth2.Modelo.AsignacionEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionEjercicioRepository extends JpaRepository<AsignacionEjercicio, Integer> {
    List<AsignacionEjercicio> findByUsuarioIdUsuario(Integer idUsuario);
}
