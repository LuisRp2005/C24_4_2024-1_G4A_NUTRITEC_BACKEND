package com.example.oauth2.Repository;

import com.example.oauth2.Modelo.AsignacionComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionComidaRepository extends JpaRepository<AsignacionComida, Integer> {
    List<AsignacionComida> findByUsuarioIdUsuario(Integer idUsuario);
}
