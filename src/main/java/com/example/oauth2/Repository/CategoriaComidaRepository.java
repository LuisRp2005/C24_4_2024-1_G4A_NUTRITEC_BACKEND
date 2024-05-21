package com.example.oauth2.Repository;

import com.example.oauth2.Modelo.CategoriaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaComidaRepository extends JpaRepository<CategoriaComida, Integer> {
}
